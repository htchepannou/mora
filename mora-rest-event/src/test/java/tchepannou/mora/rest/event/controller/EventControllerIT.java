package tchepannou.mora.rest.event.controller;

import com.jayway.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tchepannou.mora.rest.core.security.SecurityContants;
import tchepannou.mora.rest.event.Application;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;

@RunWith (SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration (classes = Application.class)
@WebIntegrationTest
@SqlGroup ({
        @Sql (executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:db/rest-event-clean.sql", "classpath:db/rest-event-populate.sql"}),
})
public class EventControllerIT {
    @Value ("${server.port}")
    private int port;



    @Before
    public void setUp (){
        RestAssured.port = this.port;
    }

    @Test
    public void testGetEvent(){
        when()
            .get("/events/{eventId}", 300)
        .then()
            .statusCode(200)
            .log().all()
            .body("id", equalTo(300))
            .body("title", equalTo("title1"))
            .body("notes", equalTo("<p>notes</p>"))
            .body("location", equalTo("location"))
            .body("url", equalTo("http://www.google.ca"))
            .body("timezone", equalTo("America/Montreal"))
            .body("user.id", equalTo(300))
            .body("user.name", equalTo("Ray Sponsible"))
            .body("space.id", equalTo(300))
            .body("space.name", equalTo("space1"))
            .body("space.logoUrl", equalTo("http://space1.com/logo.png"))
            .body("type.id", equalTo(1))
            .body("type.name", equalTo("game"))
        ;
    }

    @Test
    public void testGetUpcoming(){
        given()
            .header(SecurityContants.X_AUTH_TOKEN.name(), "bc9a50e1e0085b13c4bba866f6dfe57c")
        .when()
            .get("/events/upcoming?limit=100&offset=0")
        .then()
            .statusCode(200)
            .log().all()
            .body("id", contains(300, 301))
            .body("title", contains("title1", "title2"))
        ;
    }
}
