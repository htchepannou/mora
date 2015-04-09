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
import tchepannou.mora.core.dao.PasswordDao;
import tchepannou.mora.core.domain.Password;
import tchepannou.mora.core.domain.User;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.util.MatcherAssertionErrors.assertThat;

@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration (classes = {JdbcConfig.class})
@SqlGroup ({
        @Sql (executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:db/core-clean.sql", "classpath:db/core-populate.sql"}),
})
public class JdbcPasswordDaoIT {
    @Autowired
    private PasswordDao dao;

    private DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Test
    public void testFindByUser() throws Exception {
        // Given

        // When
        Password result = dao.findByUser(1);

        // Then
        Password expected = new Password (1, new User(1));
        expected.setValue("bc9a50e1e0085b13c4bba866f6dfe57c");
        expected.setCreationDate(new Timestamp(fmt.parse("2014-01-01 10:30:55").getTime()));
        expected.setLastUpdate(new Timestamp(fmt.parse("2014-12-01 14:30:55").getTime()));

        assertThat(result, equalTo(expected));
    }

    @Test
    public void testCreate() throws Exception {
        //  Given
        Date now = new Date ();

        Password password = new Password(new User(2));
        password.setValue("_secret_");
        password.setCreationDate(DateUtils.addDays(now, -1));
        password.setLastUpdate(now);

        Password expected = new Password(password);

        // When
        long id = dao.create(password);
        Password result  = dao.findById(id);

        // Then
        assertThat(id, equalTo(password.getId()));

        expected.setId(id);
        expected.setLastUpdate(result.getLastUpdate());
        expected.setCreationDate(result.getCreationDate());
        assertThat(result, equalTo(expected));
    }

    @Test
    public void testUpdate() throws Exception {
        //  Given
        Date now = new Date ();

        Password password = dao.findById(1);
        password.setValue("_secret_");
        password.setLastUpdate(now);

        Password expected = new Password(password);

        // When
        dao.update(password);
        Password result = dao.findById(1);

        // Then
        expected.setLastUpdate(result.getLastUpdate());

        assertThat(result, equalTo(expected));

    }
}