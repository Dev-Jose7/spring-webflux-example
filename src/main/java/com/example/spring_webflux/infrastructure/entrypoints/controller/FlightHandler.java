package com.example.spring_webflux.infrastructure.entrypoints.controller;

import com.example.spring_webflux.domain.entities.model.Flight;
import com.example.spring_webflux.domain.usecase.CreateFlightUseCase;
import com.example.spring_webflux.infrastructure.entrypoints.dto.CreateFlightRequest;
import com.example.spring_webflux.infrastructure.entrypoints.dto.FlightResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class FlightHandler {

    private final CreateFlightUseCase createFlightUseCase;

    public Mono<ServerResponse> createFlights(ServerRequest request) {
        return request.bodyToMono(CreateFlightRequest.class)
                .flatMap(createFlightRequest -> createFlightUseCase.createFlight(
                         createFlightRequest.getFlightNumber(),
                        createFlightRequest.getAirline(),
                        createFlightRequest.getDepartureAirport(),
                        createFlightRequest.getArrivalAirport(),
                        createFlightRequest.getDepartureTime(),
                        createFlightRequest.getArrivalTime(),
                        createFlightRequest.getAircraftType(),
                        createFlightRequest.getCapacity(),
                        createFlightRequest.getPrice()
                ))
                .map(FlightResponse::new)
                .flatMap(response -> ServerResponse.status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(response));
    }
}
