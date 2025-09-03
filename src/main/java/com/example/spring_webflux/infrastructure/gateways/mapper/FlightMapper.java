package com.example.spring_webflux.infrastructure.gateways.mapper;

import com.example.spring_webflux.domain.entities.model.Flight;
import com.example.spring_webflux.domain.entities.model.enums.FlightStatus;
import com.example.spring_webflux.infrastructure.gateways.entity.FlightEntity;
import org.springframework.stereotype.Component;

@Component
public class FlightMapper {

    // Convert domain Flight to FlightEntity

    public FlightEntity toEntity(Flight flight) {
        if(flight == null) {
            return null;
        }
        FlightEntity entity = new FlightEntity();
        entity.setId(flight.getId());
        entity.setFlightNumber(flight.getFlightNumber());
        entity.setAirline(flight.getAirline());
        entity.setDepartureAirport(flight.getDepartureAirport());
        entity.setArrivalAirport(flight.getArrivalAirport());
        entity.setDepartureTime(flight.getDepartureTime());
        entity.setArrivalTime(flight.getArrivalTime());
        entity.setStatus(flight.getStatus().name());
        entity.setAircraftType(flight.getAircraftType());
        entity.setCapacity(flight.getCapacity());
        entity.setPrice(flight.getPrice());
        entity.setBookedSeats(flight.getBookedSeats());
        entity.setGate(flight.getGate());
        entity.setCreatedAt(flight.getCreatedAt());
        entity.setUpdatedAt(flight.getUpdatedAt());

        return entity;

    }

    // Convert FlightEntity to Flight

    public Flight toFlight(FlightEntity flightEntity) {
        if(flightEntity == null) {
            return null;
        }

        FlightStatus status = FlightStatus.valueOf(flightEntity.getStatus());

        Flight flight = new Flight();
        flight.setId(flightEntity.getId());
        flight.setFlightNumber(flightEntity.getFlightNumber());
        flight.setAirline(flightEntity.getAirline());
        flight.setDepartureAirport(flightEntity.getDepartureAirport());
        flight.setArrivalAirport(flightEntity.getArrivalAirport());
        flight.setDepartureTime(flightEntity.getDepartureTime());
        flight.setArrivalTime(flightEntity.getArrivalTime());
        flight.setStatus(status);
        flight.setAircraftType(flightEntity.getAircraftType());
        flight.setCapacity(flightEntity.getCapacity());
        flight.setPrice(flightEntity.getPrice());
        flight.setBookedSeats(flightEntity.getBookedSeats());
        flight.setGate(flightEntity.getGate());
        flight.setCreatedAt(flightEntity.getCreatedAt());
        flight.setUpdatedAt(flightEntity.getUpdatedAt());
        return flight;
    }
}
