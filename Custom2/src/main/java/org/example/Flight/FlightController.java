package org.example.Flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/v1")
public class FlightController {

    @Autowired
    FlightRepository flightRepository;
    FlightService flightService;

    @GetMapping("/flights")
    public List<FlightEntity> getAllFlights() {
        return flightRepository.findAllFlights();
    }
    @GetMapping("/flightsOrderedByFrom")
    public List<FlightEntity> getAllFlightsOrderedbByFrom() {
        return flightRepository.findFlightsOrderedByFROM();
    }

    @PostMapping("/flights")
    public Boolean flights(@RequestParam Integer num) {
        Random random = new Random();
        if (num >= 0) {
            for (int i = 1; i < num; i++) {
                int randomIndex = random.nextInt(0, flightStatus.values().length);
                FlightEntity flyght = new FlightEntity();
                flyght.setDescription(generateRandomString());
                flyght.setStatus(flightStatus.values()[randomIndex]);
                flyght.setFromAirport(generateRandomPlace());
                flyght.setToAirport(generateRandomPlace());
                flightRepository.save(flyght);
            }
            return true;
        } else {
            return false;
        }
    }

    @PostMapping("/flightsByNumber")
    public List<FlightEntity> flightsByNumber(@RequestParam(name = "limit", required = false, defaultValue = "100") Optional<Integer> limit) {
        return flightRepository.findFlightsByNumber(limit.orElse(null));
    }

    private String generateRandomString() {
        Random random = new Random();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 5; i++) {
            char randomChar = alphabet.charAt(random.nextInt(alphabet.length()));
            sb.append(randomChar);
        }
        return sb.toString();
    }

    private String generateRandomPlace() {
        Random random = new Random();
        ArrayList<String> posti = new ArrayList<>(Arrays.asList("Palermo", "Roma", "Napoli", "Milano"));
        return posti.get(random.nextInt(posti.size()));
    }
    @GetMapping("/getOnTimeFlights")
    public List<FlightEntity> getOnTimeFlights() {
        List<FlightEntity> onTimeFlight = flightService.allOnTimeFlights();
        if (onTimeFlight.isEmpty()) {
            return null;
        }
        return onTimeFlight;
    }

    @GetMapping("/searchStatus")
    public List<FlightEntity> getFlightsByStatus(@RequestParam(name = "p1") flightStatus p1,
                                           @RequestParam(name = "p2") flightStatus p2) {
        List<FlightEntity> flights = flightService.searchByStatus(p1, p2);
        if (flights.isEmpty()) {
            return null;
        }
        return flights;
    }

}