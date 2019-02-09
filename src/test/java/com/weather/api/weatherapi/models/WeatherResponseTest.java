package com.weather.api.weatherapi.models;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WeatherResponseTest {
    @Test
    public void shouldReturnTheAverageTemperatureForTheNext3Days() throws ParseException {
        final WeatherResponse weatherResponse = generateForecastMocksForDailyTests();

//        assertThat(weatherResponse.calculateDailyAverageTemperature(), is(10F));
    }

    private WeatherResponse generateForecastMocksForDailyTests() throws ParseException {
        List<HourForecast> hourForecasts = new ArrayList<>();

        for (int i = 0; i < 24; i++) {
            HourForecast hourForecast = HourForecast.builder()
                    .date(createDateFromString("2019-02-14T12:00:00.000+0000"))
                    .pressure(10F)
                    .temperature(10F)
                    .build();
            hourForecasts.add(hourForecast);
        }

        return WeatherResponse.builder().hourForecasts(hourForecasts).build();
    }

    private Date createDateFromString(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

        return format.parse(date);
    }
}