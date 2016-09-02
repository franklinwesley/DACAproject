package com.ufcg;

import com.jayway.restassured.http.ContentType;
import com.ufcg.Utils.UserType;
import com.ufcg.models.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

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
                .statusCode(404);
    }

    @Test
    public void testCreateUser() throws Exception {
        long id = 11;
        User user = new User("email@example.com", "password", UserType.ADMINISTRATOR);

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
    public void testUpdateUser() throws Exception {
        long id = 11;
        User user = new User("email@example.com", "password", UserType.ADMINISTRATOR);

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
    public void testDeleteUser() throws Exception {
        int id = 11;
        given()
                .when()
                .port(this.port)
                .delete(route + "/" + id)
                .then()
                .assertThat().statusCode(204);
    }
}
