package com.ufcg;

import com.jayway.restassured.http.ContentType;
import com.ufcg.Utils.UserType;
import com.ufcg.models.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.empty;

@SpringApplicationConfiguration(classes=DacaApplication.class)
@WebIntegrationTest("server.port=0")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest {

    @Value("${local.server.port}")
    private int port;
    private String route = "/user";

    @Test
    public void testGetUsers() throws Exception {
        given()
                .when()
                .port(this.port)
                .get(route)
                .then().assertThat()
                .statusCode(is(200));
    }

    @Test
    public void testGetUserById() throws Exception {
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
        long id = 11;
        User user = new User("email@example.com", "password", id, UserType.ADMINISTRATOR);

        given()
                .accept(ContentType.JSON)
                .body(user)
                .when()
                .port(this.port)
                .post(route)
                .then()
                .assertThat().statusCode(415);
    }

    @Test
    public void testUpdateSolution() throws Exception {
        long id = 11;
        User user = new User("email@example.com", "password", id, UserType.ADMINISTRATOR);

        given()
                .accept(ContentType.JSON)
                .body(user)
                .when()
                .port(this.port)
                .put(route + "/" + id)
                .then()
                .assertThat().statusCode(415);
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
