package com.seatsure.hold;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface SeatHoldRepository extends JpaRepository<SeatHold, Long> {

    Optional<SeatHold> findByUserIdAndShowIdAndStatus(Long userId, Long showId, HoldStatus status);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT sh FROM SeatHold sh WHERE sh.id = :id")
    Optional<SeatHold> findByIdForUpdate(@Param("id") Long id);

    @Query("SELECT sh FROM SeatHold sh WHERE sh.status = 'ACTIVE' AND sh.expiresAt <= :now")
    List<SeatHold> findExpiredActiveHolds(@Param("now") Instant now);

    List<SeatHold> findByUserIdAndStatus(Long userId, HoldStatus status);
}
