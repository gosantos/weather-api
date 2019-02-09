package com.weather.api.weatherapi.controllers;

import com.github.prominence.openweathermap.api.HourlyForecastRequester;
import com.github.prominence.openweathermap.api.OpenWeatherMapManager;
import com.github.prominence.openweathermap.api.constants.Accuracy;
import com.github.prominence.openweathermap.api.constants.Language;
import com.github.prominence.openweathermap.api.constants.Unit;
import com.github.prominence.openweathermap.api.exception.DataNotFoundException;
import com.github.prominence.openweathermap.api.exception.InvalidAuthTokenException;
import com.github.prominence.openweathermap.api.model.response.HourlyForecast;
import com.weather.api.weatherapi.models.WeatherResponse;
import com.weather.api.weatherapi.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/data/{cityName}")
    public WeatherResponse fetchAverageTemperaturesByCity(@PathVariable("cityName") String cityName) throws InvalidAuthTokenException {
        return weatherService.fetchAverageTemperaturesByCity(cityName);
    }

    @GetMapping("/foo")
    public HourlyForecast foo() throws InvalidAuthTokenException, DataNotFoundException {
        OpenWeatherMapManager openWeatherManager = new OpenWeatherMapManager("7e0d05a35ca278396bfcb7ea3062e5bf");
        HourlyForecastRequester forecastRequester = openWeatherManager.getHourlyForecastRequester();

        return forecastRequester
                .setLanguage(Language.ENGLISH)
                .setUnitSystem(Unit.METRIC_SYSTEM)
                .setAccuracy(Accuracy.ACCURATE)
                .getByCityName("Pruzhany");
    }

}
