package com.example.spring_webflux.infrastructure.gateways.external;

import com.example.spring_webflux.domain.entities.model.Weather;
import com.example.spring_webflux.domain.entities.port.WeatherGateway;
import com.example.spring_webflux.infrastructure.entrypoints.dto.WeatherApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.Map;

@Service
public class WeatherGatewayImpl implements WeatherGateway {

    private static final Logger logger = LoggerFactory.getLogger(WeatherGatewayImpl.class);

    private final WebClient webClient;
    private final String apiKey;
    private final String baseUrl;

    private static final Map<String, String> AIRPORT_TO_CITY = Map.of(
            "JFK", "New York",
            "LAX", "Los Angeles",
            "MIA", "Miami",
            "ATL", "Atlanta"
    );

    public WeatherGatewayImpl(WebClient.Builder webClientBuilder,
                               @Value("${weather.api.key}") String apiKey,
                               @Value("${weather.api.baseUrl}") String baseUrl) {

        this.webClient = webClientBuilder
                .baseUrl(baseUrl)
                .build();

        this.apiKey = apiKey;
        this.baseUrl = baseUrl;
    }

    @Override
    public Mono<Weather> getCurrentWeather(String airportCode) {
        String city = AIRPORT_TO_CITY.getOrDefault(airportCode.toUpperCase(), airportCode);
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/current.json")
                        .queryParam("key", apiKey)
                        .queryParam("q", city)
                        .queryParam("aqi", "no")
                        .build())
                .retrieve()
                .bodyToMono(WeatherApiResponse.class)
                .map(response -> mapToWeather(response, airportCode))
                .retryWhen(Retry.backoff(3, Duration.ofSeconds(1)))
                .onErrorResume(throwable -> {
                    logger.error("Failed to fetch weather for airport: {}. Using fallback data Error: {}", airportCode,
                            throwable.getMessage());
                    return Mono.error(throwable);
                })
                .doOnNext(weatherResponse -> { logger.info("Successfully fetched weather for airport: {}", airportCode); })
                .timeout(Duration.ofSeconds(10));
    }

    private Weather mapToWeather(WeatherApiResponse response, String airportCode) {

        WeatherApiResponse.CurrentWeatherDto current = response.getCurrent();
        String condition = current.getCondition() != null ? current.getCondition().getText(): "Unknown";

        return new Weather(
                airportCode,
                condition,
                current.getTemperatureCelsius(),
                current.getWindSpeedKph(),
                current.getWindDirection(),
                current.getVisibilityKm(),
                condition
        );
    }

    @Override
    public Mono<Boolean> isWeatherSafeForFlight(String airportCode) {
        return getCurrentWeather(airportCode)
                .map(Weather::isFlightSafe)
                .onErrorReturn(true);
    }
}
