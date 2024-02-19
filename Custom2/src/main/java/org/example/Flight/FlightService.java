package org.example.Flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class FlightService {
    @Autowired FlightRepository flightRepository;

    public List<FlightEntity> allFlights(Pageable pageable) {
        return flightRepository.findAllFlights();
    }

    public List<FlightEntity> allOnTimeFlights() {
        List<FlightEntity> allFlights = flightRepository.findAll();
        List<FlightEntity> onTimeFlights = new ArrayList<>();
        for (FlightEntity flight : allFlights) {
            if (flight.getStatus().equals(flightStatus.ONTIME)) {
                onTimeFlights.add(flight);
            }
        }
        return onTimeFlights;
    }

    public List<FlightEntity> searchByStatus(flightStatus p1, flightStatus p2) {
        return flightRepository.findByStatus(p1, p2);

    }
}
