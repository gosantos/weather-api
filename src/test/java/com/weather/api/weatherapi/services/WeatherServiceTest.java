package com.weather.api.weatherapi.services;

import com.github.prominence.openweathermap.api.HourlyForecastRequester;
import com.github.prominence.openweathermap.api.exception.DataNotFoundException;
import com.github.prominence.openweathermap.api.exception.InvalidAuthTokenException;
import com.github.prominence.openweathermap.api.model.response.HourlyForecast;
import com.weather.api.weatherapi.client.OpenWeatherClient;
import com.weather.api.weatherapi.converters.HourlyForecastConverter;
import com.weather.api.weatherapi.models.ThreeHourForecast;
import com.weather.api.weatherapi.models.WeatherResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class WeatherServiceTest {

    private WeatherService weatherService;

    @Mock
    private HourlyForecastConverter hourlyForecastConverter;
    @Mock
    private OpenWeatherClient openWeatherClient;

    @Before
    public void setUp() {
        initMocks(this);
        weatherService = new WeatherService(hourlyForecastConverter, openWeatherClient);
    }

    @Test
    public void fetchAverageTemperaturesByCity() throws InvalidAuthTokenException, DataNotFoundException {
        HourlyForecastRequester forecastRequester = mock(HourlyForecastRequester.class);

        when(openWeatherClient.getForecastRequester()).thenReturn(forecastRequester);

        HourlyForecast hourlyForecast = mock(HourlyForecast.class);

        when(forecastRequester.getByCityName("Foo")).thenReturn(hourlyForecast);

        final ThreeHourForecast threeHourForecast = ThreeHourForecast.builder().temperature(10F).pressure(10F).date(new Date()).build();
        final List<ThreeHourForecast> threeHourForecasts = Collections.singletonList(threeHourForecast);

        when(hourlyForecastConverter.convert(hourlyForecast)).thenReturn(threeHourForecasts);

        final WeatherResponse weatherResponse = WeatherResponse.builder().cityName("Foo").threeHourForecasts(threeHourForecasts).build();

        assertThat(weatherService.fetchAverageTemperaturesByCity("Foo"), is(weatherResponse));
    }
}