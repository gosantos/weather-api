package com.weather.api.weatherapi;

import com.weather.api.weatherapi.controllers.WeatherController;
import com.weather.api.weatherapi.services.WeatherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {WeatherController.class})
public class APITests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherService weatherService;

    @Test
    public void shouldReturn2xxWhenUpdatingTheGame() throws Exception {

        mockMvc.perform(get("/data?cityName=Berlin&numberOfDays=3")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is2xxSuccessful());
    }
}
