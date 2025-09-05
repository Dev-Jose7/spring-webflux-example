package com.example.spring_webflux.infrastructure.gateways.persistence.adapter;

import com.example.spring_webflux.domain.entities.model.Flight;
import com.example.spring_webflux.domain.entities.port.FlightGateway;
import com.example.spring_webflux.infrastructure.gateways.persistence.mapper.FlightMapper;
import com.example.spring_webflux.infrastructure.gateways.persistence.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class FlightGatewayImpl implements FlightGateway {

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;

    @Override
    public Mono<Flight> createFlight(Flight flight) {
        return Mono.just(flight)
                .map(flightMapper::toEntity)
                .flatMap(flightRepository::save)
                .map(flightMapper::toFlight);
    }

    @Override
    public Mono<Boolean> existsByFlightNumber(String flightNumber) {
        return flightRepository.existsByFlightNumber(flightNumber);
    }
}
