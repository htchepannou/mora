package tchepannou.mora.core.dao.jdbc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tchepannou.mora.core.config.JdbcConfig;
import tchepannou.mora.core.dao.EventTypeDao;
import tchepannou.mora.core.domain.EventType;

import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration (classes = {JdbcConfig.class})
@SqlGroup ({
        @Sql (executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:db/core-clean.sql", "classpath:db/JdbcEventTypeDao.sql"}),
})
public class JdbcEventTypeDaoIT {
    @Autowired
    private EventTypeDao dao;


    @Test
    public void testFindById (){
        // Given

        // When
        EventType result = dao.findById(1);

        // Then
        assertThat(result, equalTo(new EventType(1, "game")));
    }
    @Test
    public void testFindById_notFound_returnsNull (){
        // Given

        // When
        EventType result = dao.findById(999);

        // Then
        assertThat(result, nullValue());
    }

    @Test
    public void testFindAll (){
        // Given

        // When
        List<EventType> result = dao.findAll();

        // Then
        assertThat(result, hasSize(2));
        assertThat(result, hasItems(new EventType(1, "game"), new EventType(2, "practice")));
    }
}
