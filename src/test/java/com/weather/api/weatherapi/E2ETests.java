package com.weather.api.weatherapi;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
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
                .when().log().all()
                .queryParams("cityName", "London", "numberOfDays", "3")
                .get(getUrl("data"))
                .then().log().all()
                .statusCode(is(200));
    }

    private String getUrl(String endpoint) {
        return String.format("http://localhost:%s/%s", port, endpoint);
    }
}
