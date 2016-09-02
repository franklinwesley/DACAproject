package com.ufcg;

import com.jayway.restassured.http.ContentType;
import com.ufcg.Utils.Visibility;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.empty;

@SpringApplicationConfiguration(classes=DacaApplication.class)
@WebIntegrationTest("server.port=0")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestControllerTest {
    @Value("${local.server.port}")
    private int port;

    @Test
    public void testGetTests() throws Exception {
        int id = 1;
        given()
                .when()
                .port(this.port)
                .get("/problem/"+id+"/test")
                .then().assertThat()
                .statusCode(is(200));
    }

    @Test
    public void testGetTestById() throws Exception {
        int idProblem = 11;
        int idTest = 1;
        given()
                .when()
                .port(this.port)
                .get("/problem/" + idProblem + "/test" +"/" + idTest)
                .then().assertThat()
                .statusCode(200);
    }

    @Test
    public void testCreateTest() throws Exception {
        com.ufcg.models.Test test = new com.ufcg.models.Test("name", "tip", "", "", Visibility.PRIVATE);

        int idProblem = 11;
        given()
                .accept(ContentType.JSON)
                .body(test)
                .when()
                .port(this.port)
                .post("/problem/" + idProblem + "/test")
                .then()
                .assertThat()
                .statusCode(415);
    }

    @Test
    public void testUpdateTest() throws Exception {
        com.ufcg.models.Test test = new com.ufcg.models.Test("name", "tip", "", "", Visibility.PRIVATE);

        int idProblem = 11;
        int idTest = 1;

        given()
                .accept(ContentType.JSON)
                .body(test)
                .when()
                .port(this.port)
                .put("/problem/" + idProblem + "/test" +"/" + idTest)
                .then()
                .assertThat()
                .statusCode(415);
    }

    @Test
    public void testDeleteTest() throws Exception {
        int idProblem = 11;
        int idTest = 1;
        given()
                .when()
                .port(this.port)
                .delete("/problem/" + idProblem + "/test" +"/" + idTest)
                .then()
                .assertThat().statusCode(204);
    }

}
