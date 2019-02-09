package com.weather.api.weatherapi.services;

import com.github.prominence.openweathermap.api.exception.DataNotFoundException;
import com.github.prominence.openweathermap.api.exception.InvalidAuthTokenException;
import com.github.prominence.openweathermap.api.model.response.HourlyForecast;
import com.weather.api.weatherapi.client.OpenWeatherClient;
import com.weather.api.weatherapi.converters.WeatherResponseConverter;
import com.weather.api.weatherapi.models.HourForecast;
import com.weather.api.weatherapi.models.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherService {

    private final WeatherResponseConverter weatherResponseConverter;
    private final OpenWeatherClient openWeatherClient;

    @Autowired
    public WeatherService(WeatherResponseConverter weatherResponseConverter, OpenWeatherClient openWeatherClient) {
        this.weatherResponseConverter = weatherResponseConverter;
        this.openWeatherClient = openWeatherClient;
    }

    public WeatherResponse fetchAverageTemperaturesByCity(String cityName) throws InvalidAuthTokenException, DataNotFoundException {
        final HourlyForecast forecast = openWeatherClient.getForecastRequester().getByCityName(cityName);

        final List<HourForecast> hourForecasts = weatherResponseConverter.convert(forecast);

        return WeatherResponse.builder().cityName(cityName).hourForecasts(hourForecasts).build();
    }

}
