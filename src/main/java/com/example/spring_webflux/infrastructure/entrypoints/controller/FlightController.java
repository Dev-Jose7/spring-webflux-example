package com.example.spring_webflux.infrastructure.entrypoints.controller;

import com.example.spring_webflux.domain.entities.model.Flight;
import com.example.spring_webflux.domain.usecase.CreateFlightUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/flights")
public class FlightController {

    private final CreateFlightUseCase createFlightUseCase;

    public FlightController(CreateFlightUseCase createFlightUseCase) {
        this.createFlightUseCase = createFlightUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Flight> createFlight(@RequestBody Flight flight) {
        return createFlightUseCase.createFlight(flight.getFlightNumber(), flight.getAirline(), flight.getDepartureAirport()
                ,flight.getArrivalAirport(), flight.getDepartureTime(), flight.getArrivalTime(), flight.getAircraftType(),
                flight.getCapacity(), flight.getPrice());
    }
}
