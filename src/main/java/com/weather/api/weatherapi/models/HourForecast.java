package com.weather.api.weatherapi.models;

import lombok.Builder;
import lombok.Value;

import java.util.Date;

@Builder
@Value
public class HourForecast {
    private static final int MAX_DAILY_HOUR = 18;
    private static final int MIN_DAILY_HOUR = 6;

    private Float temperature;
    private Float pressure;
    private Date date;

    boolean isDay() {
        return (date.getHours() > MIN_DAILY_HOUR) && (date.getHours() < MAX_DAILY_HOUR);
    }

    boolean isNight() {
        return !isDay();
    }
}
