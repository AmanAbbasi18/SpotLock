package com.seatsure.movie;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {

    Page<Movie> findByStatus(MovieStatus status, Pageable pageable);

    @Query("SELECT DISTINCT m FROM Movie m " +
           "WHERE (:status IS NULL OR m.status = :status) " +
           "AND (:genre IS NULL OR LOWER(m.genre) LIKE LOWER(CONCAT('%', :genre, '%'))) " +
           "AND (:language IS NULL OR LOWER(m.language) = LOWER(:language)) " +
           "AND (:search IS NULL OR LOWER(m.title) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<Movie> searchMovies(
            @Param("status") MovieStatus status,
            @Param("genre") String genre,
            @Param("language") String language,
            @Param("search") String search,
            Pageable pageable
    );

    @Query("SELECT DISTINCT m FROM Movie m " +
           "JOIN Show s ON s.movie.id = m.id " +
           "JOIN Screen sc ON s.screen.id = sc.id " +
           "JOIN Cinema c ON sc.cinema.id = c.id " +
           "WHERE LOWER(c.city) = LOWER(:city) AND m.status = 'ACTIVE' AND s.status = 'SCHEDULED'")
    List<Movie> findActiveMoviesInCity(@Param("city") String city);
}
