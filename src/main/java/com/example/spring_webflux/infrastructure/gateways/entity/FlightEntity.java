package com.example.spring_webflux.infrastructure.gateways.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("flights")
@NoArgsConstructor
@Getter
@Setter
public class FlightEntity {

    @Id
    private Long id;

    @Column("flight_number")
    private String flightNumber;

    @Column("airline")

    private String airline;
    @Column("arrival_airport")
    private String arrivalAirport;

    @Column("departure_airport")
    private String departureAirport;

    @Column("departure_time")
    private LocalDateTime departureTime;

    @Column("arrival_time")
    private LocalDateTime arrivalTime;

    @Column("status")
    private String status;

    @Column("aircraft_type")
    private String aircraftType;

    @Column("capacity")
    private Integer capacity;

    @Column("booked_seats")
    private Integer bookedSeats;

    @Column("price")
    private Double price;

    @Column("gate")
    private String gate;

    @Column("created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column("updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public FlightEntity(String flightNumber, String airline, String arrivalAirport, String departureAirport, LocalDateTime departureTime, LocalDateTime arrivalTime, String status, String aircraftType, Integer capacity, Integer bookedSeats, Double price, String gate, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.arrivalAirport = arrivalAirport;
        this.departureAirport = departureAirport;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.status = status;
        this.aircraftType = aircraftType;
        this.capacity = capacity;
        this.bookedSeats = bookedSeats;
        this.price = price;
        this.gate = gate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
