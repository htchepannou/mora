package tchepannou.mora.rest.space.controller;

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
import tchepannou.mora.core.dao.MemberDao;
import tchepannou.mora.core.domain.Member;
import tchepannou.mora.rest.core.security.SecurityContants;
import tchepannou.mora.rest.space.Application;
import tchepannou.mora.rest.space.dto.CreateMemberDto;
import tchepannou.mora.rest.space.dto.SaveMemberDto;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith (SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration (classes = Application.class)
@WebIntegrationTest
@SqlGroup ({
        @Sql (executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:db/rest-space-clean.sql", "classpath:db/rest-space-populate.sql"}),
})
public class MemberControllerIT {
    private static final String ACCESS_TOKEN = "bc9a50e1e0085b13c4bba866f6dfe57c";

    @Value ("${server.port}")
    private int port;

    @Autowired
    private MemberDao dao;

    //-- Tests
    @Before
    public void setUp (){
        RestAssured.port = this.port;
    }


    @Test
    public void testGet() throws Exception {
        when()
            .get("/members/{memberId}", 10)
        .then()
            .statusCode(HttpStatus.SC_OK)
            .log().all()
            .body("id", is(10))
            .body("spaceId", is(1))
            .body("userId", is(1))
            .body("roleId", is(1))
            .body("creationDate", is("2014-12-01 14:30:55 -0500"))
        ;
    }
    @Test
    public void testGet_notFound_returns404() throws Exception {
        when()
            .get("/members/{memberId}", 9999)
        .then()
            .statusCode(HttpStatus.SC_NOT_FOUND)
        ;
    }

    @Test
    public void testCreate () throws Exception {
        // Given
        CreateMemberDto dto = new CreateMemberDto();
        dto.setSpaceId(1);
        dto.setRoleId(1);
        dto.setUserId(3);
        String json = new ObjectMapper().writeValueAsString(dto);

        // When
        int memberId = given()
            .contentType("application/json")
            .header(new Header(SecurityContants.X_AUTH_TOKEN.name(), ACCESS_TOKEN))
            .body(json)
        .when()
            .put("/members")
        .then()
            .statusCode(HttpStatus.SC_OK)
            .log().all()
            .body("id", greaterThan(0))
            .body("spaceId", is(1))
            .body("userId", is(3))
            .body("roleId", is(1))
        .extract().path("id")
        ;

        Member result = dao.findById(memberId);

        Member expected = new Member();
        expected.setId(memberId);
        expected.setCreationDate(result.getCreationDate());
        expected.setUserId(3);
        expected.setSpaceId(1);
        expected.setRoleId(1);
        assertThat(result, equalTo(expected));
    }

    @Test
    public void testCreate_duplicateRole_retunsDuplicate () throws Exception {
        // Given
        CreateMemberDto dto = new CreateMemberDto();
        dto.setSpaceId(1);
        dto.setUserId(1);
        dto.setRoleId(2);
        String json = new ObjectMapper().writeValueAsString(dto);

        // When
        given()
            .contentType("application/json")
            .header(new Header(SecurityContants.X_AUTH_TOKEN.name(), ACCESS_TOKEN))
            .body(json)
        .when()
            .put("/members")
        .then()
            .statusCode(HttpStatus.SC_OK)
            .log().all()
            .body("id", equalTo(11))
            .body("spaceId", is(1))
            .body("userId", is(1))
            .body("roleId", is(2))
            .body("creationDate", is("2014-12-01 14:30:55 -0500"))
        ;
    }

    @Test
    public void testUpdate() throws Exception {
        // Given
        SaveMemberDto dto = new SaveMemberDto();
        dto.setRoleId(2);
        String json = new ObjectMapper().writeValueAsString(dto);

        // When
        given()
            .contentType("application/json")
            .header(new Header(SecurityContants.X_AUTH_TOKEN.name(), ACCESS_TOKEN))
            .body(json)
        .when()
            .post("/members/{memberId}", 20)
        .then()
            .statusCode(HttpStatus.SC_OK)
            .log().all()
            .body("id", equalTo(20))
            .body("spaceId", is(1))
            .body("userId", is(2))
            .body("roleId", is(2))
            .body("creationDate", is("2014-12-01 14:30:55 -0500"))
        ;
    }

    @Test
    public void testUpdate_duplicateRole_retunsDuplicate () throws Exception {
        // Given
        SaveMemberDto dto = new SaveMemberDto();
        dto.setRoleId(2);
        String json = new ObjectMapper().writeValueAsString(dto);

        // When
        given()
            .contentType("application/json")
            .header(new Header(SecurityContants.X_AUTH_TOKEN.name(), ACCESS_TOKEN))
            .body(json)
        .when()
            .post("/members/{memberId}", 10)
        .then()
            .statusCode(HttpStatus.SC_OK)
            .log().all()
            .body("id", equalTo(11))
            .body("spaceId", is(1))
            .body("userId", is(1))
            .body("roleId", is(2))
            .body("creationDate", is("2014-12-01 14:30:55 -0500"))
        ;
    }

    @Test
    public void testUpdate_notFound_retuns404 () throws Exception {
        // Given
        SaveMemberDto dto = new SaveMemberDto();
        dto.setRoleId(2);
        String json = new ObjectMapper().writeValueAsString(dto);

        // When
        given()
            .contentType("application/json")
            .header(new Header(SecurityContants.X_AUTH_TOKEN.name(), ACCESS_TOKEN))
            .body(json)
        .when()
            .post("/members/{memberId}", 999)
        .then()
            .statusCode(404)
            .log().all()
        ;
    }

    @Test
    public void testDelete() throws Exception {
        given()
            .header(new Header(SecurityContants.X_AUTH_TOKEN.name(), ACCESS_TOKEN))
        .when()
            .delete("/members/{memberId}", 10)
        .then()
            .statusCode(HttpStatus.SC_OK)
            .log().all()
        ;
        Member result = dao.findById(10);

        assertThat(result, nullValue());
    }

    @Test
    public void testDelete_notFound_ok() throws Exception {
        given()
            .header(new Header(SecurityContants.X_AUTH_TOKEN.name(), ACCESS_TOKEN))
        .when()
            .delete("/members/{memberId}", 999)
        .then()
            .statusCode(HttpStatus.SC_OK)
            .log().all()
        ;
    }
}