package com.example.spring_webflux.domain.usecase;

import com.example.spring_webflux.domain.entities.model.Flight;
import com.example.spring_webflux.domain.entities.ports.FlightGateway;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public class CreateFlightUseCase {

    private FlightGateway flightGateway;

    public CreateFlightUseCase(FlightGateway flightGateway) {
        this.flightGateway = flightGateway;
    }

    public Mono<Flight> CreateFlight(String flightNumber, String airline, String departureAirport,
                                     String arrivalAirport, LocalDateTime departureTime, LocalDateTime arrivalTime,
                                     String aircraftType, Integer capacity, Double price) {
        return flightGateway.createFlight(flightNumber, airline, departureAirport, arrivalAirport, departureTime, arrivalTime
                , aircraftType, capacity, price);
    }
}