package com.example.spring_webflux.domain.usecase;

import com.example.spring_webflux.domain.entities.model.Flight;
import com.example.spring_webflux.domain.entities.port.FlightGateway;
import com.example.spring_webflux.domain.entities.port.WeatherGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class CreateFlightUseCase {

    private final FlightGateway flightGateway;
    private final WeatherGateway weatherGateway;

    public Mono<Flight> createFlight(String flightNumber, String airline, String departureAirport,
                                     String arrivalAirport, LocalDateTime departureTime, LocalDateTime arrivalTime,
                                     String aircraftType, Integer capacity, Double price) {
        return flightGateway.existsByFlightNumber(flightNumber)
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new RuntimeException());
                    }

                    return validateWeatherCondition(departureAirport, arrivalAirport)
                            .then(Mono.defer(() -> {
                                Flight newFlight = new Flight(flightNumber, airline, departureAirport, arrivalAirport, departureTime, arrivalTime, aircraftType, capacity, price);
                                return flightGateway.createFlight(newFlight);
                            }));
                });
    }

    public Mono<Void> validateWeatherCondition(String departureAirport, String arrivalAirport) {
        return Mono.zip(
                weatherGateway.isWeatherSafeForFlight(departureAirport),
                weatherGateway.isWeatherSafeForFlight(arrivalAirport))
                .flatMap(tuple -> {
                    boolean departureWeatherSafe = tuple.getT1();
                    boolean arrivalWeatherSafe = tuple.getT2();

                    if (!departureWeatherSafe || !arrivalWeatherSafe) {
                        return Mono.error(new RuntimeException("Weather conditions are not satisfied."));
                    }

                    return Mono.empty();
                });
    }
}