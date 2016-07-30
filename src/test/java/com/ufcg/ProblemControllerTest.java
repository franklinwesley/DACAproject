package com.ufcg;

import com.jayway.restassured.http.ContentType;
import com.ufcg.Utils.Visibility;
import com.ufcg.models.Problem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;

@SpringApplicationConfiguration(classes=DacaApplication.class)
@WebIntegrationTest("server.port=0")
@RunWith(SpringJUnit4ClassRunner.class)
public class ProblemControllerTest {

    @Value("${local.server.port}")
    private int port;
    private String route = "/problem";

    @Test
    public void testGetProblems() throws Exception {
        given()
                .accept(ContentType.JSON)
                .when()
                .port(this.port)
                .get(route)
                .then().assertThat()
                .statusCode(is(200))
                .body("", empty());
    }

    @Test
    public void testGetProblemById() throws Exception {
        int id = 1;
        given()
                .accept(ContentType.JSON)
                .when()
                .port(this.port)
                .get(route +"/" + id)
                .then().assertThat()
                .statusCode(is(200))
                .body("id", equalTo(1));
    }

    @Test
    public void testCreateProblem() throws Exception {
        List<com.ufcg.models.Test> tests = new ArrayList<>();
        Problem problem = new Problem(11L, 2L, "name", "description","tip", tests, Visibility.PUBLIC);
        given()
                .accept(ContentType.JSON)
                .body(problem)
                .when()
                .port(this.port)
                .post(route)
                .then()
                .assertThat().statusCode(is(HttpStatus.CREATED));
    }

    @Test
    public void testUpdateProblem() throws Exception {
        List<com.ufcg.models.Test> tests = new ArrayList<>();
        Problem problem = new Problem(11L, 2L, "name", "description","tip", tests, Visibility.PUBLIC);
        int id = 1;

        given()
                .accept(ContentType.JSON)
                .body(problem)
                .when()
                .port(this.port)
                .put(route + "/" + id)
                .then()
                .assertThat().statusCode(is(200));
    }

    @Test
    public void testDeleteProblem() throws Exception {
        int id = 1;
        given()
                .when()
                .port(this.port)
                .delete(route + "/" + id)
                .then()
                .assertThat().statusCode(is(HttpStatus.NO_CONTENT));
    }

    @Test
    public void testPublishProblem() throws Exception {
        int id = 1;
        given()
                .when()
                .port(this.port)
                .patch(route + "/" + id)
                .then()
                .assertThat().statusCode(is(HttpStatus.OK));
    }
}
