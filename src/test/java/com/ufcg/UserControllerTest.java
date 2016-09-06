package com.ufcg;

import com.google.gson.Gson;
import com.jayway.restassured.http.ContentType;
import com.ufcg.Utils.UserType;
import com.ufcg.models.User;
import com.ufcg.repositories.UserRepository;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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

    private UserRepository userRepository;

    private User user1, user2, user3;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Before
    public void setup(){
        userRepository.deleteAll();
        user1 = new User("user1@gmail.com", "12919121", UserType.NORMAL);
        user2 = new User("user2@gmail.com", "aposm212om", UserType.ADMINISTRATOR);
        user3 = new User("user3@gmail.com", "210eo01e", UserType.NORMAL);
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
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
                        .body("email", Matchers.hasItems(user1.getEmail(), user2.getEmail(), user3.getEmail()));
    }

    @Test
    public void testGetUserById() throws Exception {
        given()
                .when()
                    .port(this.port)
                    .get(route + "/{id}", user1.getId())
                .then()
                    .assertThat()
                        .statusCode(HttpStatus.SC_OK)
                        .body("email", Matchers.is(user1.getEmail()))
                        .body("id", is(user1.getId().intValue()));
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
        user1.setEmail(user2.getEmail());
        given()
                .contentType(ContentType.JSON)
                .body(user1)
                .when()
                .port(this.port)
                .put(route + "/{id}", user1.getId())
                .then()
                .assertThat().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void testDeleteUser() throws Exception {
        given()
                .when()
                .port(this.port)
                .delete(route + "/{id}",user1.getId())
                .then()
                .assertThat().statusCode(HttpStatus.SC_NO_CONTENT);
    }
}
