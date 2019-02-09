package com.weather.api.weatherapi;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class E2ETests {
    @LocalServerPort
    private int port;

    @Test
    public void shouldReturn2xxWhenDataEndpointIsHit() {
        given()
                .header("Content-Type", "application/json")
                .when()
                .get(getUrl("data/london"))
                .then()
                .statusCode(is(200))
                .body("$", hasKey("nightAverageTemperature"))
                .body("$", hasKey("nightAverageTemperature"))
                .body("$", hasKey("averagePressure"))
                .body("$", hasEntry("cityName", "london"));
    }

    @Test
    public void shouldReturn5xxWhenDataEndpointIsHitWithAnInvalidCity() {
        given()
                .header("Content-Type", "application/json")
                .when()
                .get(getUrl("data/foobarbaz"))
                .then()
                .statusCode(is(500));
    }

    private String getUrl(String endpoint) {
        return String.format("http://localhost:%s/%s", port, endpoint);
    }
}
