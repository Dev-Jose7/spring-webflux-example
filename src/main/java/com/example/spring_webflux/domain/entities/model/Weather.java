package com.example.spring_webflux.domain.entities.model;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Weather {

    public Weather(String location, String condition, String description, String temperature, Double windSpeed, Double windDirection, Double visibility) {
        this.location = location;
        this.condition = condition;
        this.description = description;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.visibility = visibility;
        this.flightSafe = determinateFlightSafety();
    }

    private String location;
    private String condition;
    private String description;
    private String temperature;
    private Double windSpeed;
    private Double windDirection;
    private Double visibility;
    private boolean flightSafe;

    private boolean determinateFlightSafety() {
        if (windSpeed != null && windSpeed > 50) return false;
        if (visibility != null && visibility < 1.0) return false;
        if (condition != null &&
                (condition.toLowerCase().contains("storm") || condition.toLowerCase().contains("severe"))) return false;

        return true;
    }
}
