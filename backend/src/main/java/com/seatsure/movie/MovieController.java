package com.seatsure.movie;

import com.seatsure.common.response.ApiResponse;
import com.seatsure.common.response.PagedResponse;
import com.seatsure.movie.dto.MovieDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
@Tag(name = "Movies (Public)", description = "Public movie discovery and detail endpoints")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    @Operation(summary = "Get paginated movies with optional search, genre, language, and city filters")
    public ResponseEntity<ApiResponse<PagedResponse<MovieDto>>> getMovies(
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String language,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String city,
            @PageableDefault(size = 12, sort = "releaseDate", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        if (city != null && !city.isBlank()) {
            List<MovieDto> cityMovies = movieService.getActiveMoviesInCity(city);
            PagedResponse<MovieDto> response = PagedResponse.<MovieDto>builder()
                    .content(cityMovies)
                    .pageNumber(0)
                    .pageSize(cityMovies.size())
                    .totalElements(cityMovies.size())
                    .totalPages(1)
                    .isLast(true)
                    .build();
            return ResponseEntity.ok(ApiResponse.ok(response));
        }

        PagedResponse<MovieDto> movies = movieService.getMovies(MovieStatus.ACTIVE, genre, language, search, pageable);
        return ResponseEntity.ok(ApiResponse.ok(movies));
    }

    @GetMapping("/{movieId}")
    @Operation(summary = "Get movie details by ID")
    public ResponseEntity<ApiResponse<MovieDto>> getMovieById(@PathVariable Long movieId) {
        MovieDto movie = movieService.getMovieById(movieId);
        return ResponseEntity.ok(ApiResponse.ok(movie));
    }
}
