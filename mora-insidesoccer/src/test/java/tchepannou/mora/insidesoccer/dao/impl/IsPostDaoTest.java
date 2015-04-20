package tchepannou.mora.insidesoccer.dao.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tchepannou.mora.core.domain.Post;
import tchepannou.mora.core.domain.Space;
import tchepannou.mora.core.domain.User;
import tchepannou.mora.insidesoccer.config.JdbcConfig;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration (classes = {JdbcConfig.class})
@SqlGroup ({
        @Sql (executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:sql/dao/is-clean.sql", "classpath:sql/dao/IsPostDao.sql"}),
})
public class IsPostDaoTest {
    @Autowired
    private IsPostDao dao;

    private DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    //-- Test
    @Test
    public void testFindById() throws Exception {
        // Given
        Post result = dao.findById(100);

        // Then
        Post expected = new Post(100, new Space(100), new User(100));
        expected.setTitle("title1");
        expected.setSummary("This is a content1");
        expected.setContent("This is a content1");
        expected.setDeleted(false);
        expected.setLastUpdate(new Timestamp(fmt.parse("2014-01-01 12:30:55").getTime()));
        assertThat(result, equalTo(expected));
    }

    @Test
    public void testFindById_badType_returnsNull() throws Exception {
        // Given
        Post result = dao.findById(200);

        // Then
        assertThat(result, nullValue());
    }

    @Test
    public void testFindById_badId_returnsNull() throws Exception {
        // Given
        Post result = dao.findById(999);

        // Then
        assertThat(result, nullValue());
    }

    @Test
    public void testFindIdsPublishedForUser() throws Exception {
        List<Long> result = dao.findIdsPublishedForUser(300, 100, 0);

        assertThat(result, hasSize(2));
        assertThat(result, hasItems(300L, 310L));
    }
}