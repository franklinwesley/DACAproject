package com.ufcg;

import com.jayway.restassured.http.ContentType;
import com.ufcg.Utils.UserType;
import com.ufcg.Utils.Visibility;
import com.ufcg.models.Problem;
import com.ufcg.models.Solution;
import com.ufcg.models.User;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void testGetSolutionById() throws Exception {
        int id = 11;
        given()
                .when()
                .port(this.port)
                .get(route +"/" + id)
                .then().assertThat()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void testCreateSolution() throws Exception {

        List<com.ufcg.models.Test> testList = new ArrayList<>();

        com.ufcg.models.Test test = new com.ufcg.models.Test("name", "tip", "", "", Visibility.PRIVATE);
        User userCreator = new User("usercreator@gmail.com", "1oi2io1n", UserType.ADMINISTRATOR);

        testList.add(test);

        Problem problem = new Problem(userCreator, "Problem 1", "Problem about the problems", "Good tip",testList,Visibility.PUBLIC);


        Solution solution = new Solution(new User(), "2202", problem, testList);

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

        List<com.ufcg.models.Test> testList = new ArrayList<>();

        com.ufcg.models.Test test = new com.ufcg.models.Test("name", "tip", "", "", Visibility.PRIVATE);
        User userCreator = new User("usercreator@gmail.com", "1oi2io1n", UserType.ADMINISTRATOR);

        testList.add(test);

        Problem problem = new Problem(userCreator, "Problem 1", "Problem about the problems", "Good tip",testList,Visibility.PUBLIC);
        Solution solution = new Solution(new User(), "2202", problem, testList);
        int id = 11;

        given()
                .contentType(ContentType.JSON)
                .body(solution)
                .when()
                .port(this.port)
                .put(route + "/" + id)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void testDeleteSolution() throws Exception {
        int id = 11;
        given()
                .when()
                .port(this.port)
                .delete(route + "/" + id)
                .then()
                .assertThat().statusCode(HttpStatus.SC_NOT_FOUND);
    }
}
