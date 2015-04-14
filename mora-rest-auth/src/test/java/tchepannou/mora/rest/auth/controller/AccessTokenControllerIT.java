package tchepannou.mora.rest.auth.controller;

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
import tchepannou.mora.core.dao.AccessTokenDao;
import tchepannou.mora.core.domain.AccessToken;
import tchepannou.mora.rest.auth.Application;
import tchepannou.mora.rest.auth.dto.AuthRequest;
import tchepannou.mora.rest.core.security.SecurityContants;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith (SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration (classes = Application.class)
@WebIntegrationTest
@SqlGroup ({
        @Sql (executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:db/rest-auth-clean.sql", "classpath:db/rest-auth-populate.sql"}),
})
public class AccessTokenControllerIT {
    @Value ("${server.port}")
    private int port;

    @Autowired
    private AccessTokenDao accessTokenDao;


    @Before
    public void setUp (){
        RestAssured.port = this.port;
    }


    @Test
    public void testGet() throws Exception {
        given()
           .header(new Header(SecurityContants.X_AUTH_TOKEN.name(), "bc9a50e1e0085b13c4bba866f6dfe57c"))
        .when()
            .get("/access_token")
        .then()
            .statusCode(HttpStatus.SC_OK)
                .log().all()
            .body("value", is("bc9a50e1e0085b13c4bba866f6dfe57c"))
            .body("creationDate", is("2014-01-01 10:30:55 -0500"))
            .body("expiryDate", is("2030-12-01 14:30:55 -0500"))
        ;
    }

    @Test
    public void testGet_noHeader_returns400() throws Exception {
        when().
            get("/access_token")
        .then()
            .statusCode(HttpStatus.SC_BAD_REQUEST)
        ;
    }

    @Test
    public void testGet_badHeader_returns401() throws Exception {
        given()
           .header(new Header(SecurityContants.X_AUTH_TOKEN.name(), "???"))
        .when()
            .get("/access_token")
        .then()
            .statusCode(HttpStatus.SC_UNAUTHORIZED)
        ;
    }

    @Test
    public void testGet_expiredToken_returns401() throws Exception {
        given()
           .header(new Header(SecurityContants.X_AUTH_TOKEN.name(), "xc9a50e1e0085b13c4bba866f6dfe57c"))
        .when()
            .get("/access_token")
        .then()
            .statusCode(HttpStatus.SC_UNAUTHORIZED)
        ;
    }


    @Test
    public void testDelete() throws Exception {
        given()
           .header(new Header(SecurityContants.X_AUTH_TOKEN.name(), "bc9a50e1e0085b13c4bba866f6dfe57c"))
        .when()
            .delete("/access_token")
        .then()
            .statusCode(HttpStatus.SC_OK);

        AccessToken token = accessTokenDao.findByValue("bc9a50e1e0085b13c4bba866f6dfe57c");
        assertThat(token.isExpired(), equalTo(true));
    }

    @Test
    public void testDelete_noHeader_returns400() throws Exception {
        when().
            delete("/access_token")
        .then()
            .statusCode(HttpStatus.SC_BAD_REQUEST)
        ;
    }

    @Test
    public void testDelete_badHeader_returns401() throws Exception {
        given()
           .header(new Header(SecurityContants.X_AUTH_TOKEN.name(), "????"))
        .when()
            .delete("/access_token")
        .then()
            .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }


    @Test
    public void testAuthenticateByUsername() throws Exception {
        AuthRequest req = new AuthRequest();
        req.setUsernameOrEmail("ray.sponsible");
        req.setPassword("_secret_");
        String json = new ObjectMapper().writeValueAsString(req);

        String value = given()
            .contentType("application/json")
            .body(json)
        .when()
            .put("/access_token")
        .then()
            .statusCode(HttpStatus.SC_OK)
        .extract()
            .path("value");
        ;

        AccessToken token = accessTokenDao.findByValue(value);
        assertThat(token, notNullValue());
    }

    @Test
    public void testAuthenticateByUsername_BadUsername_returns409() throws Exception {
        AuthRequest req = new AuthRequest();
        req.setUsernameOrEmail("????");
        req.setPassword("select");
        String json = new ObjectMapper().writeValueAsString(req);

        given()
            .contentType("application/json")
            .body(json)
        .when()
            .put("/access_token")
        .then()
            .statusCode(HttpStatus.SC_CONFLICT)
        ;
    }

    @Test
    public void testAuthenticateByUsername_BadPassword_returns409() throws Exception {
        AuthRequest req = new AuthRequest();
        req.setUsernameOrEmail("ray.sponsible");
        req.setPassword("???");
        String json = new ObjectMapper().writeValueAsString(req);

        given()
            .contentType("application/json")
            .body(json)
        .when()
            .put("/access_token")
        .then()
            .statusCode(HttpStatus.SC_CONFLICT)
        ;
    }

    @Test
    public void testAuthenticateByEmail() throws Exception {
        AuthRequest req = new AuthRequest();
        req.setUsernameOrEmail("ray.sponsible@gmail.com");
        req.setPassword("_secret_");
        String json = new ObjectMapper().writeValueAsString(req);

        String value = given()
            .contentType("application/json")
            .body(json)
        .when()
            .put("/access_token")
        .then()
            .statusCode(HttpStatus.SC_OK)
        .extract()
            .path("value");
        ;

        AccessToken token = accessTokenDao.findByValue(value);
        assertThat(token, notNullValue());
    }

    @Test
    public void testAuthenticateByEmail_BadEmail_returns409() throws Exception {
        AuthRequest req = new AuthRequest();
        req.setUsernameOrEmail("????");
        req.setPassword("select");
        String json = new ObjectMapper().writeValueAsString(req);

        given()
            .contentType("application/json")
            .body(json)
        .when()
            .put("/access_token")
        .then()
            .statusCode(HttpStatus.SC_CONFLICT)
        ;
    }

    @Test
    public void testAuthenticateByEmail_BadPassword_returns409() throws Exception {
        AuthRequest req = new AuthRequest();
        req.setUsernameOrEmail("ray.sponsible@gmail.com");
        req.setPassword("???");
        String json = new ObjectMapper().writeValueAsString(req);

        given()
            .contentType("application/json")
            .body(json)
        .when()
            .put("/access_token")
        .then()
            .statusCode(HttpStatus.SC_CONFLICT)
        ;
    }
}