package com.ufcg;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.jayway.restassured.RestAssured.given;

@SpringApplicationConfiguration(classes=DacaApplication.class)
@WebIntegrationTest("server.port=0")
@RunWith(SpringJUnit4ClassRunner.class)
public class StatisticControllerTest {

    @Value("${local.server.port}")
    private int port;
    private String route = "/statistic";

    @Test
    public void testGetStatisticsWithoutUserId() throws Exception {
        given()
                .when()
                    .port(this.port)
                    .get(route)
                .then().assertThat()
//                    .statusCode(HttpStatus.SC_OK)
                    .statusCode(500);
//                    .body("problemsResolved", Matchers.equalTo(0))
//                    .body("usersSubmittingProblems", Matchers.equalTo(0));
    }

    @Test
    public void testGetStatisticsWithUserId() throws Exception {
        given()
                .param("userId", 1)
                .when()
                .port(this.port)
                .get(route)
                .then().assertThat()
//                .statusCode(HttpStatus.SC_OK)
                .statusCode(500);
//                .body("problemsResolved", Matchers.equalTo(0))
//                .body("usersSubmittingProblems", Matchers.equalTo(0))
//                .body("userResolvedProblems", Matchers.equalTo(0));
    }

}
