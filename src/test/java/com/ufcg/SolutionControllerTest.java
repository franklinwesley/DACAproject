package com.ufcg;

import com.jayway.restassured.http.ContentType;
import com.ufcg.models.Problem;
import com.ufcg.models.Solution;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@SpringApplicationConfiguration(classes=DacaApplication.class)
@WebIntegrationTest("server.port=0")
@RunWith(SpringJUnit4ClassRunner.class)
public class SolutionControllerTest {
    @Value("${local.server.port}")
    private int port;
    private String route = "/solution";

    @Test
    public void testGetSolutions() throws Exception {
        int id = 1;
        given()
                .param("problemId", id)
                .when()
                .port(this.port)
                .get(route)
                .then().assertThat()
                .statusCode(is(200));
    }

    @Test
    public void testGetSolutionById() throws Exception {
        int id = 11;
        given()
                .when()
                .port(this.port)
                .get(route +"/" + id)
                .then().assertThat()
                .statusCode(200);
    }

    @Test
    public void testCreateSolution() throws Exception {
        Map<String, String> inputs = new HashMap<>();
        Solution solution = new Solution("2202", new Problem(), inputs);

        given()
                .accept(ContentType.JSON)
                .body(solution)
                .when()
                .port(this.port)
                .post(route)
                .then()
                .assertThat()
                .statusCode(415);
    }

    @Test
    public void testUpdateSolution() throws Exception {
        Map<String, String> inputs = new HashMap<>();
        Solution solution = new Solution("2202", new Problem(), inputs);
        int id = 11;

        given()
                .accept(ContentType.JSON)
                .body(solution)
                .when()
                .port(this.port)
                .put(route + "/" + id)
                .then()
                .assertThat()
                .statusCode(415);
    }

    @Test
    public void testDeleteSolution() throws Exception {
        int id = 11;
        given()
                .when()
                .port(this.port)
                .delete(route + "/" + id)
                .then()
                .assertThat().statusCode(204);
    }
}
