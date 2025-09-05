package com.example.spring_webflux.infrastructure.entrypoints.dto;

import com.example.spring_webflux.domain.entities.model.Flight;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FlightResponse {
    private Long  id;
    private String flightNumber;
    private String departureAirport;
    private String arrivalAirport;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private String status;
    private String aircraftType;
    private Integer capacity;
    private Integer bookedSeats;
    private Double price;
    private String gate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public FlightResponse(Flight flight) {
        this.id = flight.getId();
        this.flightNumber = flight.getFlightNumber();
        this.departureAirport = flight.getDepartureAirport();
        this.arrivalAirport = flight.getArrivalAirport();
        this.departureTime = flight.getDepartureTime();
        this.arrivalTime = flight.getArrivalTime();
        this.status = flight.getStatus().name();
        this.aircraftType = flight.getAircraftType();
        this.capacity = flight.getCapacity();
        this.bookedSeats = flight.getBookedSeats();
        this.price = flight.getPrice();
        this.gate = flight.getGate();
        this.createdAt = flight.getCreatedAt();
        this.updatedAt = flight.getUpdatedAt();
    }
}
