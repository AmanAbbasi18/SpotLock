package com.seatsure.showtime;

import com.seatsure.cinema.Screen;
import com.seatsure.cinema.ScreenRepository;
import com.seatsure.cinema.Seat;
import com.seatsure.cinema.SeatRepository;
import com.seatsure.cinema.SeatType;
import com.seatsure.common.exception.DuplicateResourceException;
import com.seatsure.common.exception.ResourceNotFoundException;
import com.seatsure.common.util.SecurityUtils;
import com.seatsure.hold.HoldStatus;
import com.seatsure.hold.SeatHold;
import com.seatsure.hold.SeatHoldRepository;
import com.seatsure.movie.Movie;
import com.seatsure.movie.MovieRepository;
import com.seatsure.showtime.dto.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShowtimeService {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ShowtimeService.class);
    private final ShowRepository showRepository;
    private final ShowSeatRepository showSeatRepository;
    private final MovieRepository movieRepository;
    private final ScreenRepository screenRepository;
    private final SeatRepository seatRepository;
    private final SeatHoldRepository seatHoldRepository;

    public ShowtimeService(ShowRepository showRepository, ShowSeatRepository showSeatRepository, MovieRepository movieRepository, ScreenRepository screenRepository, SeatRepository seatRepository, SeatHoldRepository seatHoldRepository) {
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.movieRepository = movieRepository;
        this.screenRepository = screenRepository;
        this.seatRepository = seatRepository;
        this.seatHoldRepository = seatHoldRepository;
    }

    @Transactional(readOnly = true)
    public List<ShowDto> getUpcomingShowsForMovie(Long movieId, String city) {
        if (!movieRepository.existsById(movieId)) {
            throw new ResourceNotFoundException("Movie", "id", movieId);
        }
        return showRepository.findUpcomingShowsByMovieAndCity(movieId, city, Instant.now()).stream().map(ShowDto::fromEntity).toList();
    }

    @Transactional(readOnly = true)
    public ShowDto getShowById(Long showId) {
        Show show = showRepository.findById(showId).orElseThrow(() -> new ResourceNotFoundException("Show", "id", showId));
        return ShowDto.fromEntity(show);
    }

    @Transactional(readOnly = true)
    public ShowSeatAvailabilityResponse getShowSeatAvailability(Long showId) {
        if (!showRepository.existsById(showId)) {
            throw new ResourceNotFoundException("Show", "id", showId);
        }
        Long currentUserId = SecurityUtils.getCurrentUser().map(p -> p.getId()).orElse(null);
        Long currentHoldId = null;
        if (currentUserId != null) {
            currentHoldId = seatHoldRepository.findByUserIdAndShowIdAndStatus(currentUserId, showId, HoldStatus.ACTIVE).map(SeatHold::getId).orElse(null);
        }
        List<ShowSeat> showSeats = showSeatRepository.findByShowIdWithSeatDetails(showId);
        int total = showSeats.size();
        int available = 0;
        int held = 0;
        int booked = 0;
        List<ShowSeatDto> seatDtos = new ArrayList<>(total);
        for (ShowSeat ss : showSeats) {
            switch (ss.getStatus()) {
                case AVAILABLE -> available++;
                case HELD -> held++;
                case BOOKED -> booked++;
            }
            seatDtos.add(ShowSeatDto.fromEntity(ss, currentUserId, currentHoldId));
        }
        return ShowSeatAvailabilityResponse.builder().showId(showId).totalSeats(total).availableSeats(available).heldSeats(held).bookedSeats(booked).seats(seatDtos).build();
    }

    @Transactional
    public ShowDto createShow(CreateShowRequest request) {
        Movie movie = movieRepository.findById(request.getMovieId()).orElseThrow(() -> new ResourceNotFoundException("Movie", "id", request.getMovieId()));
        Screen screen = screenRepository.findById(request.getScreenId()).orElseThrow(() -> new ResourceNotFoundException("Screen", "id", request.getScreenId()));
        if (request.getEndTime().isBefore(request.getStartTime())) {
            throw new IllegalArgumentException("Show end time must be after start time");
        }
        // Validate overlapping shows on the screen
        List<Show> overlapping = showRepository.findOverlappingShows(screen.getId(), request.getStartTime(), request.getEndTime(), null);
        if (!overlapping.isEmpty()) {
            throw new DuplicateResourceException(String.format("Cannot schedule show: Screen \'%s\' already has an overlapping show scheduled between %s and %s", screen.getName(), overlapping.get(0).getStartTime(), overlapping.get(0).getEndTime()));
        }
        Show show = Show.builder().movie(movie).screen(screen).startTime(request.getStartTime()).endTime(request.getEndTime()).basePrice(request.getBasePrice()).status(ShowStatus.SCHEDULED).build();
        show = showRepository.save(show);
        // Populate ShowSeat booking inventory from physical seats
        List<Seat> physicalSeats = seatRepository.findByScreenIdOrderByRowLabelAscSeatNumberAsc(screen.getId());
        List<ShowSeat> inventory = new ArrayList<>(physicalSeats.size());
        for (Seat seat : physicalSeats) {
            BigDecimal price = calculateSeatPrice(request.getBasePrice(), seat.getSeatType());
            inventory.add(ShowSeat.builder().show(show).seat(seat).price(price).status(ShowSeatStatus.AVAILABLE).build());
        }
        showSeatRepository.saveAll(inventory);
        log.info("Created show id: {} with {} seats for movie \'{}\' in screen \'{}\'", show.getId(), inventory.size(), movie.getTitle(), screen.getName());
        return ShowDto.fromEntity(show);
    }

    private BigDecimal calculateSeatPrice(BigDecimal basePrice, SeatType seatType) {
        return switch (seatType) {
            case SILVER -> basePrice;
            case GOLD -> basePrice.multiply(BigDecimal.valueOf(1.4)).setScale(2, RoundingMode.HALF_UP);
            case PLATINUM -> basePrice.multiply(BigDecimal.valueOf(2.0)).setScale(2, RoundingMode.HALF_UP);
        };
    }
}
