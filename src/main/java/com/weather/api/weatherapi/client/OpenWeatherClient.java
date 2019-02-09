package com.weather.api.weatherapi.client;

import com.github.prominence.openweathermap.api.HourlyForecastRequester;
import com.github.prominence.openweathermap.api.OpenWeatherMapManager;
import com.github.prominence.openweathermap.api.constants.Accuracy;
import com.github.prominence.openweathermap.api.constants.Language;
import com.github.prominence.openweathermap.api.constants.Unit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OpenWeatherClient {

    @Value("${openWeatherApiKey}")
    private String openWeatherApiKey;

    public HourlyForecastRequester getForecastRequester() {
        final OpenWeatherMapManager openWeatherManager = new OpenWeatherMapManager(openWeatherApiKey);
        final HourlyForecastRequester forecastRequester = openWeatherManager.getHourlyForecastRequester();

        return forecastRequester
                .setLanguage(Language.ENGLISH)
                .setUnitSystem(Unit.METRIC_SYSTEM)
                .setAccuracy(Accuracy.ACCURATE);
    }
}
