package com.example.spring_webflux.infrastructure.gateways.repository.implPort;

import com.example.spring_webflux.domain.entities.model.Flight;
import com.example.spring_webflux.domain.entities.ports.FlightGateway;
import com.example.spring_webflux.infrastructure.gateways.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
public class FlightRepositoryImplPort implements FlightGateway {

    private final FlightRepository flightRepository;

    @Override
    public Mono<Flight> createFlight(String flightNumber, String airline, String departureAirport, String arrivalAirport, LocalDateTime departureTime, LocalDateTime arrivalTime, String aircraftType, Integer capacity, Double price) {
        return null;
    }

    @Override
    public Mono<Boolean> existsByFlightNumber(String flightNumber) {
        return null;
    }
}
