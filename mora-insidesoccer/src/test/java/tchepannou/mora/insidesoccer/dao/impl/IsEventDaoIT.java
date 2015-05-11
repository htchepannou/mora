package tchepannou.mora.insidesoccer.dao.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tchepannou.mora.core.domain.Event;
import tchepannou.mora.core.domain.EventType;
import tchepannou.mora.core.domain.Space;
import tchepannou.mora.core.domain.User;
import tchepannou.mora.insidesoccer.config.JdbcConfig;
import tchepannou.mora.insidesoccer.domain.IsEventType;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration (classes = {JdbcConfig.class})
@SqlGroup ({
        @Sql (executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:sql/dao/is-clean.sql", "classpath:sql/dao/IsEventDao.sql"}),
})
public class IsEventDaoIT {
    @Autowired
    private IsEventDao dao;


    //-- Test
    @Test
    public void testFindById() throws Exception {
        // Given
        Event result = dao.findById(101);

        // Then
        Event expected = new Event(101, new EventType(IsEventType.MATCH), new Space(100), new User(100));
        expected.setTitle("title1");
        expected.setNotes("This is a content1");
        expected.setDeleted(false);
        expected.setLastUpdate(new Timestamp(10000));
        expected.setStartDateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2014-01-01 10:30"));
        expected.setEndDateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2014-01-01 15:30"));
        assertThat(result, equalTo(expected));
    }

    @Test
    public void testFindById_badType_returnsNull() throws Exception {
        // Given
        Event result = dao.findById(200);

        // Then
        assertThat(result, nullValue());
    }

    @Test
    public void testFindById_badId_returnsNull() throws Exception {
        // Given
        Event result = dao.findById(999);

        // Then
        assertThat(result, nullValue());
    }

    @Test
    public void testFindIdsUpcomingForUser() throws Exception {
        List<Long> result = dao.findIdsUpcomingForUser(300, 100, 0);

        assertThat(result, hasSize(2));
        assertThat(result, hasItems(301L, 311L));
    }

}