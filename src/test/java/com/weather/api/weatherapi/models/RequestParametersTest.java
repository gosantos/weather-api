package com.weather.api.weatherapi.models;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class RequestParametersTest {
    @Test
    public void shouldReturnTrueWhenNumberOfDaysIsValid() {
        final RequestParameters london = RequestParameters.builder().cityName("Berlin").numberOfDays(2).build();

        assertThat(london.isNumberOfDaysValid(), is(true));
    }

    @Test
    public void shouldReturnFalseWhenNumberOfDaysIsInvalid() {
        final RequestParameters london = new RequestParameters("London", 8);

        assertThat(london.isNumberOfDaysValid(), is(false));
    }

    @Test
    public void shouldReturnTrueWhenDaysAreNotPassed() {
        final RequestParameters london = RequestParameters.builder().cityName("Berlin").build();

        assertThat(london.isNumberOfDaysValid(), is(true));
    }

}