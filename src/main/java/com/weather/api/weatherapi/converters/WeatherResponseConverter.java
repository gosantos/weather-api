package com.weather.api.weatherapi.converters;

import com.github.prominence.openweathermap.api.model.response.HourlyForecast;
import com.weather.api.weatherapi.models.WeatherResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WeatherResponseConverter implements Converter<HourlyForecast, WeatherResponse> {

    @Override
    public WeatherResponse convert(HourlyForecast source) {
        final List<Float> temperatures = source.getForecasts().stream()
                .map(HourlyForecast.Forecast::getWeatherInfo)
                .map(HourlyForecast.Forecast.WeatherInfo::getTemperature)
                .collect(Collectors.toList());

        final List<Float> pressures = source.getForecasts().stream()
                .map(HourlyForecast.Forecast::getWeatherInfo)
                .map(HourlyForecast.Forecast.WeatherInfo::getPressure)
                .collect(Collectors.toList());

        final List<Long> calculationTimes = source.getForecasts().stream()
                .map(HourlyForecast.Forecast::getDataCalculationTime)
                .collect(Collectors.toList());

        return WeatherResponse
                .builder()
                .calculationTimes(calculationTimes)
                .pressures(pressures)
                .temperatures(temperatures)
                .build();
    }
}
