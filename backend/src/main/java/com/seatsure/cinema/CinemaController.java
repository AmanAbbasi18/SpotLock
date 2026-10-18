package com.seatsure.cinema;

import com.seatsure.cinema.dto.CinemaDto;
import com.seatsure.cinema.dto.ScreenDto;
import com.seatsure.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Cinemas & Cities (Public)", description = "Public cinema discovery and screen info")
public class CinemaController {

    private final CinemaService cinemaService;

    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @GetMapping("/cities")
    @Operation(summary = "Get list of all supported cities")
    public ResponseEntity<ApiResponse<List<String>>> getCities() {
        return ResponseEntity.ok(ApiResponse.ok(cinemaService.getAllCities()));
    }

    @GetMapping("/cinemas")
    @Operation(summary = "Get all cinemas, optionally filtered by city")
    public ResponseEntity<ApiResponse<List<CinemaDto>>> getCinemas(@RequestParam(required = false) String city) {
        return ResponseEntity.ok(ApiResponse.ok(cinemaService.getCinemas(city)));
    }

    @GetMapping("/cinemas/{cinemaId}")
    @Operation(summary = "Get cinema details by ID")
    public ResponseEntity<ApiResponse<CinemaDto>> getCinemaById(@PathVariable Long cinemaId) {
        return ResponseEntity.ok(ApiResponse.ok(cinemaService.getCinemaById(cinemaId)));
    }

    @GetMapping("/cinemas/{cinemaId}/screens")
    @Operation(summary = "Get screens belonging to a cinema")
    public ResponseEntity<ApiResponse<List<ScreenDto>>> getScreensByCinema(@PathVariable Long cinemaId) {
        return ResponseEntity.ok(ApiResponse.ok(cinemaService.getScreensByCinema(cinemaId)));
    }
}
