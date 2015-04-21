package tchepannou.mora.rest.post.controller;

import com.jayway.restassured.RestAssured;
import org.junit.Before;
import org.junit.Ignore;
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
import tchepannou.mora.rest.post.Application;
import tchepannou.mora.rest.post.dto.PostSummaryDto;

import static com.jayway.restassured.RestAssured.when;

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
    @Ignore
    public void getUserPosts() throws Exception {
        PostSummaryDto[] result = when().get("/users/300/posts?limit=100&offset=0").as(PostSummaryDto[].class);
    }
}