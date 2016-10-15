package com.ufcg;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.ufcg.Utils.UserType;
import com.ufcg.Utils.Visibility;
import com.ufcg.models.Problem;
import com.ufcg.models.Test;
import com.ufcg.models.User;
import com.ufcg.repositories.ProblemRepository;
import com.ufcg.repositories.UserRepository;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.jayway.restassured.RestAssured.basic;
import static com.jayway.restassured.RestAssured.given;

@SpringApplicationConfiguration(classes=DacaApplication.class)
@WebIntegrationTest("server.port=0")
@RunWith(SpringJUnit4ClassRunner.class)
public class AdministratorUserProblemControllerTest {


    @Value("${local.server.port}")
    private int port;
    private String route = "/problem";

    private ProblemRepository problemRepository;
    private UserRepository userRepository;

    private Problem problem1, problem2;

    @Autowired
    public void setProblemRepository(ProblemRepository problemRepository, UserRepository userRepository) {
        this.problemRepository = problemRepository;
        this.userRepository = userRepository;
    }

    @Before
    public void setUp(){

        Random randomNumber = new Random(1000);

        String username = "useradmin"+randomNumber.nextInt()+"@gmail.com";
        String password = "2312331";

        User userTest = new User(username,password, UserType.ADMINISTRATOR);
        userRepository.save(userTest);

        com.ufcg.models.Test problemTest = new com.ufcg.models.Test("problemTest", "Use Scanner object",
                "oi", "oi", Visibility.PUBLIC);
        List<Test> problemtests = new ArrayList<>();
        problemtests.add(problemTest);

        problem1 = new Problem(userTest, "Print input", "Print the program input",
                "Use Scanner object", problemtests, Visibility.PUBLIC);
        problemRepository.save(problem1);

        com.ufcg.models.Test problemTest2 = new com.ufcg.models.Test("problemTest 2", "Use Scanner object",
                "oi", "oi", Visibility.PUBLIC);
        List<com.ufcg.models.Test> testList = new ArrayList<>();
        testList.add(problemTest2);

        problem2 = new Problem(userTest, "Print input 2", "Print the program input",
                "Use Scanner object", testList, Visibility.PUBLIC);
        problemRepository.save(problem2);

        RestAssured.authentication = basic(username, password);
    }

    @After
    public void after(){
        problemRepository.deleteAll();
        userRepository.deleteAll();
    }

    @org.junit.Test
    public void testGetProblems() throws Exception {
        given()
                .contentType(ContentType.JSON)
                .when()
                .port(this.port)
                .get(route)
                .then().assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("numberOfElements", Matchers.equalTo(2));
    }

    @org.junit.Test
    public void testGetProblemsWithParamsNoUser() throws Exception {
        given()
                .contentType(ContentType.JSON)
                .param("page", 1)
                .param("size", 10)
                .param("sort", "date")
                .when()
                .port(this.port)
                .get(route)
                .then().assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("numberOfElements", Matchers.equalTo(2));
    }

    @org.junit.Test
    public void testGetProblemsWithParamsAndUser() throws Exception {
        given()
                .contentType(ContentType.JSON)
                .param("page", 1)
                .param("size", 10)
                .param("sort", "date")
                .param("user", 1)
                .when()
                .port(this.port)
                .get(route)
                .then().assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("numberOfElements", Matchers.equalTo(2));
    }

    @org.junit.Test
    public void testGetProblemById() throws Exception {
        given()
                .when()
                .port(this.port)
                .get(route +"/" + problem1.getId())
                .then().assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("id", Matchers.equalTo(problem1.getId().intValue()));
    }

    @org.junit.Test
    public void testCreateProblem() throws Exception {
        List<com.ufcg.models.Test> tests = new ArrayList<>();
        User user = new User("usertest099@gmail.com", "1234567", UserType.ADMINISTRATOR);
        userRepository.save(user);
        Problem problem = new Problem(user, "name Problem", "description","tip", tests, Visibility.PUBLIC);
        given()
                .contentType(ContentType.JSON)
                .body(problem)
                .when()
                .port(this.port)
                .post(route)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED);
    }

    @org.junit.Test
    public void testUpdateProblem() throws Exception {
        List<com.ufcg.models.Test> tests = new ArrayList<>();
        Problem problem = new Problem(new User(), "name", "description","tip", tests, Visibility.PUBLIC);
        int id = 112;

        given()
                .contentType(ContentType.JSON)
                .body(problem)
                .when()
                .port(this.port)
                .put(route + "/" + id)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @org.junit.Test
    public void testDeleteProblem() throws Exception {
        given()
                .when()
                .port(this.port)
                .delete(route + "/{id}", problem2.getId())
                .then()
                .assertThat().statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @org.junit.Test
    public void testPublishProblem() throws Exception {
        given()
                .when()
                .port(this.port)
                .patch(route + "/{id}",problem1.getId())
                .then()
                .assertThat().statusCode(HttpStatus.SC_OK);
    }

}
