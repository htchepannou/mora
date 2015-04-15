package tchepannou.mora.rest.auth.controller;

import com.jayway.restassured.RestAssured;
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
import tchepannou.mora.core.dao.RoleDao;
import tchepannou.mora.rest.auth.Application;
import tchepannou.mora.rest.auth.dto.RoleDto;

import java.util.Arrays;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith (SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration (classes = Application.class)
@WebIntegrationTest
@SqlGroup ({
        @Sql (executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:db/rest-auth-clean.sql", "classpath:db/rest-auth-populate.sql"}),
})
public class RoleControllerIT {
    private static final String ACCESS_TOKEN = "bc9a50e1e0085b13c4bba866f6dfe57c";

    @Value ("${server.port}")
    private int port;

    @Autowired
    private RoleDao dao;


    @Before
    public void setUp (){
        RestAssured.port = this.port;
    }


    @Test
    public void testAll() throws Exception {
        RoleDto[] aresult = given()
                .when()
                .get("/roles")
                    .as(RoleDto[].class);
        List<RoleDto> result = Arrays.asList(aresult);

        assertThat(result, hasSize(2));
        assertThat(result, hasItems(new RoleDto(1, "admin"), new RoleDto(2, "member")));
    }

    @Test
    public void testGet() throws Exception {
        when()
        .get("/roles/{roleId}", 1L)
        .then()
            .statusCode(HttpStatus.SC_OK)
            .log().all()
            .body("id", is(1))
            .body("name", is("admin"))
        ;
    }

    @Test
    public void testGet_notFound_returns404() throws Exception {
        when()
        .get("/roles/{roleId}", 999)
        .then()
            .statusCode(HttpStatus.SC_NOT_FOUND)
        ;
    }
}