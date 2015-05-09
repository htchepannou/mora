package tchepannou.mora.rest.event.controller;

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
import tchepannou.mora.core.dao.EventDao;
import tchepannou.mora.rest.event.Application;

@RunWith (SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration (classes = Application.class)
@WebIntegrationTest
@SqlGroup ({
        @Sql (executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:db/rest-event-clean.sql", "classpath:db/rest-event-populate.sql"}),
})
public class EventControllerIT {
    @Value ("${server.port}")
    private int port;

    @Autowired
    private EventDao dao;



    @Before
    public void setUp (){
        RestAssured.port = this.port;
    }

    @Test
    public void testGetEvent(){

    }
}
