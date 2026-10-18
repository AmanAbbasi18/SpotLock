package com.seatsure.cinema;

import com.seatsure.cinema.dto.*;
import com.seatsure.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Admin: Cinemas & Screens", description = "Administrator cinema, screen, and seat configuration")
@SecurityRequirement(name = "bearerAuth")
public class AdminCinemaController {

    private final CinemaService cinemaService;

    public AdminCinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @PostMapping("/cinemas")
    @Operation(summary = "Create a new cinema")
    public ResponseEntity<ApiResponse<CinemaDto>> createCinema(@Valid @RequestBody CreateCinemaRequest request) {
        CinemaDto cinema = cinemaService.createCinema(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok("Cinema created successfully", cinema));
    }

    @PostMapping("/cinemas/{cinemaId}/screens")
    @Operation(summary = "Create a new screen in a cinema and generate seat layout")
    public ResponseEntity<ApiResponse<ScreenDto>> createScreen(
            @PathVariable Long cinemaId,
            @Valid @RequestBody CreateScreenRequest request
    ) {
        ScreenDto screen = cinemaService.createScreen(cinemaId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok("Screen created successfully", screen));
    }

    @GetMapping("/screens/{screenId}/seats")
    @Operation(summary = "Get physical seats configured for a screen")
    public ResponseEntity<ApiResponse<List<SeatDto>>> getSeatsByScreen(@PathVariable Long screenId) {
        return ResponseEntity.ok(ApiResponse.ok(cinemaService.getSeatsByScreen(screenId)));
    }
}
