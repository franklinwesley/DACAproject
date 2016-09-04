package com.ufcg;

import com.jayway.restassured.http.ContentType;
import com.ufcg.Utils.UserType;
import com.ufcg.Utils.Visibility;
import com.ufcg.models.Problem;
import com.ufcg.models.User;
import com.ufcg.repositories.ProblemRepository;
import com.ufcg.repositories.UserRepository;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPatch;
import org.hamcrest.Matchers;
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

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@SpringApplicationConfiguration(classes=DacaApplication.class)
@WebIntegrationTest("server.port=0")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestControllerTest {
    @Value("${local.server.port}")
    private int port;
    private ProblemRepository problemRepository;
    private UserRepository userRepository;

    private User userCreator;
    private Problem problem;
    private String[] testName;
    private String[] testTip;
    private String[] testInput;
    private String[] testOutput;
    private List<com.ufcg.models.Test> testList;
    private int idProblemNotExist = 11;
    private int idTestNotExist = 112;
    private int idTestExist = 1;

    @Autowired
    public void setProblemRepository(ProblemRepository problemRepository, UserRepository userRepository) {
        this.problemRepository = problemRepository;
        this.userRepository = userRepository;
    }

    @Before
    public void setup(){

        testList = new ArrayList<>();

        userCreator = new User("usercreator@gmail.com", "1oi2io1n", UserType.ADMINISTRATOR);
        userRepository.save(userCreator);
        testName = new String[5];
        testTip = new String[5];
        testInput = new String[5];
        testOutput = new String[5];
        for (int i = 0; i < 5; i++) {
            testList.add(new com.ufcg.models.Test("Test " + i, "Bad tip", "Inputs", "Output", Visibility.PUBLIC));
            testName[i] = "Test " + i;
            testTip[i] = "Bad tip";
            testInput[i] = "Inputs";
            testOutput[i] = "Output";
        }
        problem = new Problem(userCreator, "Problem 1", "Problem about the problems", "Good tip",testList,Visibility.PUBLIC);
        problemRepository.save(problem);
    }


    @Test
    public void testGetTests() throws Exception {
        given()
                .when()
                .port(this.port)
                .get("/problem/{id}/test", problem.getId())
                .then().assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("name", Matchers.hasItems(testName))
                .body("tip", Matchers.hasItems(testTip))
                .body("input", Matchers.hasItems(testInput))
                .body("output", Matchers.hasItems(testOutput));
    }

    @Test
    public void testGetProblemNotExistingListTestsEmpty() throws Exception {
        given()
                .when()
                .port(this.port)
                .get("/problem/{id}/test", idProblemNotExist)
                .then().assertThat()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void testGetTestById() throws Exception {
        given()
                .when()
                .port(this.port)
                .get("/problem/{idProblem}/test/{idTest}" ,problem.getId(), idTestExist)
                .then().assertThat()
                .statusCode(200);
    }

    @Test
    public void testGetTestByIdNotFound() throws Exception {
        int idProblem = 11;
        int idTest = 1;
        given()
                .when()
                .port(this.port)
                .get("/problem/{idProblem}/test/{idTest}",idProblem, idTest)
                .then().assertThat()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
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

    @Test
    public void testUpdateTest() throws Exception {
        com.ufcg.models.Test test = new com.ufcg.models.Test("name", "tip", "", "", Visibility.PRIVATE);

        given()
                .contentType(ContentType.JSON)
                .body(test)
                .when()
                .port(this.port)
                .put("/problem/{idProblem}/test/{idTest}", problem.getId(), idTestExist)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
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

    @Test
    public void testDeleteTest() throws Exception {
        given()
                .when()
                .port(this.port)
                .delete("/problem/{idProblem}/test/{idTest}", problem.getId(), idTestExist)
                .then()
                .assertThat().statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void testDeleteTestNotExist() throws Exception {
        given()
                .when()
                .port(this.port)
                .delete("/problem/{idProblem}/test/{idTest}", idProblemNotExist, idTestExist)
                .then()
                .assertThat().statusCode(HttpStatus.SC_NOT_FOUND);
    }
}
