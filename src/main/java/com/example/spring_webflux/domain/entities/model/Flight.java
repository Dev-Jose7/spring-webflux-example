package com.example.spring_webflux.domain.entities.model;

import com.example.spring_webflux.domain.entities.model.enums.FlightStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class Flight {

    private Long id;
    private String flightNumber;
    private String airline;
    private String departureAirport;
    private String arrivalAirport;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private FlightStatus status;
    private String aircraftType;
    private Integer capacity;
    private Integer bookedSeats;
    private Double price;
    private String gate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructor for creating new flights
    public Flight(String flightNumber, String airline, String departureAirport,
                  String arrivalAirport, LocalDateTime departureTime, LocalDateTime arrivalTime,
                  String aircraftType, Integer capacity, Double price) {
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.aircraftType = aircraftType;
        this.capacity = capacity;
        this.price = price;
        this.status = FlightStatus.SCHEDULED;
        this.bookedSeats = 0;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}