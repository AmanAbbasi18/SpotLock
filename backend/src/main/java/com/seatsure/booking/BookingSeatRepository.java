package com.seatsure.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingSeatRepository extends JpaRepository<BookingSeat, Long> {

    @Query("SELECT bs FROM BookingSeat bs " +
           "JOIN FETCH bs.showSeat ss " +
           "JOIN FETCH ss.seat s " +
           "WHERE bs.booking.id = :bookingId " +
           "ORDER BY s.rowLabel ASC, s.seatNumber ASC")
    List<BookingSeat> findByBookingIdWithDetails(@Param("bookingId") Long bookingId);
}
