package com.ufcg;

import com.google.gson.Gson;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.ufcg.Utils.UserType;
import com.ufcg.models.User;
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
import static org.hamcrest.CoreMatchers.is;

@SpringApplicationConfiguration(classes=DacaApplication.class)
@WebIntegrationTest("server.port=0")
@RunWith(SpringJUnit4ClassRunner.class)
public class AdministratorUserControllerTest {


    @Value("${local.server.port}")
    private int port;
    private String route = "/user";

    private UserRepository userRepository;

    private User userAdmin;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Before
    public void setup(){
        String username = "useradmin@gmail.com";
        String password = "123456";

        userAdmin = new User(username,password, UserType.ADMINISTRATOR);
        userRepository.save(userAdmin);

        RestAssured.authentication = basic(username, password);
    }

    @After
    public void setdown() {
        userRepository.deleteAll();
    }

    @Test
    public void testGetUsers() throws Exception {
        given()
                .when()
                .port(this.port)
                .get(route)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("email", Matchers.hasItems(userAdmin.getEmail()));
    }

    @Test
    public void testGetUserById() throws Exception {
        given()
                .when()
                .port(this.port)
                .get(route + "/{id}", userAdmin.getId())
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("email", Matchers.is(userAdmin.getEmail()))
                .body("id", is(userAdmin.getId().intValue()));
    }

    @Test
    public void testCreateUser() throws Exception {
        Gson gson = new Gson();
        User user5 = new User("user5@gmail.com", "12j1oi2jow", UserType.NORMAL);

        given()
                .contentType(ContentType.JSON)
                .body(gson.toJson(user5))
                .when()
                .port(this.port)
                .post(route)
                .then().assertThat()
                .statusCode(HttpStatus.SC_CREATED);
    }

    @Test
    public void testUpdateUser() throws Exception {
        userAdmin.setEmail("newUser@gmail.com");

        given()
                .contentType(ContentType.JSON)
                .body(userAdmin)
                .when()
                .port(this.port)
                .put(route + "/{id}", userAdmin.getId())
                .then()
                .assertThat().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void testDeleteUser() throws Exception {
        given()
                .when()
                .port(this.port)
                .delete(route + "/{id}",userAdmin.getId())
                .then()
                .assertThat().statusCode(HttpStatus.SC_NO_CONTENT);
    }
}
