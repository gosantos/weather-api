package com.weather.api.weatherapi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.AssertTrue;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestParameters {
    private static final int MIN_DAYS = 1;
    private static final int MAX_DAYS = 5;
    private static final int DEFAULT_VALUE = 3;

    private String cityName;

    @Builder.Default
    private int numberOfDays = DEFAULT_VALUE;

    @AssertTrue
    boolean isNumberOfDaysValid() {
        return numberOfDays >= MIN_DAYS && numberOfDays <= MAX_DAYS;
    }
}

