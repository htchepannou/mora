package tchepannou.mora.core.dao.jdbc;

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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.util.MatcherAssertionErrors.assertThat;


@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JdbcConfig.class})
@SqlGroup ({
        @Sql (executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:db/clean.sql", "classpath:db/populate.sql"}),
})
public class JdbcUserDaoIT {
    @Autowired
    private UserDao userDao;


    @Test
    public void testFindById() throws Exception {
        // Given
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        User expected = new User (1);
        expected.setUsername("ray.sponsible");
        expected.setEmail("ray.sponsible@gmail.com");
        expected.setLastName("Sponsible");
        expected.setFirstName("Ray");
        expected.setDeleted(false);
        expected.setCreationDate(new Timestamp(fmt.parse("2014-01-01 10:30:55").getTime()));
        expected.setLastUpdate(new Timestamp(fmt.parse("2014-12-01 14:30:55").getTime()));

        // When
        User user = userDao.findById(1);

        // Then
        assertThat(user, equalTo(expected));
    }

//    @Test
//    public void testFindByEmail() throws Exception {
//
//    }
//
//    @Test
//    public void testFindByUsername() throws Exception {
//
//    }
//
//    @Test
//    public void testSave() throws Exception {
//
//    }
}