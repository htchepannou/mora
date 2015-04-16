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
import tchepannou.mora.core.dao.UserDao;
import tchepannou.mora.core.domain.User;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.util.MatcherAssertionErrors.assertThat;


@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration (classes = {JdbcConfig.class})
@SqlGroup ({
        @Sql (executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:db/core-clean.sql", "classpath:db/JdbcUserDao.sql"}),
})
public class JdbcUserDaoIT {
    @Autowired
    private UserDao userDao;

    private DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @Test
    public void testFindById() throws Exception {
        // Given

        // When
        User result = userDao.findById(1);

        // Then
        User expected = new User (1);
        expected.setUsername("ray.sponsible");
        expected.setEmail("ray.sponsible@gmail.com");
        expected.setLastName("Sponsible");
        expected.setFirstName("Ray");
        expected.setDeleted(false);
        expected.setCreationDate(new Timestamp(fmt.parse("2014-01-01 10:30:55").getTime()));
        expected.setLastUpdate(new Timestamp(fmt.parse("2014-12-01 14:30:55").getTime()));
        assertThat(result, equalTo(expected));
    }
    @Test
    public void testFindById_notFound_shouldReturnsNull() throws Exception {
        // Given

        // When
        User result = userDao.findById(999);

        // Then
        assertThat(result, nullValue());
    }
    @Test
    public void testFindById_deleted_shouldReturnsNull() throws Exception {
        // Given

        // When
        User result = userDao.findById(10);

        // Then
        assertThat(result, nullValue());
    }

    @Test
    public void testFindByEmail() throws Exception {
        // Given

        // When
        List<User> result = userDao.findByEmail("ray.sponsible@gmail.com");

        // Then
        User user1 = new User (1);
        user1.setUsername("ray.sponsible");
        user1.setEmail("ray.sponsible@gmail.com");
        user1.setLastName("Sponsible");
        user1.setFirstName("Ray");
        user1.setDeleted(false);
        user1.setCreationDate(new Timestamp(fmt.parse("2014-01-01 10:30:55").getTime()));
        user1.setLastUpdate(new Timestamp(fmt.parse("2014-12-01 14:30:55").getTime()));

        User user2 = new User (11);
        user2.setUsername("##ray.sponsible");
        user2.setEmail("ray.sponsible@gmail.com");
        user2.setLastName("Sponsible");
        user2.setFirstName("Ray");
        user2.setDeleted(false);
        user2.setCreationDate(new Timestamp(fmt.parse("2011-01-01 10:30:55").getTime()));
        user2.setLastUpdate(new Timestamp(fmt.parse("2012-12-01 14:30:55").getTime()));

        assertThat(result, hasSize(2));
        assertThat(result, hasItems(user1, user2));
    }

    @Test
    public void testFindByEmail_notFound_shouldReturnEmptyList() throws Exception {
        // Given

        // When
        List<User> result = userDao.findByEmail("__ray.sponsible__@gmail.com");

        // Then
        assertThat(result, hasSize(0));
    }

    //    @Test
    public void testFindByUsername() throws Exception {
        // Given

        // When
        List<User> result = userDao.findByEmail("#ray.sponsible@gmail.com");

        // Then
        User user1 = new User (10);
        user1.setUsername("#ray.sponsible");
        user1.setEmail("ray.sponsible@gmail.com");
        user1.setLastName("Sponsible");
        user1.setFirstName("Ray");
        user1.setDeleted(true);
        user1.setCreationDate(new Timestamp(fmt.parse("2011-01-01 10:30:55").getTime()));
        user1.setLastUpdate(new Timestamp(fmt.parse("2012-12-01 14:30:55").getTime()));

        assertThat(result, hasSize(1));
        assertThat(result, hasItems(user1));

    }


    @Test
    public void testFindByUsername_notFound_shouldReturnEmptyList() throws Exception {
        // Given

        // When
        List<User> result = userDao.findByUsername("__ray.sponsible__");

        // Then
        assertThat(result, hasSize(0));
    }

    @Test
    public void testCreate() throws Exception {
        // Given
        Date now = new Date();
        String username =  String.valueOf(System.currentTimeMillis());
        User user = new User ();
        user.setUsername(username);
        user.setEmail(username + "@gmail.com");
        user.setDeleted(false);
        user.setLastName("Tchepannou");
        user.setFirstName("Herve");
        user.setCreationDate(DateUtils.addDays(now, -1));
        user.setLastUpdate(now);

        User expected = new User(user);

        // When
        long id = userDao.create(user);
        User result = userDao.findById(id);

        // Then
        assertThat(user.getId(), equalTo(id));

        assertThat(result.getCreationDate().getTime()-expected.getCreationDate().getTime(), lessThan(2000L));
        assertThat(result.getLastUpdate().getTime()-expected.getLastUpdate().getTime(), lessThan(2000L));

        expected.setId(id);
        expected.setCreationDate(result.getCreationDate());
        expected.setLastUpdate(result.getLastUpdate());
        assertThat(result, equalTo(expected));
    }


    @Test
    public void testUpdate() throws Exception {
        // Given
        Date now = new Date();
        String username =  String.valueOf(System.currentTimeMillis());
        User user = new User ();
        user.setId(1);
        user.setUsername(username);
        user.setEmail(username + "@gmail.com");
        user.setLastName("Tchepannou");
        user.setFirstName("Herve");
        user.setCreationDate(DateUtils.addDays(now, -1));
        user.setLastUpdate(now);

        User expected = new User(user);
        expected.setCreationDate(new Timestamp(fmt.parse("2014-01-01 10:30:55").getTime()));

        // When
        userDao.update(user);
        User result = userDao.findById(1);

        // Then
        assertThat(result.getLastUpdate().getTime()-expected.getLastUpdate().getTime(), lessThan(2000L));

        expected.setLastUpdate(result.getLastUpdate());
        assertThat(result, equalTo(expected));
    }



    @Test
    public void testDelete() throws Exception {
        // Given
        User user = userDao.findById(1);

        User expected = new User (user);
        expected.setDeleted(true);

        // When
        userDao.delete(user);
        User result = userDao.findById(1);

        // Then
        assertThat(user, equalTo(expected));
        assertThat(result, nullValue());
    }
}