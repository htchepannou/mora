package tchepannou.mora.rest.user.controller;

import com.jayway.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tchepannou.mora.rest.user.Application;

import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;

@RunWith (SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration (classes = Application.class)
@WebIntegrationTest
@SqlGroup({
        @Sql (executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:db/clean.sql", "classpath:db/populate.sql"}),
})
public class UserControllerIT {
    @Value ("${server.port}")
    private int port;

    @Before
    public void setUp (){
        RestAssured.port = this.port;
    }

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