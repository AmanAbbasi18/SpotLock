package com.seatsure.movie;

import com.seatsure.common.response.ApiResponse;
import com.seatsure.common.response.PagedResponse;
import com.seatsure.movie.dto.CreateMovieRequest;
import com.seatsure.movie.dto.MovieDto;
import com.seatsure.movie.dto.UpdateMovieRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/movies")
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Admin: Movies", description = "Administrator movie catalog management")
@SecurityRequirement(name = "bearerAuth")
public class AdminMovieController {

    private final MovieService movieService;

    public AdminMovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    @Operation(summary = "Get all movies including inactive ones for administrative review")
    public ResponseEntity<ApiResponse<PagedResponse<MovieDto>>> getAllMovies(
            @RequestParam(required = false) MovieStatus status,
            @RequestParam(required = false) String search,
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        PagedResponse<MovieDto> movies = movieService.getMovies(status, null, null, search, pageable);
        return ResponseEntity.ok(ApiResponse.ok(movies));
    }

    @PostMapping
    @Operation(summary = "Add a new movie to catalog")
    public ResponseEntity<ApiResponse<MovieDto>> createMovie(@Valid @RequestBody CreateMovieRequest request) {
        MovieDto movie = movieService.createMovie(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok("Movie created successfully", movie));
    }

    @PutMapping("/{movieId}")
    @Operation(summary = "Update an existing movie")
    public ResponseEntity<ApiResponse<MovieDto>> updateMovie(
            @PathVariable Long movieId,
            @Valid @RequestBody UpdateMovieRequest request
    ) {
        MovieDto movie = movieService.updateMovie(movieId, request);
        return ResponseEntity.ok(ApiResponse.ok("Movie updated successfully", movie));
    }

    @PatchMapping("/{movieId}/status")
    @Operation(summary = "Update movie active/inactive status")
    public ResponseEntity<ApiResponse<MovieDto>> updateStatus(
            @PathVariable Long movieId,
            @RequestParam MovieStatus status
    ) {
        MovieDto movie = movieService.updateMovieStatus(movieId, status);
        return ResponseEntity.ok(ApiResponse.ok("Movie status updated", movie));
    }
}
