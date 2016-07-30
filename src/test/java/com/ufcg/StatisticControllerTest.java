package com.ufcg;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@SpringApplicationConfiguration(classes=DacaApplication.class)
@WebIntegrationTest("server.port=0")
@RunWith(SpringJUnit4ClassRunner.class)
public class StatisticControllerTest {

    @Value("${local.server.port}")
    private int port;
    private String route = "/";

    @Test
    public void testGetSolutions() throws Exception {
        int id = 1;
        given()
                .param("userId", id)
                .when()
                .port(this.port)
                .get(route)
                .then().assertThat()
                .statusCode(is(200));
    }

}
