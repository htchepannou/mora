package tchepannou.mora.core.dao.jdbc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tchepannou.mora.core.config.JdbcConfig;
import tchepannou.mora.core.dao.EventDao;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration (classes = {JdbcConfig.class})
@SqlGroup ({
        @Sql (executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:db/core-clean.sql", "classpath:db/JdbcEventDao.sql"}),
})
public class JdbcEventDaoIT {
    @Autowired
    private EventDao dao;


    @Test
    public void testFindIdsUpcomingForUser() throws Exception {
        List<Long> ids = dao.findIdsUpcomingForUser(300, 100, 0);

        assertThat(ids).hasSize(2);
        assertThat(ids).contains(300L, 301L);
    }
}