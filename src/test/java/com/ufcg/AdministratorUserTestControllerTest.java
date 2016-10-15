package com.ufcg;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.ufcg.Utils.UserType;
import com.ufcg.Utils.Visibility;
import com.ufcg.models.Problem;
import com.ufcg.models.Test;
import com.ufcg.models.User;
import com.ufcg.repositories.ProblemRepository;
import com.ufcg.repositories.SolutionRepository;
import com.ufcg.repositories.UserRepository;
import org.apache.http.HttpStatus;
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

import static com.jayway.restassured.RestAssured.basic;
import static com.jayway.restassured.RestAssured.given;

@SpringApplicationConfiguration(classes=DacaApplication.class)
@WebIntegrationTest("server.port=0")
@RunWith(SpringJUnit4ClassRunner.class)
public class AdministratorUserTestControllerTest {
    @Value("${local.server.port}")
    private int port;
    private ProblemRepository problemRepository;
    private UserRepository userRepository;

    private Problem problem;
    private String[] testName;
    private String[] testTip;
    private String[] testInput;
    private String[] testOutput;
    private List<Test> testList;
    private int idProblemNotExist = 12012;
    private int idTestNotExist = 11212;
    private int idTestExist = 1;
    private SolutionRepository solutionRepository;

    @Autowired
    public void setProblemRepository(ProblemRepository problemRepository, UserRepository userRepository,
                                     SolutionRepository solutionRepository) {
        this.problemRepository = problemRepository;
        this.userRepository = userRepository;
        this.solutionRepository = solutionRepository;
    }

    @Before
    public void setup(){
        String username = "useradmintests@gmail.com";
        String password = "2312331";

        User userTest = new User(username,password, UserType.ADMINISTRATOR);
        userRepository.save(userTest);

        testList = new ArrayList<>();

        testName = new String[5];
        testTip = new String[5];
        testInput = new String[5];
        testOutput = new String[5];
        for (int i = 0; i < 5; i++) {
            testList.add(new com.ufcg.models.Test("Test " + i, "Bad tip", "Inputs", "Output", Visibility.PUBLIC));
            testName[i] = "Test g " + i;
            testTip[i] = "Bad tip";
            testInput[i] = "Inputs";
            testOutput[i] = "Output";
        }
        problem = new Problem(userTest, "Problem 1", "Problem about the problems", "Good tip",testList,Visibility.PUBLIC);
        problemRepository.save(problem);

        RestAssured.authentication = basic(username, password);
    }

    @After
    public void setdown() {
        solutionRepository.deleteAll();
        problemRepository.deleteAll();
        userRepository.deleteAll();
    }

    @org.junit.Test
    public void testGetTests() throws Exception {
        given()
                .when()
                .port(this.port)
                .get("/problem/{problemId}/test", problem.getId())
                .then().assertThat()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @org.junit.Test
    public void testGetProblemNotExistingListTestsEmpty() throws Exception {
        given()
                .when()
                .port(this.port)
                .get("/problem/{problemId}/test", idProblemNotExist)
                .then().assertThat()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @org.junit.Test
    public void testGetTestById() throws Exception {
        given()
                .when()
                .port(this.port)
                .get("/problem/{idProblem}/test/{idTest}" ,problem.getId(), testList.get(0).getId())
                .then().assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

    @org.junit.Test
    public void testGetTestByIdNotFound() throws Exception {
        given()
                .when()
                .port(this.port)
                .get("/problem/{idProblem}/test/{idTest}",idProblemNotExist, idTestNotExist)
                .then().assertThat()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @org.junit.Test
    public void testCreateTest() throws Exception {
        com.ufcg.models.Test test = new com.ufcg.models.Test("name", "tip", "", "", Visibility.PRIVATE);

        given()
                .contentType(ContentType.JSON)
                .body(test)
                .when()
                .port(this.port)
                .post("/problem/{idProblem}/test", problem.getId())
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED);
    }

    @org.junit.Test
    public void testUpdateTest() throws Exception {
        com.ufcg.models.Test test = new com.ufcg.models.Test("name", "tip", "", "", Visibility.PRIVATE);

        given()
                .contentType(ContentType.JSON)
                .body(test)
                .when()
                .port(this.port)
                .put("/problem/{idProblem}/test/{idTest}", problem.getId(), testList.get(0).getId())
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

    @org.junit.Test
    public void testUpdateTestNotExisting() throws Exception {
        com.ufcg.models.Test test = new com.ufcg.models.Test("name", "tip", "", "", Visibility.PRIVATE);

        given()
                .contentType(ContentType.JSON)
                .body(test)
                .when()
                .port(this.port)
                .put("/problem/{idProblem}/test/{idTest}", idProblemNotExist, idTestNotExist)
                .then().assertThat()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @org.junit.Test
    public void testDeleteTest() throws Exception {
        given()
                .when()
                .port(this.port)
                .delete("/problem/{idProblem}/test/{idTest}", problem.getId(), testList.get(0).getId())
                .then()
                .assertThat().statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @org.junit.Test
    public void testDeleteTestNotExist() throws Exception {
        given()
                .when()
                .port(this.port)
                .delete("/problem/{idProblem}/test/{idTest}", idProblemNotExist, idTestNotExist)
                .then()
                .assertThat().statusCode(HttpStatus.SC_NOT_FOUND);
    }
}

