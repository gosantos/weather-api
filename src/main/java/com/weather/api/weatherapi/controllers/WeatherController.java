package com.weather.api.weatherapi.controllers;

import com.github.prominence.openweathermap.api.exception.DataNotFoundException;
import com.github.prominence.openweathermap.api.exception.InvalidAuthTokenException;
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
    public WeatherResponse fetchAverageTemperaturesByCity(@PathVariable("cityName") String cityName) throws InvalidAuthTokenException, DataNotFoundException {
        return weatherService.fetchAverageTemperaturesByCity(cityName);
    }
}
