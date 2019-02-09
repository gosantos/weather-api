package com.weather.api.weatherapi.client;

import com.github.prominence.openweathermap.api.HourlyForecastRequester;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

public class OpenWeatherClientTest {

    private OpenWeatherClient openWeatherClient;

    @Before
    public void setUp(){
        initMocks(this);
        openWeatherClient = new OpenWeatherClient();
    }

    @Test
    public void getForecastRequesterShouldReturnAnInstanceOfHourlyForecastRequester() {
        assertThat(openWeatherClient.getForecastRequester(), is(instanceOf(HourlyForecastRequester.class)));
    }
}