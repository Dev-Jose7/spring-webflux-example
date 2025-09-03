package com.example.spring_webflux.domain.entities.ports;

import com.example.spring_webflux.domain.entities.model.Flight;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public interface FlightGateway {
    Mono<Flight> createFlight(
            String flightNumber,
            String airline,
            String departureAirport,
            String arrivalAirport,
            LocalDateTime departureTime,
            LocalDateTime arrivalTime,
            String aircraftType,
            Integer capacity,
            Double price
    );

    Mono<Boolean> existsByFlightNumber(String flightNumber);
}
