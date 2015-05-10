package tchepannou.mora.insidesoccer.dao.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tchepannou.mora.core.dao.UserDao;
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
        @Sql (executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:sql/dao/is-clean.sql", "classpath:sql/dao/IsUserDao.sql"}),
})
public class IsUserDaoIT {
    @Autowired
    private UserDao dao;

    private DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @Test
    public void testFindById() throws Exception {
        // When
        User result = dao.findById(100);

        // Then
        User expected = new User(100);
        expected.setEmail("ray.sponsible@gmail.com");
        expected.setUsername("ray.sponsible");
        expected.setFirstName("Ray");
        expected.setLastName("Sponsible");
        expected.setDeleted(false);
        expected.setCreationDate(new Timestamp(fmt.parse("2014-01-01 10:30:55").getTime()));
        expected.setLastUpdate(new Timestamp(fmt.parse("2014-01-01 12:30:55").getTime()));

        assertThat(result, equalTo(expected));
    }

    @Test
    public void testFindById_notFound_returnNull() throws Exception {
        // When
        User result = dao.findById(999);

        // Then
        assertThat(result, nullValue());
    }

    @Test
    public void testFindById_badType_returnNull() throws Exception {
        // When
        User result = dao.findById(500);

        // Then
        assertThat(result, nullValue());
    }

    @Test
    public void testFindById_deletedParty_returnNull() throws Exception {
        // When
        User result = dao.findById(200);

        // Then
        assertThat(result, nullValue());
    }

    @Test
    public void testFindByIds () throws Exception{
        // When
        List<User> result = dao.findByIds(Arrays.asList(300L, 310L));

        // Then
        User expected1 = new User(300);
        expected1.setEmail("duplicate1@gmail.com");
        expected1.setUsername("duplicate");
        expected1.setDeleted(false);
        expected1.setCreationDate(new Timestamp(fmt.parse("2014-01-01 10:30:55").getTime()));
        expected1.setLastUpdate(new Timestamp(fmt.parse("2014-01-01 12:30:55").getTime()));

        User expected2 = new User(310);
        expected2.setEmail("duplicate2@gmail.com");
        expected2.setUsername("duplicate");
        expected2.setDeleted(false);
        expected2.setCreationDate(new Timestamp(fmt.parse("2014-01-01 10:30:55").getTime()));
        expected2.setLastUpdate(new Timestamp(fmt.parse("2014-01-01 12:30:55").getTime()));

        assertThat(result, hasSize(2));
        assertThat(result, hasItems(expected2));
    }

    @Test
    public void testFindByUsername() throws Exception {
        // When
        List<User> result = dao.findByUsername("duplicate");

        // Then
        User expected1 = new User(300);
        expected1.setEmail("duplicate1@gmail.com");
        expected1.setUsername("duplicate");
        expected1.setDeleted(false);
        expected1.setCreationDate(new Timestamp(fmt.parse("2014-01-01 10:30:55").getTime()));
        expected1.setLastUpdate(new Timestamp(fmt.parse("2014-01-01 12:30:55").getTime()));

        User expected2 = new User(310);
        expected2.setEmail("duplicate2@gmail.com");
        expected2.setUsername("duplicate");
        expected2.setDeleted(false);
        expected2.setCreationDate(new Timestamp(fmt.parse("2014-01-01 10:30:55").getTime()));
        expected2.setLastUpdate(new Timestamp(fmt.parse("2014-01-01 12:30:55").getTime()));
        
        assertThat(result, hasSize(2));
        assertThat(result, hasItems(expected2));
    }

    @Test
    public void testFindByEmail() throws Exception {
        // When
        List<User> result = dao.findByEmail("duplicate@gmail.com");

        // Then
        User expected1 = new User(400);
        expected1.setEmail("duplicate@gmail.com");
        expected1.setUsername("duplicate1");
        expected1.setDeleted(false);
        expected1.setCreationDate(new Timestamp(fmt.parse("2014-01-01 10:30:55").getTime()));
        expected1.setLastUpdate(new Timestamp(fmt.parse("2014-01-01 12:30:55").getTime()));

        User expected2 = new User(410);
        expected2.setEmail("duplicate@gmail.com");
        expected2.setUsername("duplicate2");
        expected2.setDeleted(false);
        expected2.setCreationDate(new Timestamp(fmt.parse("2014-01-01 10:30:55").getTime()));
        expected2.setLastUpdate(new Timestamp(fmt.parse("2014-01-01 12:30:55").getTime()));

        assertThat(result, hasSize(2));
        assertThat(result, hasItems(expected2));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testCreate() throws Exception {
        dao.create(new User(1));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUpdate() throws Exception {
        dao.update(new User(1));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testDelete() throws Exception {
        dao.delete(new User(1));
    }
}