package com.weather.api.weatherapi.services;

import com.github.prominence.openweathermap.api.exception.DataNotFoundException;
import com.github.prominence.openweathermap.api.exception.InvalidAuthTokenException;
import com.github.prominence.openweathermap.api.model.response.HourlyForecast;
import com.weather.api.weatherapi.client.OpenWeatherClient;
import com.weather.api.weatherapi.converters.HourlyForecastConverter;
import com.weather.api.weatherapi.models.ThreeHourForecast;
import com.weather.api.weatherapi.models.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherService {

    private final HourlyForecastConverter hourlyForecastConverter;
    private final OpenWeatherClient openWeatherClient;

    @Autowired
    public WeatherService(HourlyForecastConverter hourlyForecastConverter, OpenWeatherClient openWeatherClient) {
        this.hourlyForecastConverter = hourlyForecastConverter;
        this.openWeatherClient = openWeatherClient;
    }

    public WeatherResponse fetchAverageTemperaturesByCity(String cityName) throws InvalidAuthTokenException, DataNotFoundException {
        final HourlyForecast forecast = openWeatherClient.getForecastRequester().getByCityName(cityName);

        final List<ThreeHourForecast> threeHourForecasts = hourlyForecastConverter.convert(forecast);

        return WeatherResponse.builder().cityName(cityName).threeHourForecasts(threeHourForecasts).build();
    }

}
