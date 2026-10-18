package com.seatsure.bootstrap;

import com.seatsure.cinema.*;
import com.seatsure.movie.Movie;
import com.seatsure.movie.MovieRepository;
import com.seatsure.movie.MovieStatus;
import com.seatsure.showtime.*;
import com.seatsure.user.Role;
import com.seatsure.user.User;
import com.seatsure.user.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DataInitializer.class);
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final CinemaRepository cinemaRepository;
    private final ScreenRepository screenRepository;
    private final SeatRepository seatRepository;
    private final ShowRepository showRepository;
    private final ShowSeatRepository showSeatRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, MovieRepository movieRepository, CinemaRepository cinemaRepository, ScreenRepository screenRepository, SeatRepository seatRepository, ShowRepository showRepository, ShowSeatRepository showSeatRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
        this.cinemaRepository = cinemaRepository;
        this.screenRepository = screenRepository;
        this.seatRepository = seatRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Value("${seatsure.admin.email:admin@seatsure.com}")
    private String adminEmail;
    @Value("${seatsure.admin.password:Admin@12345}")
    private String adminPassword;
    @Value("${seatsure.admin.name:SeatSure Administrator}")
    private String adminName;

    @Override
    @Transactional
    public void run(String... args) {
        bootstrapAdminUser();
        bootstrapDemoCustomer();
        if (movieRepository.count() == 0) {
            seedCinemaCatalog();
        }
    }

    private void bootstrapAdminUser() {
        if (!userRepository.existsByEmail(adminEmail)) {
            User admin = User.builder().name(adminName).email(adminEmail.toLowerCase().trim()).passwordHash(passwordEncoder.encode(adminPassword)).role(Role.ROLE_ADMIN).enabled(true).build();
            userRepository.save(admin);
            log.info("Bootstrapped default administrator account: {}", adminEmail);
        }
    }

    private void bootstrapDemoCustomer() {
        String customerEmail = "customer@seatsure.com";
        if (!userRepository.existsByEmail(customerEmail)) {
            User customer = User.builder().name("Demo Customer").email(customerEmail).passwordHash(passwordEncoder.encode("Password@123")).role(Role.ROLE_CUSTOMER).enabled(true).build();
            userRepository.save(customer);
            log.info("Bootstrapped demo customer account: {}", customerEmail);
        }
    }

    private void seedCinemaCatalog() {
        log.info("Starting seed data creation for movies, cinemas, screens, and shows...");
        // 1. Seed Movies
        Movie m1 = Movie.builder().title("Interstellar").description("A team of explorers travel through a wormhole in space in an attempt to ensure humanity\'s survival.").durationMinutes(169).language("English").genre("Sci-Fi").releaseDate(LocalDate.now().minusDays(30)).posterUrl("https://images.unsplash.com/photo-1534447677768-be436bb09401?w=800&auto=format&fit=crop&q=80").contentRating("UA").status(MovieStatus.ACTIVE).build();
        Movie m2 = Movie.builder().title("Inception").description("A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea.").durationMinutes(148).language("English").genre("Action / Sci-Fi").releaseDate(LocalDate.now().minusDays(60)).posterUrl("https://images.unsplash.com/photo-1518709268805-4e9042af9f23?w=800&auto=format&fit=crop&q=80").contentRating("UA").status(MovieStatus.ACTIVE).build();
        Movie m3 = Movie.builder().title("Oppenheimer").description("The story of American scientist J. Robert Oppenheimer and his role in the development of the atomic bomb.").durationMinutes(180).language("English").genre("Drama / Biography").releaseDate(LocalDate.now().minusDays(90)).posterUrl("https://images.unsplash.com/photo-1440404653325-ab127d49abc1?w=800&auto=format&fit=crop&q=80").contentRating("A").status(MovieStatus.ACTIVE).build();
        Movie m4 = Movie.builder().title("Kalki 2898 AD").description("A modern avatar of Vishnu descends to Earth to protect the world from evil forces in a dystopian mythological future.").durationMinutes(181).language("Hindi").genre("Mythological Sci-Fi").releaseDate(LocalDate.now().minusDays(15)).posterUrl("https://images.unsplash.com/photo-1579783900882-c0d3dad7b119?w=800&auto=format&fit=crop&q=80").contentRating("UA").status(MovieStatus.ACTIVE).build();
        List<Movie> savedMovies = movieRepository.saveAll(List.of(m1, m2, m3, m4));
        // 2. Seed Cinemas
        Cinema c1 = Cinema.builder().name("PVR ICON: Phoenix Palladium").address("462, Senapati Bapat Marg, Lower Parel").city("Mumbai").status("ACTIVE").build();
        Cinema c2 = Cinema.builder().name("PVR IMAX: Forum Mall").address("Hosur Road, Koramangala").city("Bengaluru").status("ACTIVE").build();
        Cinema c3 = Cinema.builder().name("PVR Director\'s Cut: Ambience Mall").address("Nelson Mandela Road, Vasant Kunj").city("Delhi NCR").status("ACTIVE").build();
        Cinema c4 = Cinema.builder().name("Prasads Multiplex").address("NTR Gardens, Necklace Road").city("Hyderabad").status("ACTIVE").build();
        List<Cinema> savedCinemas = cinemaRepository.saveAll(List.of(c1, c2, c3, c4));
        // 3. Seed Screens & Seats
        List<Screen> allScreens = new ArrayList<>();
        for (Cinema cinema : savedCinemas) {
            Screen s1 = Screen.builder().cinema(cinema).name("Screen 1 (IMAX)").totalSeats(40).status("ACTIVE").build();
            Screen s2 = Screen.builder().cinema(cinema).name("Screen 2 (Dolby Atmos Luxe)").totalSeats(30).status("ACTIVE").build();
            allScreens.addAll(screenRepository.saveAll(List.of(s1, s2)));
        }
        // Generate physical seats for all screens
        for (Screen screen : allScreens) {
            List<Seat> seats = new ArrayList<>();
            char[] rows = {'A', 'B', 'C', 'D'};
            int seatsPerRow = screen.getTotalSeats() / 4;
            for (char row : rows) {
                SeatType type = (row == 'A' || row == 'B') ? SeatType.SILVER : (row == 'C') ? SeatType.GOLD : SeatType.PLATINUM;
                for (int num = 1; num <= seatsPerRow; num++) {
                    seats.add(Seat.builder().screen(screen).rowLabel(String.valueOf(row)).seatNumber(num).seatType(type).status("ACTIVE").build());
                }
            }
            seatRepository.saveAll(seats);
        }
        // 4. Seed Scheduled Shows & Show Seats
        Instant baseTime = Instant.now().plus(2, ChronoUnit.HOURS).truncatedTo(ChronoUnit.HOURS);
        int[] showOffsetsHours = {0, 4, 8, 24, 28};
        for (int i = 0; i < savedMovies.size(); i++) {
            Movie movie = savedMovies.get(i);
            Screen screen = allScreens.get(i % allScreens.size());
            for (int offset : showOffsetsHours) {
                Instant start = baseTime.plus(offset, ChronoUnit.HOURS);
                Instant end = start.plus(movie.getDurationMinutes(), ChronoUnit.MINUTES);
                Show show = Show.builder().movie(movie).screen(screen).startTime(start).endTime(end).basePrice(BigDecimal.valueOf(250.0)).status(ShowStatus.SCHEDULED).build();
                show = showRepository.save(show);
                // Populate ShowSeat booking inventory
                List<Seat> physicalSeats = seatRepository.findByScreenIdOrderByRowLabelAscSeatNumberAsc(screen.getId());
                List<ShowSeat> showSeats = new ArrayList<>(physicalSeats.size());
                for (Seat seat : physicalSeats) {
                    BigDecimal seatPrice = switch (seat.getSeatType()) {
                        case SILVER -> BigDecimal.valueOf(250.0);
                        case GOLD -> BigDecimal.valueOf(350.0);
                        case PLATINUM -> BigDecimal.valueOf(500.0);
                    };
                    showSeats.add(ShowSeat.builder().show(show).seat(seat).price(seatPrice).status(ShowSeatStatus.AVAILABLE).build());
                }
                showSeatRepository.saveAll(showSeats);
            }
        }
        log.info("Completed cinema seed data successfully!");
    }
}
