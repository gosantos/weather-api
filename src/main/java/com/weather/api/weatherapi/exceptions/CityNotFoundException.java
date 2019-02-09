package com.weather.api.weatherapi.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, code = HttpStatus.NOT_FOUND, reason = "City not found")
public class CityNotFoundException extends RuntimeException {
    public CityNotFoundException() {
        super("City not found");
    }
}
