package tchepannou.mora.rest.post.controller;

import com.jayway.restassured.RestAssured;
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
import tchepannou.mora.core.dao.PostDao;
import tchepannou.mora.rest.core.security.SecurityContants;
import tchepannou.mora.rest.post.Application;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

@RunWith (SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration (classes = Application.class)
@WebIntegrationTest
@SqlGroup ({
        @Sql (executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:db/rest-post-clean.sql", "classpath:db/rest-post-populate.sql"}),
})
public class PostControllerIT {
    @Value ("${server.port}")
    private int port;

    @Autowired
    private PostDao dao;


    
    @Before
    public void setUp (){
        RestAssured.port = this.port;
    }

    @Test
    public void getCurrentUserPosts() throws Exception {
        given()
            .header(SecurityContants.X_AUTH_TOKEN.name(), "bc9a50e1e0085b13c4bba866f6dfe57c")
        .when()
            .get("/posts?limit=100&offset=0")
        .then()
                .statusCode(200)
                .log().all()
                .body("id", hasSize(2))
                .body("id", hasItems(300, 301))
                .body("title", hasItems("title1", "title2"))
                .body("summary", hasItems("summary1", "summary2"))
        ;
    }

    @Test
    public void getCurrentUserPosts_unauthenticated_returns404() throws Exception {
        when()
            .get("/posts?limit=100&offset=0")
        .then()
                .statusCode(401)
                .log().all()
        ;
    }

    @Test
    public void getPost() throws Exception {
        when()
            .get("/posts/{postId}", 300)
        .then()
            .statusCode(200)
            .log().all()
            .body("id", equalTo(300))
            .body("title", equalTo("title1"))
            .body("summary", equalTo("summary1"))
            .body("content", equalTo("<p>content1</p>"))
            .body("user.id", equalTo(300))
            .body("user.name", equalTo("Ray Sponsible"))
            .body("space.id", equalTo(300))
            .body("space.name", equalTo("space1"))
            .body("space.logoUrl", equalTo("http://space1.com/logo.png"))
            .body("medias.typeId", hasItems(1, 1))
            .body("medias.title", hasItems("title1", "title2"))
            .body("medias.description", hasItems("description1", "description2"))
            .body("medias.contentType", hasItems("image/png", "image/png"))
        ;
    }

    @Test
    public void getPost_badId_returns404() throws Exception {
        when()
            .get("/posts/{postId}", 999)
        .then()
            .statusCode(404)
        ;
    }
}