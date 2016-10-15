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
public class NormalUserStatisticControllerTest {

    @Value("${local.server.port}")
    private int port;
    private String route = "/statistic";
    private UserRepository userRepository;
    private ProblemRepository problemRepository;
    private SolutionRepository solutionRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository, ProblemRepository problemRepository,
                                  SolutionRepository solutionRepository) {
        this.problemRepository = problemRepository;
        this.userRepository = userRepository;
        this.solutionRepository = solutionRepository;
    }

    @Before
    public void setUp(){
        String username = "usernormal@gmail.com";
        String password = "2312331";

        User userTest = new User(username,password, UserType.NORMAL);
        userRepository.save(userTest);

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
