package com.ufcg;

import com.jayway.restassured.RestAssured;
import com.ufcg.Utils.UserType;
import com.ufcg.models.User;
import com.ufcg.repositories.ProblemRepository;
import com.ufcg.repositories.SolutionRepository;
import com.ufcg.repositories.UserRepository;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.jayway.restassured.RestAssured.basic;
import static com.jayway.restassured.RestAssured.given;

@SpringApplicationConfiguration(classes=DacaApplication.class)
@WebIntegrationTest("server.port=0")
@RunWith(SpringJUnit4ClassRunner.class)
public class AdministratorUserStatisticControllerTest {
    @Value("${local.server.port}")
    private int port;
    private String route = "/statistic";
    private UserRepository userRepository;
    private User userTest;
    private User userAdmin;
    private SolutionRepository solutionRepository;
    private ProblemRepository problemRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository, ProblemRepository problemRepository,
                                  SolutionRepository solutionRepository) {
        this.problemRepository = problemRepository;
        this.userRepository = userRepository;
        this.solutionRepository = solutionRepository;
    }

    @Before
    public void setUp(){
        after();
        String username = "useradminstatistic@gmail.com";
        String password = "123456";

        userAdmin = new User(username,password, UserType.ADMINISTRATOR);
        userRepository.save(userAdmin);

        RestAssured.authentication = basic(username, password);
    }

    @After
    public void after(){
        solutionRepository.deleteAll();
        problemRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void testGetStatisticsWithoutUserId() throws Exception {

        given()
                .when()
                .port(this.port)
                .get(route)
                .then().assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("resolvedProblems", Matchers.equalTo(0))
                .body("usersSubmittingProblems", Matchers.equalTo(0));
    }

    @Test
    public void testGetStatisticsWithUserId() throws Exception {
        given()
                .param("userId", 1)
                .when()
                .port(this.port)
                .get(route)
                .then().assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("resolvedProblems", Matchers.equalTo(0))
                .body("usersSubmittingProblems", Matchers.equalTo(0))
                .body("userResolvedProblems", Matchers.equalTo(0));
    }
}
