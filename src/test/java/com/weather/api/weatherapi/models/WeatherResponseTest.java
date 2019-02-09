package com.weather.api.weatherapi.models;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class WeatherResponseTest {
    @Test
    public void shouldReturnDayAverageTemperatureForTheNext3Days() throws ParseException {
        final WeatherResponse weatherResponse = generateForecastMocksForDailyTests();

        assertThat(weatherResponse.calculateDayAverageTemperature(), is(10F));
    }

    @Test
    public void shouldReturnAveragePressureForTheNext3Days() throws ParseException {
        final WeatherResponse weatherResponse = generateForecastMocksForDailyTests();

        assertThat(weatherResponse.calculateAveragePressure(), is(7F));
    }

    @Test
    public void shouldReturnNightAverageTemperatureForTheNext3Days() throws ParseException {
        final WeatherResponse weatherResponse = generateForecastMocksForDailyTests();

        assertThat(weatherResponse.calculateNightAverageTemperature(), is(0F));
    }

    private WeatherResponse generateForecastMocksForDailyTests() throws ParseException {
        List<ThreeHourForecast> threeHourForecasts = new ArrayList<>();

        for (int i = 0; i < 24; i++) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

            ThreeHourForecast threeHourForecast = ThreeHourForecast.builder()
                    .date(format.parse("2019-02-14T12:00:00.000+0000"))
                    .pressure(7F)
                    .temperature(10F)
                    .build();
            threeHourForecasts.add(threeHourForecast);
        }

        return WeatherResponse.builder().threeHourForecasts(threeHourForecasts).build();
    }
}