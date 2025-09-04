package com.example.spring_webflux.domain.entities.port;

import com.example.spring_webflux.domain.entities.model.Flight;
import reactor.core.publisher.Mono;

public interface FlightGateway {
    Mono<Flight> createFlight( Flight flight);

    Mono<Boolean> existsByFlightNumber(String flightNumber);
}
