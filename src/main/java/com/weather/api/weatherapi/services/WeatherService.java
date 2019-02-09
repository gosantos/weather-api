package com.weather.api.weatherapi.services;

import com.github.prominence.openweathermap.api.HourlyForecastRequester;
import com.github.prominence.openweathermap.api.OpenWeatherMapManager;
import com.github.prominence.openweathermap.api.constants.Accuracy;
import com.github.prominence.openweathermap.api.constants.Language;
import com.github.prominence.openweathermap.api.constants.Unit;
import com.github.prominence.openweathermap.api.exception.DataNotFoundException;
import com.github.prominence.openweathermap.api.exception.InvalidAuthTokenException;
import com.github.prominence.openweathermap.api.model.response.HourlyForecast;
import com.weather.api.weatherapi.converters.WeatherResponseConverter;
import com.weather.api.weatherapi.exceptions.CityNotFoundException;
import com.weather.api.weatherapi.models.HourForecast;
import com.weather.api.weatherapi.models.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherService {

    private final WeatherResponseConverter weatherResponseConverter;

    @Autowired
    public WeatherService(WeatherResponseConverter weatherResponseConverter) {
        this.weatherResponseConverter = weatherResponseConverter;
    }

    public WeatherResponse fetchAverageTemperaturesByCity(String cityName) throws InvalidAuthTokenException {
        final OpenWeatherMapManager openWeatherManager = new OpenWeatherMapManager("7e0d05a35ca278396bfcb7ea3062e5bf");
        HourlyForecastRequester forecastRequester = openWeatherManager.getHourlyForecastRequester();

        final HourlyForecast forecastResponse;

        try {
            forecastResponse = forecastRequester
                    .setLanguage(Language.ENGLISH)
                    .setUnitSystem(Unit.METRIC_SYSTEM)
                    .setAccuracy(Accuracy.ACCURATE)
                    .getByCityName(cityName);
        } catch (DataNotFoundException e) {
            throw new CityNotFoundException();
        }

        final List<HourForecast> hourForecasts = weatherResponseConverter.convert(forecastResponse);

        return WeatherResponse.builder()
                .cityName(cityName)
                .hourForecasts(hourForecasts)
                .build();
    }

}
