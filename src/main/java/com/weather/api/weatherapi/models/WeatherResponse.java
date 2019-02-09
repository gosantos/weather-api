package com.weather.api.weatherapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeatherResponse {
    private static final float DEFAULT_VALUE = 0F;
    private static final float NUMBER_OF_DAYS = 24F;

    @JsonIgnore
    private List<HourForecast> hourForecasts;

    private String cityName;

    @JsonProperty(value = "dayAverageTemperature")
    public float calculateDayAverageTemperature() {
        return hourForecasts.stream()
                .filter(HourForecast::isDay)
                .map(HourForecast::getTemperature)
                .reduce(Float::sum)
                .orElse(DEFAULT_VALUE) / NUMBER_OF_DAYS;
    }

    @JsonProperty(value = "nightAverageTemperature")
    public float calculateNightAverageTemperature() {
        return hourForecasts.stream()
                .filter(HourForecast::isNight)
                .map(HourForecast::getTemperature)
                .reduce(Float::sum)
                .orElse(DEFAULT_VALUE) / NUMBER_OF_DAYS;
    }

    @JsonProperty(value = "averagePressure")
    public float calculateAveragePressure() {
        return hourForecasts.stream()
                .map(HourForecast::getPressure)
                .reduce(Float::sum)
                .orElse(0F) / NUMBER_OF_DAYS;
    }
}
