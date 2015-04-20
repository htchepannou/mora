package tchepannou.mora.core.dao.jdbc;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tchepannou.mora.core.config.JdbcConfig;
import tchepannou.mora.core.dao.PostDao;
import tchepannou.mora.core.domain.Post;
import tchepannou.mora.core.domain.Space;
import tchepannou.mora.core.domain.User;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration (classes = {JdbcConfig.class})
@SqlGroup ({
        @Sql (executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:db/core-clean.sql", "classpath:db/JdbcPostDao.sql"}),
})
public class JdbcPostDaoIT {
    @Autowired
    private PostDao postDao;

    private DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @Test
    public void testFindId() throws Exception {
        // When
        Post result = postDao.findById(100);

        // Then
        Post expected = new Post(100, new Space(100), new User(100));
        expected.setDeleted(false);
        expected.setTitle("title1");
        expected.setSummary("summary1");
        expected.setContent("<p>content1</p>");
        expected.setCreationDate(new Timestamp(fmt.parse("2014-01-01 10:30:55").getTime()));
        expected.setLastUpdate(new Timestamp(fmt.parse("2014-12-01 14:30:55").getTime()));
        assertThat(result, equalTo(expected));
    }

    @Test
    public void testFindId_deleted_returnNull() throws Exception {
        // When
        Post result = postDao.findById(200);

        // Then
        assertThat(result, nullValue());
    }

    @Test
    public void testFindId_notFound_returnNull() throws Exception {
        // When
        Post result = postDao.findById(999);

        // Then
        assertThat(result, nullValue());
    }



    @Test
    public void testFindIdsPublishedForUser() throws Exception {
        // When
        List<Long> result = postDao.findIdsPublishedForUser(300, 1000, 0);

        // Then
        assertThat(result, hasSize(4));
        assertThat(result, hasItems(300L, 301L, 302L, 310L));
    }

    @Test
    public void testFindIdsPublishedForUser_offset() throws Exception {
        // When
        List<Long> result = postDao.findIdsPublishedForUser(400, 5, 5);

        // Then
        assertThat(result, hasSize(5));
        assertThat(result, hasItems(404L, 403L, 402L, 401L, 400L));
    }



    @Test
    public void testCreate() throws Exception {
        // Given
        Date now = new Date();
        Post post = new Post(0, new Space(100), new User(100));
        post.setTitle("new-title1");
        post.setSummary("new-summary1");
        post.setContent("<p>new-content1</p>");
        post.setCreationDate(DateUtils.addDays(now, -1));
        post.setLastUpdate(now);

        Post expected = new Post(post);

        // When
        long id = postDao.create(post);
        Post result = postDao.findById(id);

        // Then
        assertThat(id, equalTo(post.getId()));
        assertThat(result.getCreationDate().getTime() - expected.getCreationDate().getTime(), lessThan(2000L));
        assertThat(result.getLastUpdate().getTime() - expected.getLastUpdate().getTime(), lessThan(2000L));

        expected.setId(id);
        expected.setCreationDate(result.getCreationDate());
        expected.setLastUpdate(result.getLastUpdate());
        assertThat(result, equalTo(expected));
    }



    @Test
    public void testUpdate() throws Exception {
        // Given
        Date now = new Date();
        Post post = postDao.findById(100);
        post.setTitle("new-title1");
        post.setSummary("new-summary1");
        post.setContent("<p>new-content1</p>");
        post.setCreationDate(DateUtils.addDays(now, -1));
        post.setLastUpdate(now);

        Post expected = new Post(post);
        expected.setCreationDate(new Timestamp(fmt.parse("2014-01-01 10:30:55").getTime()));

        // When
        postDao.update(post);
        Post result = postDao.findById(100);

        // Then
        assertThat(result.getLastUpdate().getTime() - expected.getLastUpdate().getTime(), lessThan(2000L));

        expected.setLastUpdate(result.getLastUpdate());
        assertThat(result, equalTo(expected));

    }



    @Test
    public void testDelete() throws Exception {
        // Given
        Post post = new Post(100);

        // When
        postDao.delete(post);

        // Then
        Post result = postDao.findById(100);
        assertThat(result, nullValue());
    }

}