package com.weather.api.weatherapi.converters;

import com.github.prominence.openweathermap.api.model.response.HourlyForecast;
import com.weather.api.weatherapi.models.HourForecast;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WeatherResponseConverter implements Converter<HourlyForecast, List<HourForecast>> {

    @Override
    public List<HourForecast> convert(HourlyForecast hourlyForecast) {
        return hourlyForecast.getForecasts().stream().map(this::createHourForecast).collect(Collectors.toList());
    }

    private HourForecast createHourForecast(HourlyForecast.Forecast forecast) {
        return HourForecast.builder()
                .date(forecast.getDataCalculationDate())
                .pressure(forecast.getWeatherInfo().getPressure())
                .temperature(forecast.getWeatherInfo().getTemperature())
                .build();
    }
}
