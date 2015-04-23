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
import tchepannou.mora.core.dao.SpaceDao;
import tchepannou.mora.core.domain.Space;
import tchepannou.mora.rest.core.security.SecurityContants;
import tchepannou.mora.rest.space.Application;
import tchepannou.mora.rest.space.dto.CreateSpaceDto;
import tchepannou.mora.rest.space.dto.SaveSpaceDto;
import tchepannou.mora.rest.space.dto.SpaceTypeDto;

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
        @Sql (executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:db/rest-space-clean.sql", "classpath:db/rest-space-populate.sql"}),
})
public class SpaceControllerIT {
    private static final String ACCESS_TOKEN = "bc9a50e1e0085b13c4bba866f6dfe57c";

    @Value ("${server.port}")
    private int port;

    @Autowired
    private SpaceDao spaceDao;

    //-- Tests
    @Before
    public void setUp (){
        RestAssured.port = this.port;
    }

    @Test
    public void testTypes() throws Exception {
        SpaceTypeDto[] aresult = given()
                .when()
                    .get("/spaces/types")
                    .as(SpaceTypeDto[].class);
        List<SpaceTypeDto> result = Arrays.asList(aresult);

        assertThat(result, hasSize(2));
        assertThat(result, hasItems(new SpaceTypeDto(1, "club"), new SpaceTypeDto(2, "team")));
    }

    @Test
    public void testType() throws Exception {
        when()
            .get("/spaces/types/{typeId}", 1)
        .then()
            .statusCode(HttpStatus.SC_OK)
            .log().all()
            .body("id", greaterThan(0))
            .body("name", is("club"))
        ;
    }

    @Test
    public void testType_notFound_returns404() throws Exception {
        when()
            .get("/spaces/types/{typeId}", 999)
        .then()
            .statusCode(HttpStatus.SC_NOT_FOUND)
            .log().all()
        ;
    }


    @Test
    public void testGet () throws Exception {
        when()
            .get("/spaces/{spaceId}", 1)
        .then()
            .statusCode(HttpStatus.SC_OK)
            .log().all()
            .body("id", is(1))
            .body("name", is("New York Soccer Club"))
            .body("email", is("info@newyorksoccerclub.org"))
            .body("logoUrl", is("http://img.com/nysc.png"))
            .body("websiteUrl", is("http://newyorksoccerclub.org"))
            .body("address", nullValue())
            .body("abbreviation", is("NYSC"))
            .body("description", is("Best soccer club"))
            .body("type.id", is(1))
            .body("type.name", is("club"))
        ;
    }
    @Test
    public void testGet_notFound_returns404 () throws Exception {
        when()
            .get("/spaces/{spaceId}", 999)
        .then()
            .statusCode(HttpStatus.SC_NOT_FOUND)
            .log().all()
        ;
    }


    @Test
    public void testCreate () throws Exception {
        // Given
        CreateSpaceDto dto = new CreateSpaceDto();
        dto.setTypeId(1);
        dto.setDescription("desc");
        dto.setAbbreviation("ABR");
        dto.setAddress("address");
        dto.setEmail("email@gmail.com");
        dto.setName("name");
        dto.setWebsiteUrl("http://www.web.com");
        String json = new ObjectMapper().writeValueAsString(dto);

        // When
        int spaceId = given()
            .contentType("application/json")
            .header(new Header(SecurityContants.X_AUTH_TOKEN.name(), ACCESS_TOKEN))
            .body(json)
        .when()
            .put("/spaces")
        .then()
            .statusCode(HttpStatus.SC_OK)
            .log().all()
            .body("id", greaterThan(0))
            .body("name", is("name"))
            .body("email", is("email@gmail.com"))
            .body("websiteUrl", is("http://www.web.com"))
            .body("address", is("address"))
            .body("abbreviation", is("ABR"))
            .body("description", is("desc"))
            .body("type.id", is(1))
            .body("type.name", is("club"))
                
        .extract().path("id");
        ;

        // Then
        Space space = spaceDao.findById(spaceId);
        Space expected = new Space ();
        expected.setId(spaceId);
        expected.setTypeId(1);
        expected.setUserId(1);
        expected.setEmail("email@gmail.com");
        expected.setDescription("desc");
        expected.setAbbreviation("ABR");
        expected.setAddress("address");
        expected.setEmail("email@gmail.com");
        expected.setName("name");
        expected.setWebsiteUrl("http://www.web.com");
        expected.setCreationDate(space.getCreationDate());
        expected.setLastUpdate(space.getLastUpdate());
        assertThat(space, equalTo(expected));
    }

