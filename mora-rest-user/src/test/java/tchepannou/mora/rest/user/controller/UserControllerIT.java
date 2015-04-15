package tchepannou.mora.rest.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Header;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tchepannou.mora.core.dao.PasswordDao;
import tchepannou.mora.core.dao.UserDao;
import tchepannou.mora.core.domain.Password;
import tchepannou.mora.core.domain.User;
import tchepannou.mora.rest.core.security.SecurityContants;
import tchepannou.mora.rest.user.Application;
import tchepannou.mora.rest.user.dto.CreateUserDto;
import tchepannou.mora.rest.user.dto.SaveUserDto;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith (SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration (classes = Application.class)
@WebIntegrationTest
@SqlGroup({
        @Sql (executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:db/rest-user-clean.sql", "classpath:db/rest-user-populate.sql"}),
})
public class UserControllerIT {
    private static final String ACCESS_TOKEN = "bc9a50e1e0085b13c4bba866f6dfe57c";

    @Value ("${server.port}")
    private int port;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordDao passwordDao;


    @Before
    public void setUp (){
        RestAssured.port = this.port;
    }



    @Test
    public void testGet() throws Exception {
        given()
           .header(new Header(SecurityContants.X_AUTH_TOKEN.name(), ACCESS_TOKEN))
        .when()
            .get("/users/{userId}", 1)
        .then()
            .statusCode(HttpStatus.SC_OK)
                .log().all()
            .body("id", is(1))
            .body("username", is("ray.sponsible"))
            .body("email", is("ray.sponsible@gmail.com"))
            .body("name", is("Ray Sponsible"))
            .body("firstName", is("Ray"))
            .body("lastName", is("Sponsible"))
            .body("creationDate", is("2014-01-01 10:30:55 -0500"))
            .body("lastUpdate", is("2014-12-01 14:30:55 -0500"))
        ;
    }

    @Test
    public void testGet_notFound_shouldReturn404() throws Exception {
        given()
           .header(new Header(SecurityContants.X_AUTH_TOKEN.name(), ACCESS_TOKEN))
        .when()
            .get("/users/{userId}", 9999)
        .then()
            .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void testGet_deletedUser_shouldReturn404() throws Exception {
        given()
           .header(new Header(SecurityContants.X_AUTH_TOKEN.name(), ACCESS_TOKEN))
        .when()
            .get("/users/{userId}", 2)
        .then()
            .statusCode(HttpStatus.SC_NOT_FOUND);
    }



    @Test
    public void testUpdate() throws Exception {
        // Given
        User user = userDao.findById(1);

        SaveUserDto request = new SaveUserDto();
        request.setEmail("foo.bar@gmail.com");
        request.setFirstName("Foo");
        request.setLastName("Bar");
        String json = new ObjectMapper().writeValueAsString(request);

        // When
        given()
            .contentType("application/json")
            .header(new Header(SecurityContants.X_AUTH_TOKEN.name(), ACCESS_TOKEN))
            .body(json)
        .when()
            .post("/users/{userId}", 1)
        .then()
            .statusCode(HttpStatus.SC_OK)
            .log().all()
            .body("id", is(1))
            .body("username", is(user.getUsername()))
            .body("email", is("foo.bar@gmail.com"))
            .body("name", is("Foo Bar"))
            .body("firstName", is("Foo"))
            .body("lastName", is("Bar"))
        ;

        User xuser = userDao.findById(1);
        User expected = new User (user);
        expected.setEmail("foo.bar@gmail.com");
        expected.setFirstName("Foo");
        expected.setLastName("Bar");
        expected.setLastUpdate(xuser.getLastUpdate());
        assertThat(xuser, equalTo(expected));
    }

    @Test
    public void testUpdate_notFound_shouldReturn404() throws Exception {
        // Given
        SaveUserDto request = new SaveUserDto();
        request.setEmail("foo.bar@gmail.com");
        request.setFirstName("Foo");
        request.setLastName("Bar");
        String json = new ObjectMapper().writeValueAsString(request);

        // When
        given()
            .contentType("application/json")
            .header(new Header(SecurityContants.X_AUTH_TOKEN.name(), ACCESS_TOKEN))
            .body(json)
        .when()
            .post("/users/{userId}", 9999)
        .then()
            .statusCode(HttpStatus.SC_NOT_FOUND)
        ;
    }

    @Test
    public void testUpdate_deleted_shouldReturn404() throws Exception {
        // Given
        SaveUserDto request = new SaveUserDto();
        request.setEmail("foo.bar@gmail.com");
        request.setFirstName("Foo");
        request.setLastName("Bar");
        String json = new ObjectMapper().writeValueAsString(request);

        // When
        given()
            .contentType("application/json")
            .header(new Header(SecurityContants.X_AUTH_TOKEN.name(), ACCESS_TOKEN))
            .body(json)
        .when()
            .post("/users/{userId}", 2)
        .then()
            .statusCode(HttpStatus.SC_NOT_FOUND)
        ;
    }

    @Test
    public void testUpdate_duplicateEmail_shouldReturn409() throws Exception {
        // Given
        SaveUserDto request = new SaveUserDto();
        request.setEmail("john.doe@gmail.com");
        request.setFirstName("Foo");
        request.setLastName("Bar");
        String json = new ObjectMapper().writeValueAsString(request);

        // When
        given()
            .contentType("application/json")
            .header(new Header(SecurityContants.X_AUTH_TOKEN.name(), ACCESS_TOKEN))
            .body(json)
        .when()
            .post("/users/{userId}", 1)
        .then()
            .statusCode(HttpStatus.SC_CONFLICT)
        ;
    }




    @Test
    public void testCreate() throws Exception {
        // Given
        CreateUserDto request = new CreateUserDto();
        request.setEmail("foo.bar@gmail.com");
        request.setFirstName("Foo");
        request.setLastName("Bar");
        request.setUsername("foo.bar");
        request.setPassword("_secret_password_");
        String json = new ObjectMapper().writeValueAsString(request);

        // When
        int userId = given()
            .contentType("application/json")
            .body(json)
        .when()
            .put("/users")
        .then()
            .statusCode(HttpStatus.SC_OK)
            .log().all()
            .body("id", greaterThan(0))
            .body("username", is("foo.bar"))
            .body("email", is("foo.bar@gmail.com"))
            .body("name", is("Foo Bar"))
            .body("firstName", is("Foo"))
            .body("lastName", is("Bar"))
        .extract()
            .path("id");
        ;

        // Then
        User user = userDao.findById(userId);
        User expected = new User (userId);
        expected.setEmail("foo.bar@gmail.com");
        expected.setFirstName("Foo");
        expected.setLastName("Bar");
        expected.setUsername("foo.bar");
        expected.setCreationDate(user.getCreationDate());
        expected.setLastUpdate(user.getLastUpdate());
        assertThat(user, equalTo(expected));

        Password password = passwordDao.findByUser(userId);
        assertThat(password, notNullValue());
        assertThat(password.getValue(), notNullValue());
    }

    @Test
    public void testCreate_DuplicateEmail_shouldReturn409() throws Exception {
        // Given
        CreateUserDto request = new CreateUserDto();
        request.setEmail("ray.sponsible@gmail.com");
        request.setFirstName("Foo");
        request.setLastName("Bar");
        request.setUsername("foo.bar");
        request.setPassword("_secret_password_");
        String json = new ObjectMapper().writeValueAsString(request);

        // When
        given()
            .contentType("application/json")
            .body(json)
        .when()
            .put("/users")
        .then()
            .statusCode(HttpStatus.SC_CONFLICT)
        ;
    }

    @Test
    public void testCreate_DuplicateUserName_shouldReturn409() throws Exception {
        // Given
        CreateUserDto request = new CreateUserDto();
        request.setUsername("ray.sponsible");
        request.setEmail("foo.bar@gmail.com");
        request.setFirstName("Foo");
        request.setLastName("Bar");
        request.setPassword("_secret_password_");
        String json = new ObjectMapper().writeValueAsString(request);

        // When
        given()
            .contentType("application/json")
            .body(json)
        .when()
            .put("/users")
        .then()
            .statusCode(HttpStatus.SC_CONFLICT)
        ;
    }

    @Test
    public void testCreate_nullEmail_shouldReturn400() throws Exception {
        // Given
        CreateUserDto request = new CreateUserDto();
        request.setUsername("ray.sponsible");
        request.setEmail(null);
        request.setFirstName("Foo");
        request.setLastName("Bar");
        request.setPassword("_secret_password_");
        String json = new ObjectMapper().writeValueAsString(request);

        // When
        given()
                .contentType("application/json")
            .body(json)
        .when()
            .put("/users")
        .then()
            .statusCode(HttpStatus.SC_BAD_REQUEST)
        ;
    }

    @Test
    public void testCreate_emptyEmail_shouldReturn400() throws Exception {
        // Given
        CreateUserDto request = new CreateUserDto();
        request.setUsername("ray.sponsible");
        request.setEmail(null);
        request.setFirstName("Foo");
        request.setLastName("Bar");
        request.setPassword("_secret_password_");
        String json = new ObjectMapper().writeValueAsString(request);

        // When
        given()
            .contentType("application/json")
            .body(json)
        .when()
            .put("/users")
        .then()
            .statusCode(HttpStatus.SC_BAD_REQUEST)
        ;
    }

    @Test
    public void testCreate_badEmail_shouldReturn400() throws Exception {
        // Given
        CreateUserDto request = new CreateUserDto();
        request.setUsername("ray.sponsible");
        request.setEmail("xxx");
        request.setFirstName("Foo");
        request.setLastName("Bar");
        request.setPassword("_secret_password_");
        String json = new ObjectMapper().writeValueAsString(request);

        // When
        given()
            .contentType("application/json")
            .body(json)
        .when()
            .put("/users")
        .then()
            .statusCode(HttpStatus.SC_BAD_REQUEST)
        ;
    }

    @Test
    public void testCreate_nullUsername_shouldReturn400() throws Exception {
        // Given
        CreateUserDto request = new CreateUserDto();
        request.setUsername(null);
        request.setEmail("ray.sponsible@gmail.com");
        request.setFirstName("Foo");
        request.setLastName("Bar");
        request.setPassword("_secret_password_");
        String json = new ObjectMapper().writeValueAsString(request);

        // When
        given()
            .contentType("application/json")
            .body(json)
        .when()
            .put("/users")
        .then()
            .statusCode(HttpStatus.SC_BAD_REQUEST)
        ;
    }

    @Test
    public void testCreate_shortUsername_shouldReturn400() throws Exception {
        // Given
        CreateUserDto request = new CreateUserDto();
        request.setUsername("12345");
        request.setEmail("ray.sponsible@gmail.com");
        request.setFirstName("Foo");
        request.setLastName("Bar");
        request.setPassword("_secret_password_");
        String json = new ObjectMapper().writeValueAsString(request);

        // When
        given()
            .contentType("application/json")
            .body(json)
        .when()
            .put("/users")
        .then()
            .statusCode(HttpStatus.SC_BAD_REQUEST)
        ;
    }

    @Test
    public void testCreate_noPassword_shouldReturn400() throws Exception {
        // Given
        CreateUserDto request = new CreateUserDto();
        request.setUsername("ray.sponsible");
        request.setEmail("ray.sponsible@gmail.com");
        request.setFirstName("Foo");
        request.setLastName("Bar");
        request.setPassword(null);
        String json = new ObjectMapper().writeValueAsString(request);

        // When
        given()
            .contentType("application/json")
            .body(json)
        .when()
            .put("/users")
        .then()
            .statusCode(HttpStatus.SC_BAD_REQUEST)
        ;
    }

    @Test
    public void testCreate_shortPassword_shouldReturn400() throws Exception {
        // Given
        CreateUserDto request = new CreateUserDto();
        request.setUsername("ray.sponsible");
        request.setEmail("ray.sponsible@gmail.com");
        request.setFirstName("Foo");
        request.setLastName("Bar");
        request.setPassword("12345");
        String json = new ObjectMapper().writeValueAsString(request);

        // When
        given()
            .contentType("application/json")
            .body(json)
        .when()
            .put("/users")
        .then()
            .statusCode(HttpStatus.SC_BAD_REQUEST)
        ;
    }


    @Test
    public void testDelete() throws Exception {
        // Given
        User expected = userDao.findById(1);
        expected.setDeleted(true);

        // When
        given()
            .header(new Header(SecurityContants.X_AUTH_TOKEN.name(), ACCESS_TOKEN))
        .when()
            .delete("/users/{userId}", 1)
        .then()
            .statusCode(HttpStatus.SC_OK);

        // Then
        User user = userDao.findById(1);
        assertThat(user, nullValue());
    }

    @Test
    public void testDelete_deletedUser() throws Exception {
        given()
            .header(new Header(SecurityContants.X_AUTH_TOKEN.name(), ACCESS_TOKEN))
        .when().
            delete("/users/{userId}", 2).
        then().
            statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void testDelete_notFound() throws Exception {
        given()
            .header(new Header(SecurityContants.X_AUTH_TOKEN.name(), ACCESS_TOKEN))
        .when().
            delete("/users/{userId}", 999).
        then().
            statusCode(HttpStatus.SC_OK);
    }

}