package tchepannou.mora.rest.space.controller;

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
import tchepannou.mora.rest.space.Application;
import tchepannou.mora.rest.space.dto.SpaceTypeDto;

import java.util.Arrays;
import java.util.List;

import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

@RunWith (SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration (classes = Application.class)
@WebIntegrationTest
@SqlGroup ({
        @Sql (executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:db/rest-space-clean.sql", "classpath:db/rest-space-populate.sql"}),
})
public class SpaceControllerIT {
    @Value ("${server.port}")
    private int port;

    @Before
    public void setUp (){
        RestAssured.port = this.port;
    }

    @Test
    public void testTypes() throws Exception {
        SpaceTypeDto[] aresult = when()
                .get("/spaces/types")
                .as(SpaceTypeDto[].class);
        List<SpaceTypeDto> result = Arrays.asList(aresult);

        assertThat(result, hasSize(2));
        assertThat(result, hasItems(new SpaceTypeDto(1, "club"), new SpaceTypeDto(2, "team")));
    }
}