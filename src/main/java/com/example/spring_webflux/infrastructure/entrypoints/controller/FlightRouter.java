package com.example.spring_webflux.infrastructure.entrypoints.controller;

import jdk.jfr.ContentType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.contentType;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class FlightRouter {

    @Bean
    public RouterFunction<ServerResponse> flightRoutes(FlightHandler flightHandler) {
        return route()
                .POST("/api/v1/flights",
                        accept(MediaType.APPLICATION_JSON)
                                .and(contentType(MediaType.APPLICATION_JSON)),
                        flightHandler::createFlights
                ).build();
    }
}
