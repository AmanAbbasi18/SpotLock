package com.seatsure.showtime;

import com.seatsure.common.response.ApiResponse;
import com.seatsure.showtime.dto.ShowDto;
import com.seatsure.showtime.dto.ShowSeatAvailabilityResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Shows & Seat Availability (Public)", description = "Public showtime browsing and real-time seat availability")
public class ShowController {

    private final ShowtimeService showtimeService;

    public ShowController(ShowtimeService showtimeService) {
        this.showtimeService = showtimeService;
    }

    @GetMapping("/movies/{movieId}/shows")
    @Operation(summary = "Get upcoming scheduled shows for a movie, optionally filtered by city")
    public ResponseEntity<ApiResponse<List<ShowDto>>> getUpcomingShows(
            @PathVariable Long movieId,
            @RequestParam(required = false) String city
    ) {
        return ResponseEntity.ok(ApiResponse.ok(showtimeService.getUpcomingShowsForMovie(movieId, city)));
    }

    @GetMapping("/shows/{showId}")
    @Operation(summary = "Get show details by ID")
    public ResponseEntity<ApiResponse<ShowDto>> getShowById(@PathVariable Long showId) {
        return ResponseEntity.ok(ApiResponse.ok(showtimeService.getShowById(showId)));
    }

    @GetMapping("/shows/{showId}/seats")
    @Operation(summary = "Get real-time seat layout and availability for a show")
    public ResponseEntity<ApiResponse<ShowSeatAvailabilityResponse>> getSeatAvailability(@PathVariable Long showId) {
        return ResponseEntity.ok(ApiResponse.ok(showtimeService.getShowSeatAvailability(showId)));
    }
}
