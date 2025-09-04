package com.example.spring_webflux.infrastructure.gateways.repository;

import com.example.spring_webflux.infrastructure.gateways.entity.FlightEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface FlightRepository extends R2dbcRepository<FlightEntity, Long> {
    Mono<Boolean> existsByFlightNumber(String flightNumber);
    Mono<FlightEntity> findByFlightNumber(String flightNumber);
}
