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
    private static final float THREE_DAYS_TIME_FRAME = 24F;

    @JsonIgnore
    private List<ThreeHourForecast> threeHourForecasts;

    private String cityName;

    @JsonProperty(value = "dayAverageTemperature")
    public float calculateDayAverageTemperature() {
        return threeHourForecasts.stream()
                .filter(ThreeHourForecast::isDay)
                .map(ThreeHourForecast::getTemperature)
                .reduce(Float::sum)
                .orElse(DEFAULT_VALUE) / THREE_DAYS_TIME_FRAME;
    }

    @JsonProperty(value = "nightAverageTemperature")
    public float calculateNightAverageTemperature() {
        return threeHourForecasts.stream()
                .filter(ThreeHourForecast::isNight)
                .map(ThreeHourForecast::getTemperature)
                .reduce(Float::sum)
                .orElse(DEFAULT_VALUE) / THREE_DAYS_TIME_FRAME;
    }

    @JsonProperty(value = "averagePressure")
    public float calculateAveragePressure() {
        return getThreeHourForecastsTheForFirstThreeDays().stream()
                .map(ThreeHourForecast::getPressure)
                .reduce(Float::sum)
                .orElse(DEFAULT_VALUE) / THREE_DAYS_TIME_FRAME;
    }

    private List<ThreeHourForecast> getThreeHourForecastsTheForFirstThreeDays() {
        return threeHourForecasts.subList(0, (int) THREE_DAYS_TIME_FRAME);
    }
}
