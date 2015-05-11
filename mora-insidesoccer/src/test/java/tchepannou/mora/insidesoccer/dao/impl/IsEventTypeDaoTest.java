package tchepannou.mora.insidesoccer.dao.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tchepannou.mora.core.dao.EventTypeDao;
import tchepannou.mora.core.domain.EventType;
import tchepannou.mora.insidesoccer.config.JdbcConfig;
import tchepannou.mora.insidesoccer.domain.IsEventType;

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

        assertThat(result).hasSize(5);
        assertThat(result).contains(
                new EventType(IsEventType.MATCH, "match"),
                new EventType(IsEventType.TRAINING, "training"),
                new EventType(IsEventType.TOURNAMENT, "tournament"),
                new EventType(IsEventType.EVENT, "event"),
                new EventType(IsEventType.OTHER, "other")
        );
    }

    @Test
    public void testFindById() throws Exception {
        assertThat(dao.findById(IsEventType.TRAINING)).isEqualTo(new EventType(IsEventType.TRAINING, "training"));
        assertThat(dao.findById(IsEventType.MATCH)).isEqualTo(new EventType(IsEventType.MATCH, "match"));
        assertThat(dao.findById(IsEventType.TOURNAMENT)).isEqualTo(new EventType(IsEventType.TOURNAMENT, "tournament"));
        assertThat(dao.findById(IsEventType.EVENT)).isEqualTo(new EventType(IsEventType.EVENT, "event"));
        assertThat(dao.findById(IsEventType.OTHER)).isEqualTo(new EventType(IsEventType.OTHER, "other"));
    }
}