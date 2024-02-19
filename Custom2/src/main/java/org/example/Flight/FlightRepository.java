package org.example.Flight;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<FlightEntity, Long> {

    @Query(value = "SELECT * FROM flight_entity", nativeQuery = true)
    List<FlightEntity> findAllFlights();
    // native sono Query in SQL come scriverei normalmente in una query su DBeaver;

    @Query(value = "SELECT * FROM flight_entity LIMIT :limit", nativeQuery = true)
    List<FlightEntity> findFlightsByNumber(@Param("limit") Integer limit);

    @Query(value = "SELECT * FROM flight_entity ORDER BY from_Airport ASC", nativeQuery = true)
    List<FlightEntity> findFlightsOrderedByFROM();

    @Query("select f from Flight f where f.status = :p1 or f.status = :p2")
    List<FlightEntity> findByStatus(flightStatus p1, flightStatus p2);
}
