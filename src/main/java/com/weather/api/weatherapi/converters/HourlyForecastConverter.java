package com.weather.api.weatherapi.converters;

import com.github.prominence.openweathermap.api.model.response.HourlyForecast;
import com.weather.api.weatherapi.models.ThreeHourForecast;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HourlyForecastConverter implements Converter<HourlyForecast, List<ThreeHourForecast>> {

    @Override
    public List<ThreeHourForecast> convert(HourlyForecast hourlyForecast) {
        return hourlyForecast.getForecasts().stream().map(this::createHourForecast).collect(Collectors.toList());
    }

    private ThreeHourForecast createHourForecast(HourlyForecast.Forecast forecast) {
        return ThreeHourForecast.builder()
                .date(forecast.getDataCalculationDate())
                .pressure(forecast.getWeatherInfo().getPressure())
                .temperature(forecast.getWeatherInfo().getTemperature())
                .build();
    }
}
