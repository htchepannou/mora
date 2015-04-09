package tchepannou.mora.rest.user.controller;

import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import tchepannou.mora.rest.user.Application;

import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;

@RunWith (SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration (classes = Application.class)
@WebAppConfiguration
@IntegrationTest ("server.port:8080")
@SqlGroup({
        @Sql (executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:db/setup.sql"),
        @Sql (executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:db/teardown.sql")
})
public class UserControllerIT {

    @Test
    public void testGet() throws Exception {
        when().
            get("/users/{userId}", 1).
        then().
            statusCode(HttpStatus.SC_OK)
                .log().all()
            .body("id", is(1))
            .body("username", is("ray.sponsible"))
            .body("email", is("ray.sponsible@gmail.com"))
            .body("name", is("Ray Sponsible"))
            .body("firstName", is("Ray"))
            .body("lastName", is("Sponsible"))
                /*
            .body("creationDate", is("2014-01-01 10:30:55"))
            .body("lastUpdate", is("2014-12-01 14:30:55"))
            */
        ;
    }

    @Test
    public void testGet_notFound_shouldReturn404() throws Exception {
        when().
            get("/users/{userId}", 9999).
        then().
            statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void testGet_deletedUser_shouldReturn404() throws Exception {
        when().
            get("/users/{userId}", 2).
        then().
            statusCode(HttpStatus.SC_NOT_FOUND);
    }

//    @Test
//    public void testCreate() throws Exception {
//
//    }
//
//    @Test
//    public void testUpdate() throws Exception {
//
//    }
//
//    @Test
//    public void testDelete() throws Exception {
//
//    }
}