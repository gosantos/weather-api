package com.weather.api.weatherapi.models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class WeatherResponse {
    private List<Long> calculationTimes;
    private List<Float> temperatures;
    private List<Float> pressures;
}
