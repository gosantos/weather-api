package com.weather.api.weatherapi.controllers;

import com.github.prominence.openweathermap.api.exception.DataNotFoundException;
import com.github.prominence.openweathermap.api.exception.InvalidAuthTokenException;
import com.weather.api.weatherapi.models.WeatherResponse;
import com.weather.api.weatherapi.services.WeatherService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class WeatherControllerTest {

    private WeatherController weatherController;

    @Mock
    private WeatherService weatherService;

    @Before
    public void setUp() {
        initMocks(this);
        weatherController = new WeatherController(weatherService);
    }

    @Test
    public void givenAnValidCityAndNumberOfDaysShouldReturnAnWeatherResponse() throws InvalidAuthTokenException, DataNotFoundException {
        final String london = "London";

        when(weatherService.fetchAverageTemperaturesByCity(london)).thenReturn(WeatherResponse.builder().build());

        final WeatherResponse weatherResponse = weatherController.fetchAverageTemperaturesByCity(london);

        assertThat(weatherResponse, is(WeatherResponse.builder().build()));
    }
}