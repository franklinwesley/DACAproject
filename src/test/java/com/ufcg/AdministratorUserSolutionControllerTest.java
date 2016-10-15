package com.ufcg;


import com.google.gson.Gson;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.ufcg.Utils.UserType;
import com.ufcg.Utils.Visibility;
import com.ufcg.models.OutputSolution;
import com.ufcg.models.Problem;
import com.ufcg.models.Solution;
import com.ufcg.models.User;
import com.ufcg.repositories.ProblemRepository;
import com.ufcg.repositories.SolutionRepository;
import com.ufcg.repositories.UserRepository;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static com.jayway.restassured.RestAssured.basic;
import static com.jayway.restassured.RestAssured.given;

@SpringApplicationConfiguration(classes=DacaApplication.class)
@WebIntegrationTest("server.port=0")
@RunWith(SpringJUnit4ClassRunner.class)
public class AdministratorUserSolutionControllerTest {


    @Value("${local.server.port}")
    private int port;
    private String route = "/solution";

    private SolutionRepository solutionRepository;
    private ProblemRepository problemRepository;
    private UserRepository userRepository;
    private Solution solution;
    private Problem problem;
    private User userCreator;
    private List<OutputSolution> outputSolutions;

    @Autowired
    public void setProblemRepository(ProblemRepository problemRepository, UserRepository userRepository,
                                     SolutionRepository solutionRepository) {
        this.problemRepository = problemRepository;
        this.userRepository = userRepository;
        this.solutionRepository = solutionRepository;
    }

    @Before
    public void setUp(){
        String username = "userAdminSolution@gmail.com";
        String password = "2312331";

        userCreator = new User(username, password, UserType.ADMINISTRATOR);
        userRepository.save(userCreator);
        List<com.ufcg.models.Test> testList = new ArrayList<>();
        com.ufcg.models.Test test = new com.ufcg.models.Test("Name test", "Tip test", "Input test", "Output test", Visibility.PUBLIC);
        testList.add(test);
        problem = new Problem(userCreator, "Name problem", "Description problem", "Tip problem", testList, Visibility.PUBLIC);
        problemRepository.save(problem);
        OutputSolution outputSolution = new OutputSolution("Input Solution", "Output solution");
        outputSolutions = new ArrayList<>();
        outputSolutions.add(outputSolution);
        solution = new Solution(userCreator, "Code", problem, outputSolutions);
        solutionRepository.save(solution);

        RestAssured.authentication = basic(username, password);
    }

    @After
    public void after(){
        solutionRepository.deleteAll();
        problemRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void testGetSolutions() throws Exception {
        given()
                .param("problemId", problem.getId())
                .when()
                .port(this.port)
                .get(route)
                .then().assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void testGetSolutionsProblemNotExist() throws Exception {
        given()
                .param("problemId", 191)
                .when()
                .port(this.port)
                .get(route)
                .then().assertThat()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void testGetSolutionByIdSolution() throws Exception {
        given()
                .when()
                .port(this.port)
                .get(route +"/{id}", solution.getId())
                .then().assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void testGetSolutionByIdSolutionNotExist() throws Exception {
        int id = 11;
        given()
                .when()
                .port(this.port)
                .get(route +"/{id}", id)
                .then().assertThat()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void testCreateSolution() throws Exception {

        Solution solution = new Solution(userCreator, "Solution 21", problem, outputSolutions);

        given()
                .contentType(ContentType.JSON)
                .body(new Gson().toJson(solution))
                .when()
                .port(this.port)
                .post(route)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void testUpdateSolution() throws Exception {

        Solution solution2 = new Solution(userCreator, "2202", problem, outputSolutions);

        given()
                .pathParam("id", problem.getId())
                .contentType(ContentType.JSON)
                .body(solution2)
                .when()
                .port(this.port)
                .put(route + "/{id}", solution.getId())
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void testDeleteSolutionExist() throws Exception {
        given()
                .when()
                .port(this.port)
                .delete(route + "/{id}", solution.getId())
                .then()
                .assertThat().statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void testDeleteSolutionNotExist() throws Exception {
        int id = 11;
        given()
                .when()
                .port(this.port)
                .delete(route + "/{id}", id)
                .then()
                .assertThat().statusCode(HttpStatus.SC_NOT_FOUND);
    }

}
