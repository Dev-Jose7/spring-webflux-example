package com.example.spring_webflux.application.init;

import io.r2dbc.spi.ConnectionFactory;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class DatabaseInitializer {

    private final ConnectionFactory connectionFactory;

    public DatabaseInitializer(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @PostConstruct
    public void initialize() {
        String ddl = """

        CREATE TABLE IF NOT EXISTS flights (
                id SERIAL PRIMARY KEY,
                flight_number VARCHAR(10),
                airline VARCHAR(100),
                arrival_airport VARCHAR(10),
                departure_airport VARCHAR(10),
                departure_time TIMESTAMP,
                arrival_time TIMESTAMP,
                status VARCHAR(50),
                aircraft_type VARCHAR(50),
                capacity INT,
                booked_seats INT,
                price DECIMAL(10, 2),
                gate VARCHAR(10),
                created_at TIMESTAMP,
                updated_at TIMESTAMP
            )
        """;

        Mono.from(connectionFactory.create())
                .flatMapMany(connection ->
                        Flux.from(connection.createStatement(ddl).execute())
                                .doFinally(signal -> connection.close())
                )
                .subscribe();
    }
}

