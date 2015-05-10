package tchepannou.mora.insidesoccer.dao.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tchepannou.mora.core.dao.EventTypeDao;
import tchepannou.mora.core.domain.EventType;
import tchepannou.mora.insidesoccer.config.JdbcConfig;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration (classes = {JdbcConfig.class})
public class IsEventTypeDaoTest {
    @Autowired
    EventTypeDao dao;

    @Test
    public void testFindAll() throws Exception {
        List<EventType> result = dao.findAll();

        assertThat(result).hasSize(3);
        assertThat(result).contains(
                new EventType(EventType.GAME, "game"),
                new EventType(EventType.PRACTICE, "practice"),
                new EventType(EventType.OTHER, "other")
        );
    }

    @Test
    public void testFindById() throws Exception {
        assertThat(dao.findById(EventType.GAME)).isEqualTo(new EventType(EventType.GAME, "game"));
        assertThat(dao.findById(EventType.PRACTICE)).isEqualTo(new EventType(EventType.PRACTICE, "practice"));
        assertThat(dao.findById(EventType.OTHER)).isEqualTo(new EventType(EventType.OTHER, "other"));
        assertThat(dao.findById(999)).isNull();
    }
}