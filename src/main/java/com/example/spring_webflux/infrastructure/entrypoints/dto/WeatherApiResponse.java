package com.example.spring_webflux.infrastructure.entrypoints.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor
public class WeatherApiResponse {

    private LocationDto location;

    private CurrentWeatherDto current;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Getter
    @Setter
    public static class LocationDto {

        @JsonProperty("name")
        private String name;
        @JsonProperty("region")
        private String region;
        @JsonProperty("country")
        private String country;

    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Getter
    @Setter
    public static class CurrentWeatherDto {
        @JsonProperty("temp_c")
        private Double temperatureCelsius;

        @JsonProperty("condition")
        private ConditionDto condition;

        @JsonProperty("wind_kph")
        private Double windSpeedKph;

        @JsonProperty("wind_dir")
        private String windDirection;

        @JsonProperty("vis_km")
        private Double visibilityKm;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Getter
    @Setter
    public static class ConditionDto {

        @JsonProperty("text")
        private String text;

        @JsonProperty("code")
        private Integer code;
    }
}
