package com.seatsure.movie;

import com.seatsure.common.exception.ResourceNotFoundException;
import com.seatsure.common.response.PagedResponse;
import com.seatsure.movie.dto.CreateMovieRequest;
import com.seatsure.movie.dto.MovieDto;
import com.seatsure.movie.dto.UpdateMovieRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class MovieService {

    private static final Logger log = LoggerFactory.getLogger(MovieService.class);

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Transactional(readOnly = true)
    public PagedResponse<MovieDto> getMovies(
            MovieStatus status,
            String genre,
            String language,
            String search,
            Pageable pageable
    ) {
        Page<Movie> moviesPage = movieRepository.searchMovies(status, genre, language, search, pageable);
        List<MovieDto> content = moviesPage.getContent().stream().map(MovieDto::fromEntity).toList();

        return PagedResponse.<MovieDto>builder()
                .content(content)
                .pageNumber(moviesPage.getNumber())
                .pageSize(moviesPage.getSize())
                .totalElements(moviesPage.getTotalElements())
                .totalPages(moviesPage.getTotalPages())
                .isLast(moviesPage.isLast())
                .build();
    }

    @Transactional(readOnly = true)
    public MovieDto getMovieById(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie", "id", id));
        return MovieDto.fromEntity(movie);
    }

    @Transactional(readOnly = true)
    public List<MovieDto> getActiveMoviesInCity(String city) {
        return movieRepository.findActiveMoviesInCity(city).stream()
                .map(MovieDto::fromEntity)
                .toList();
    }

    @Transactional
    public MovieDto createMovie(CreateMovieRequest request) {
        Movie movie = Movie.builder()
                .title(request.getTitle().trim())
                .description(request.getDescription())
                .durationMinutes(request.getDurationMinutes())
                .language(request.getLanguage().trim())
                .genre(request.getGenre().trim())
                .releaseDate(request.getReleaseDate())
                .posterUrl(request.getPosterUrl())
                .contentRating(request.getContentRating())
                .status(request.getStatus() != null ? request.getStatus() : MovieStatus.ACTIVE)
                .build();

        movie = movieRepository.save(movie);
        log.info("Created new movie with id: {} title: '{}'", movie.getId(), movie.getTitle());
        return MovieDto.fromEntity(movie);
    }

    @Transactional
    public MovieDto updateMovie(Long id, UpdateMovieRequest request) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie", "id", id));

        if (request.getTitle() != null && !request.getTitle().isBlank()) {
            movie.setTitle(request.getTitle().trim());
        }
        if (request.getDescription() != null) {
            movie.setDescription(request.getDescription());
        }
        if (request.getDurationMinutes() != null) {
            movie.setDurationMinutes(request.getDurationMinutes());
        }
        if (request.getLanguage() != null && !request.getLanguage().isBlank()) {
            movie.setLanguage(request.getLanguage().trim());
        }
        if (request.getGenre() != null && !request.getGenre().isBlank()) {
            movie.setGenre(request.getGenre().trim());
        }
        if (request.getReleaseDate() != null) {
            movie.setReleaseDate(request.getReleaseDate());
        }
        if (request.getPosterUrl() != null) {
            movie.setPosterUrl(request.getPosterUrl());
        }
        if (request.getContentRating() != null) {
            movie.setContentRating(request.getContentRating());
        }
        if (request.getStatus() != null) {
            movie.setStatus(request.getStatus());
        }

        movie = movieRepository.save(movie);
        log.info("Updated movie with id: {}", movie.getId());
        return MovieDto.fromEntity(movie);
    }

    @Transactional
    public MovieDto updateMovieStatus(Long id, MovieStatus status) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie", "id", id));
        movie.setStatus(status);
        movie = movieRepository.save(movie);
        log.info("Updated movie id: {} status to: {}", id, status);
        return MovieDto.fromEntity(movie);
    }
}
