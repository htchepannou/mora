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

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
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

    private DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    
    @Before
    public void setUp (){
        RestAssured.port = this.port;
    }

    @Test
    public void getUserPosts() throws Exception {
        when()
            .get("/users/300/posts?limit=100&offset=0")
        .then()
                .log().all()
                .body("id", hasSize(2))
                .body("id", hasItems(300, 301))
                .body("title", hasItems("title1", "title2"))
                .body("summary", hasItems("summary1", "summary2"))
        ;
    }

    @Test
    public void getCurrentUserPosts() throws Exception {
        given()
            .header(SecurityContants.X_AUTH_TOKEN.name(), "bc9a50e1e0085b13c4bba866f6dfe57c")
        .when()
            .get("/users/300/posts?limit=100&offset=0")
        .then()
                .log().all()
                .body("id", hasSize(2))
                .body("id", hasItems(300, 301))
                .body("title", hasItems("title1", "title2"))
                .body("summary", hasItems("summary1", "summary2"))
        ;

    }
}