    @Test
    public void testCreate_invalidSpaceType_shouldReturn400 () throws Exception {
        // Given
        CreateSpaceDto dto = new CreateSpaceDto();
        dto.setTypeId(999);
        dto.setDescription("desc");
        dto.setAbbreviation("ABR");
        dto.setAddress("address");
        dto.setEmail("email@gmail.com");
        dto.setName("name");
        dto.setWebsiteUrl("http://www.web.com");
        String json = new ObjectMapper().writeValueAsString(dto);

        // When
        given()
            .contentType("application/json")
            .header(new Header(SecurityContants.X_AUTH_TOKEN.name(), ACCESS_TOKEN))
            .body(json)
        .when()
            .put("/spaces")
        .then()
            .statusCode(HttpStatus.SC_BAD_REQUEST)
        ;
    }

    @Test
    public void testCreate_badTokenInRequestHeader_shouldReturn401 () throws Exception {
        // Given
        CreateSpaceDto dto = new CreateSpaceDto();
        dto.setTypeId(1);
        dto.setDescription("desc");
        dto.setAbbreviation("ABR");
        dto.setAddress("address");
        dto.setEmail("email@gmail.com");
        dto.setName("name");
        dto.setWebsiteUrl("http://www.web.com");
        String json = new ObjectMapper().writeValueAsString(dto);

        // When
        given()
            .contentType("application/json")
            .header(new Header(SecurityContants.X_AUTH_TOKEN.name(), "????"))
            .body(json)
        .when()
            .put("/spaces")
        .then()
            .statusCode(HttpStatus.SC_UNAUTHORIZED)
        ;
    }

    @Test
    public void testCreate_noTokenInRequestHeader_shouldReturn401 () throws Exception {
        // Given
        CreateSpaceDto dto = new CreateSpaceDto();
        dto.setTypeId(1);
        dto.setDescription("desc");
        dto.setAbbreviation("ABR");
        dto.setAddress("address");
        dto.setEmail("email@gmail.com");
        dto.setName("name");
        dto.setWebsiteUrl("http://www.web.com");
        String json = new ObjectMapper().writeValueAsString(dto);

        // When
        given()
            .contentType("application/json")
            .body(json)
        .when()
            .put("/spaces")
        .then()
            .statusCode(HttpStatus.SC_UNAUTHORIZED)
        ;
    }


    @Test
    public void testUpdate () throws Exception {
        // Given
        SaveSpaceDto dto = new SaveSpaceDto();
        dto.setDescription("desc");
        dto.setAbbreviation("ABR");
        dto.setAddress("address");
        dto.setEmail("email@gmail.com");
        dto.setName("name");
        dto.setWebsiteUrl("http://www.web.com");
        String json = new ObjectMapper().writeValueAsString(dto);

        Space expected = spaceDao.findById(1);
        expected.setEmail("email@gmail.com");
        expected.setDescription("desc");
        expected.setAbbreviation("ABR");
        expected.setAddress("address");
        expected.setEmail("email@gmail.com");
        expected.setName("name");
        expected.setWebsiteUrl("http://www.web.com");

        // When
        given()
            .contentType("application/json")
            .header(new Header(SecurityContants.X_AUTH_TOKEN.name(), ACCESS_TOKEN))
            .body(json)
        .when()
            .post("/spaces/{spaceId}", 1)
        .then()
            .statusCode(HttpStatus.SC_OK)
            .log().all()
            .body("id", is(1))
            .body("name", is("name"))
            .body("email", is("email@gmail.com"))
            .body("websiteUrl", is("http://www.web.com"))
            .body("address", is("address"))
            .body("abbreviation", is("ABR"))
            .body("description", is("desc"))
            .body("type.id", is(1))
            .body("type.name", is("club"))
        ;

        // Then
        Space space = spaceDao.findById(1);

        expected.setLastUpdate(space.getLastUpdate());
        assertThat(space, equalTo(expected));
    }

