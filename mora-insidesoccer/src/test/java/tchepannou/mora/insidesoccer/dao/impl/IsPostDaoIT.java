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
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration (classes = {JdbcConfig.class})
@SqlGroup ({
        @Sql (executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:sql/dao/is-clean.sql", "classpath:sql/dao/IsPostDao.sql"}),
})
public class IsPostDaoIT {
    @Autowired
    private IsPostDao dao;

    private DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    //-- Test
    @Test
    public void testFindById() throws Exception {
        // Given
        Post result = dao.findById(101);

        // Then
        Post expected = new Post(101, new Space(100), new User(100));
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
        assertThat(result, hasItems(300L, 311L));
    }
    
    
    @Test
    public void testFindByIds () throws Exception{
        // Given
        List<Post> result = dao.findByIds(Arrays.asList(401L, 411L));

        // Then
        Post expected1 = new Post(401, new Space(400), new User(400));
        expected1.setTitle("title1");
        expected1.setSummary("This is a content1");
        expected1.setContent("This is a content1");
        expected1.setDeleted(false);
        expected1.setLastUpdate(new Timestamp(fmt.parse("2014-01-01 12:30:55").getTime()));
        
        Post expected2 = new Post(411, new Space(410), new User(410));
        expected2.setTitle("title2");
        expected2.setSummary("This is a content2");
        expected2.setContent("This is a content2");
        expected2.setDeleted(false);
        expected2.setLastUpdate(new Timestamp(fmt.parse("2014-01-01 12:30:55").getTime()));
        
        assertThat(result, hasSize(2));
        assertThat(result, hasItems(expected1, expected2));
        
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testCreate() throws Exception {
        dao.create(new Post(1));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUpdate() throws Exception {
        dao.update(new Post(1));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testDelete() throws Exception {
        dao.delete(new Post(1));
    }    
}