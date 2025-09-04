package com.example.spring_webflux.domain.entities.port;

import com.example.spring_webflux.domain.entities.model.Weather;
import reactor.core.publisher.Mono;

public interface WeatherGateway {

    Mono<Weather> getCurrentWeather(String airportCode);
    Mono<Boolean> isWeatherSafeForFlight(String airportCode);
}
