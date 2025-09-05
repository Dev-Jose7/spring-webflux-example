package com.example.spring_webflux.domain.entities.model;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class Weather {

    private String location;
    private String condition;
    private Double temperature;
    private Double windSpeed;
    private String windDirection;
    private Double visibility;
    private String description;
    private boolean flightSafe;

    public Weather(String location, String condition, Double temperature, Double windSpeed, String windDirection, Double visibility, String description) {
        this.location = location;
        this.condition = condition;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.visibility = visibility;
        this.description = description;
        this.flightSafe = determinateFlightSafety();
    }

    private Boolean determinateFlightSafety() {
        if(windSpeed != null && windSpeed > 50) return false;
        if(visibility != null && visibility < 1.0) return false;
        if(condition != null && (condition.toLowerCase().contains("storm") ||
                condition.toLowerCase().contains("severe"))) return false;

        return true;
    }
}
