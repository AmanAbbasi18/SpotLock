package com.seatsure.hold;

import com.seatsure.common.exception.SeatUnavailableException;
import com.seatsure.hold.dto.CreateHoldRequest;
import com.seatsure.hold.dto.SeatHoldResponse;
import com.seatsure.security.UserPrincipal;
import com.seatsure.showtime.Show;
import com.seatsure.showtime.ShowRepository;
import com.seatsure.showtime.ShowSeat;
import com.seatsure.showtime.ShowSeatRepository;
import com.seatsure.showtime.ShowSeatStatus;
import com.seatsure.user.Role;
import com.seatsure.user.User;
import com.seatsure.user.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class SeatHoldConcurrencyIntegrationTest {

    @Autowired
    private SeatHoldService seatHoldService;

    @Autowired
    private ShowSeatRepository showSeatRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Concurrency Test: 10 concurrent requests attempting to hold the same seat simultaneously")
    void testConcurrentSeatHoldCompetition() throws InterruptedException {
        // Find an active show with available seats
        List<Show> shows = showRepository.findAll();
        assertThat(shows).isNotEmpty();
        Show show = shows.get(0);

        List<ShowSeat> availableSeats = showSeatRepository.findByShowIdWithSeatDetails(show.getId()).stream()
                .filter(ss -> ss.getStatus() == ShowSeatStatus.AVAILABLE)
                .toList();

        assertThat(availableSeats).isNotEmpty();
        Long contestedSeatId = availableSeats.get(0).getSeat().getId();

        // Prepare 10 test users
        int threadCount = 10;
        List<User> testUsers = new ArrayList<>(threadCount);
        for (int i = 0; i < threadCount; i++) {
            String email = "concurrent_user_" + i + "@example.com";
            User user = userRepository.findByEmail(email).orElseGet(() ->
                    userRepository.save(User.builder()
                            .name("Concurrent User " + email)
                            .email(email)
                            .passwordHash("hashed")
                            .role(Role.ROLE_CUSTOMER)
                            .enabled(true)
                            .build())
            );
            testUsers.add(user);
        }

        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CountDownLatch readyLatch = new CountDownLatch(threadCount);
        CountDownLatch startLatch = new CountDownLatch(1);

        AtomicInteger successCount = new AtomicInteger(0);
        AtomicInteger conflictCount = new AtomicInteger(0);
        List<Future<?>> futures = new ArrayList<>();

        for (int i = 0; i < threadCount; i++) {
            User user = testUsers.get(i);
            futures.add(executor.submit(() -> {
                // Set security context for thread
                UserPrincipal principal = UserPrincipal.create(user);
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);

                readyLatch.countDown();
                try {
                    // Wait until all threads are ready, then fire at the exact same moment
                    startLatch.await();

                    CreateHoldRequest request = CreateHoldRequest.builder()
                            .seatIds(List.of(contestedSeatId))
                            .build();

                    SeatHoldResponse response = seatHoldService.createHold(show.getId(), request);
                    if (response != null && response.getHoldId() != null) {
                        successCount.incrementAndGet();
                    }
                } catch (SeatUnavailableException e) {
                    conflictCount.incrementAndGet();
                } catch (Exception e) {
                    // Other business exceptions
                    if (e.getCause() instanceof SeatUnavailableException) {
                        conflictCount.incrementAndGet();
                    }
                } finally {
                    SecurityContextHolder.clearContext();
                }
            }));
        }

        // Wait for all threads to be ready
        readyLatch.await(5, TimeUnit.SECONDS);
        // Release the trigger!
        startLatch.countDown();

        for (Future<?> f : futures) {
            try {
                f.get(10, TimeUnit.SECONDS);
            } catch (Exception ignored) {}
        }
        executor.shutdown();

        // VERIFICATION:
        // 1. Exactly ONE request must succeed in holding the seat
        assertThat(successCount.get())
                .as("Exactly one concurrent request must win the contested seat")
                .isEqualTo(1);

        // 2. The other 9 requests must fail with a conflict / seat unavailable error
        assertThat(conflictCount.get())
                .as("All competing requests must receive a seat unavailable conflict error")
                .isEqualTo(threadCount - 1);

        // 3. The database state must show the seat as HELD with currentHoldId set
        List<ShowSeat> currentSeats = showSeatRepository.findByShowIdWithSeatDetails(show.getId()).stream()
                .filter(ss -> ss.getSeat().getId().equals(contestedSeatId))
                .toList();
        assertThat(currentSeats).hasSize(1);
        ShowSeat bookedSeat = currentSeats.get(0);
        assertThat(bookedSeat.getStatus()).isEqualTo(ShowSeatStatus.HELD);
        assertThat(bookedSeat.getCurrentHoldId()).isNotNull();
    }
}
