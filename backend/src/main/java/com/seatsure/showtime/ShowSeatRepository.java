package com.seatsure.showtime;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {

    @Query("SELECT ss FROM ShowSeat ss " +
           "JOIN FETCH ss.seat s " +
           "WHERE ss.show.id = :showId " +
           "ORDER BY s.rowLabel ASC, s.seatNumber ASC")
    List<ShowSeat> findByShowIdWithSeatDetails(@Param("showId") Long showId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT ss FROM ShowSeat ss " +
           "JOIN FETCH ss.seat s " +
           "WHERE ss.show.id = :showId AND s.id IN :seatIds " +
           "ORDER BY s.id ASC")
    List<ShowSeat> findByShowIdAndSeatIdInForUpdate(
            @Param("showId") Long showId,
            @Param("seatIds") List<Long> seatIds
    );

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT ss FROM ShowSeat ss " +
           "JOIN FETCH ss.seat s " +
           "WHERE ss.id IN :showSeatIds " +
           "ORDER BY ss.id ASC")
    List<ShowSeat> findByIdInForUpdate(@Param("showSeatIds") List<Long> showSeatIds);

    List<ShowSeat> findByCurrentHoldId(Long holdId);

    @Modifying
    @Query("UPDATE ShowSeat ss SET ss.status = 'AVAILABLE', ss.currentHoldId = NULL " +
           "WHERE ss.currentHoldId = :holdId AND ss.status = 'HELD'")
    int releaseSeatsForHold(@Param("holdId") Long holdId);
}