    @Test
    public void testUpdate_badId_shouldReturns404 () throws Exception {
        // Given
        SaveSpaceDto dto = new SaveSpaceDto();
        dto.setDescription("desc");
        dto.setAbbreviation("ABR");
        dto.setAddress("address");
        dto.setEmail("email@gmail.com");
        dto.setName("name");
        dto.setWebsiteUrl("http://www.web.com");
        String json = new ObjectMapper().writeValueAsString(dto);

        // When
        given()
            .contentType("application/json")
            .header(new Header(SecurityContants.X_AUTH_TOKEN.name(), ACCESS_TOKEN))
            .body(json)
        .when()
            .post("/spaces/{spaceId}", 999)
        .then()
            .statusCode(HttpStatus.SC_NOT_FOUND)
        ;
    }

    @Test
    public void testUpdate_badTokenInRequestHeader_shouldReturns404 () throws Exception {
        // Given
        SaveSpaceDto dto = new SaveSpaceDto();
        dto.setDescription("desc");
        dto.setAbbreviation("ABR");
        dto.setAddress("address");
        dto.setEmail("email@gmail.com");
        dto.setName("name");
        dto.setWebsiteUrl("http://www.web.com");
        String json = new ObjectMapper().writeValueAsString(dto);

        // When
        given()
            .contentType("application/json")
            .header(new Header(SecurityContants.X_AUTH_TOKEN.name(), "???"))
            .body(json)
        .when()
            .post("/spaces/{spaceId}", 999)
        .then()
            .statusCode(HttpStatus.SC_UNAUTHORIZED)
        ;
    }

    @Test
    public void testUpdate_noTokenInRequestHeader_shouldReturns404 () throws Exception {
        // Given
        SaveSpaceDto dto = new SaveSpaceDto();
        dto.setDescription("desc");
        dto.setAbbreviation("ABR");
        dto.setAddress("address");
        dto.setEmail("email@gmail.com");
        dto.setName("name");
        dto.setWebsiteUrl("http://www.web.com");
        String json = new ObjectMapper().writeValueAsString(dto);

        // When
        given()
            .contentType("application/json")
            .body(json)
        .when()
            .post("/spaces/{spaceId}", 999)
        .then()
            .statusCode(HttpStatus.SC_UNAUTHORIZED)
        ;
    }


    @Test
    public void testDelete () throws Exception {
        // When
        given()
            .contentType("application/json")
            .header(new Header(SecurityContants.X_AUTH_TOKEN.name(), ACCESS_TOKEN))
        .when()
            .delete("/spaces/{spaceId}", 1)
        .then()
            .statusCode(HttpStatus.SC_OK)
                ;

        // Then
        Space space = spaceDao.findById(1);
        assertThat(space, nullValue());
    }

    @Test
    public void testDelete_notFound_shouldReturn200 () throws Exception {
        given()
            .contentType("application/json")
            .header(new Header(SecurityContants.X_AUTH_TOKEN.name(), ACCESS_TOKEN))
        .when()
            .delete("/spaces/{spaceId}", 999)
        .then()
            .statusCode(HttpStatus.SC_OK)
                ;
    }

    @Test
    public void testDelete_badTokenInRequestHeader_shouldReturn401 () throws Exception {
        // When
        given()
            .contentType("application/json")
            .header(new Header(SecurityContants.X_AUTH_TOKEN.name(), "????"))
        .when()
            .delete("/spaces/{spaceId}", 1)
        .then()
            .statusCode(HttpStatus.SC_UNAUTHORIZED)
                ;
    }

    @Test
    public void testDelete_noTokenInRequestHeader_shouldReturn401 () throws Exception {
        // When
        given()
            .contentType("application/json")
        .when()
            .delete("/spaces/{spaceId}", 1)
        .then()
            .statusCode(HttpStatus.SC_UNAUTHORIZED)
                ;
    }
}
