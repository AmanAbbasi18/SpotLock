package com.seatsure.showtime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {

    @Query("SELECT s FROM Show s " +
           "JOIN FETCH s.screen sc " +
           "JOIN FETCH sc.cinema c " +
           "JOIN FETCH s.movie m " +
           "WHERE m.id = :movieId AND (:city IS NULL OR LOWER(c.city) = LOWER(:city)) " +
           "AND s.status = 'SCHEDULED' AND s.startTime >= :currentTime " +
           "ORDER BY s.startTime ASC")
    List<Show> findUpcomingShowsByMovieAndCity(
            @Param("movieId") Long movieId,
            @Param("city") String city,
            @Param("currentTime") Instant currentTime
    );

    @Query("SELECT s FROM Show s WHERE s.screen.id = :screenId AND s.status = 'SCHEDULED' " +
           "AND (:excludeShowId IS NULL OR s.id != :excludeShowId) " +
           "AND s.startTime < :endTime AND s.endTime > :startTime")
    List<Show> findOverlappingShows(
            @Param("screenId") Long screenId,
            @Param("startTime") Instant startTime,
            @Param("endTime") Instant endTime,
            @Param("excludeShowId") Long excludeShowId
    );

    List<Show> findByScreenIdAndStatus(Long screenId, ShowStatus status);
}
