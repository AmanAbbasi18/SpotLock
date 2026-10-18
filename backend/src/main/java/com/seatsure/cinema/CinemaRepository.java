package com.seatsure.cinema;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long> {
    List<Cinema> findByCityIgnoreCase(String city);

    @Query("SELECT DISTINCT c.city FROM Cinema c WHERE c.status = 'ACTIVE' ORDER BY c.city ASC")
    List<String> findDistinctCities();
}
