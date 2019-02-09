package com.weather.api.weatherapi.models;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ThreeHourForecastTest {
    @Test
    public void isDayShouldReturnTrueForADailyTime() throws ParseException {
        Date date = createDateFromString("2019-02-14T12:00:00.000+0000");

        final ThreeHourForecast threeHourForecast = ThreeHourForecast.builder().date(date).build();

        assertThat(threeHourForecast.isDay(), is(true));
    }

    @Test
    public void isDayShouldReturnFalseForANonDailyTime() throws ParseException {
        Date date = createDateFromString("2019-02-14T21:00:00.000+0000");

        final ThreeHourForecast threeHourForecast = ThreeHourForecast.builder().date(date).build();

        assertThat(threeHourForecast.isDay(), is(false));
    }

    @Test
    public void isNightShouldReturnTrueForANightlyTime() throws ParseException {
        Date date = createDateFromString("2019-02-14T12:00:00.000+0000");

        final ThreeHourForecast threeHourForecast = ThreeHourForecast.builder().date(date).build();


        assertThat(threeHourForecast.isNight(), is(false));
    }

    @Test
    public void isNightShouldReturnFalseForANonNightlyTime() throws ParseException {
        Date date = createDateFromString("2019-02-14T21:00:00.000+0000");

        final ThreeHourForecast threeHourForecast = ThreeHourForecast.builder().date(date).build();

        assertThat(threeHourForecast.isNight(), is(true));
    }

    private Date createDateFromString(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

        return format.parse(date);
    }
}