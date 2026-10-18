package com.seatsure.cinema;

import com.seatsure.cinema.dto.*;
import com.seatsure.common.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CinemaService {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CinemaService.class);
    private final CinemaRepository cinemaRepository;
    private final ScreenRepository screenRepository;
    private final SeatRepository seatRepository;

    public CinemaService(CinemaRepository cinemaRepository, ScreenRepository screenRepository, SeatRepository seatRepository) {
        this.cinemaRepository = cinemaRepository;
        this.screenRepository = screenRepository;
        this.seatRepository = seatRepository;
    }

    @Transactional(readOnly = true)
    public List<String> getAllCities() {
        return cinemaRepository.findDistinctCities();
    }

    @Transactional(readOnly = true)
    public List<CinemaDto> getCinemas(String city) {
        List<Cinema> cinemas;
        if (city != null && !city.isBlank()) {
            cinemas = cinemaRepository.findByCityIgnoreCase(city.trim());
        } else {
            cinemas = cinemaRepository.findAll();
        }
        return cinemas.stream().map(CinemaDto::fromEntity).toList();
    }

    @Transactional(readOnly = true)
    public CinemaDto getCinemaById(Long cinemaId) {
        Cinema cinema = cinemaRepository.findById(cinemaId).orElseThrow(() -> new ResourceNotFoundException("Cinema", "id", cinemaId));
        return CinemaDto.fromEntity(cinema);
    }

    @Transactional(readOnly = true)
    public List<ScreenDto> getScreensByCinema(Long cinemaId) {
        if (!cinemaRepository.existsById(cinemaId)) {
            throw new ResourceNotFoundException("Cinema", "id", cinemaId);
        }
        return screenRepository.findByCinemaId(cinemaId).stream().map(ScreenDto::fromEntity).toList();
    }

    @Transactional(readOnly = true)
    public List<SeatDto> getSeatsByScreen(Long screenId) {
        if (!screenRepository.existsById(screenId)) {
            throw new ResourceNotFoundException("Screen", "id", screenId);
        }
        return seatRepository.findByScreenIdOrderByRowLabelAscSeatNumberAsc(screenId).stream().map(SeatDto::fromEntity).toList();
    }

    @Transactional
    public CinemaDto createCinema(CreateCinemaRequest request) {
        Cinema cinema = Cinema.builder().name(request.getName().trim()).address(request.getAddress().trim()).city(request.getCity().trim()).status("ACTIVE").build();
        cinema = cinemaRepository.save(cinema);
        log.info("Created cinema id: {} name: \'{}\' city: \'{}\'", cinema.getId(), cinema.getName(), cinema.getCity());
        return CinemaDto.fromEntity(cinema);
    }

    @Transactional
    public ScreenDto createScreen(Long cinemaId, CreateScreenRequest request) {
        Cinema cinema = cinemaRepository.findById(cinemaId).orElseThrow(() -> new ResourceNotFoundException("Cinema", "id", cinemaId));
        Screen screen = Screen.builder().cinema(cinema).name(request.getName().trim()).totalSeats(request.getTotalSeats()).status("ACTIVE").build();
        screen = screenRepository.save(screen);
        generateDefaultSeatsForScreen(screen, 4, 10);
        log.info("Created screen id: {} for cinema id: {}", screen.getId(), cinemaId);
        return ScreenDto.fromEntity(screen);
    }

    @Transactional
    public void generateDefaultSeatsForScreen(Screen screen, int numRows, int seatsPerRow) {
        List<Seat> seats = new ArrayList<>();
        char startRow = 'A';
        for (int r = 0; r < numRows; r++) {
            String rowLabel = String.valueOf((char) (startRow + r));
            SeatType type;
            if (r < 2) {
                type = SeatType.SILVER;
            } else if (r < numRows - 1) {
                type = SeatType.GOLD;
            } else {
                type = SeatType.PLATINUM;
            }
            for (int s = 1; s <= seatsPerRow; s++) {
                seats.add(Seat.builder().screen(screen).rowLabel(rowLabel).seatNumber(s).seatType(type).status("ACTIVE").build());
            }
        }
        seatRepository.saveAll(seats);
        log.info("Generated {} seats for screen id: {}", seats.size(), screen.getId());
    }
}
