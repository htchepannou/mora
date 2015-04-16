package tchepannou.mora.insidesoccer.dao.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tchepannou.mora.core.dao.AccessTokenDao;
import tchepannou.mora.core.domain.AccessToken;
import tchepannou.mora.core.domain.User;
import tchepannou.mora.insidesoccer.config.JdbcConfig;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.springframework.test.util.MatcherAssertionErrors.assertThat;

@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration (classes = {JdbcConfig.class})
@SqlGroup ({
        @Sql (executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:sql/dao/is-clean.sql", "classpath:sql/dao/IsAccessTokenDao.sql"}),
})
public class IsAccessTokenDaoIT {
    @Autowired
    private AccessTokenDao dao;

    private DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //-- Before
    @Test
    public void testFindByValue() throws Exception {
        // Given

        // When
        AccessToken result = dao.findByValue("1");

        // Then
        AccessToken expected = new AccessToken (1, new User(1));
        expected.setValue("1");
        expected.setCreationDate(new Timestamp(fmt.parse("2014-01-01 10:30:55").getTime()));
        expected.setExpiryDate(null);

        assertThat(result, equalTo(expected));
    }

    @Test
    public void testFindByValue_notFound_returnNull() throws Exception {
        // Given

        // When
        AccessToken result = dao.findByValue("???");

        // Then
        assertThat(result, nullValue());
    }


    @Test
    public void testFindByUserByExpired() throws Exception {

    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUpdate() throws Exception {
        dao.update(new AccessToken());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUpdateBatch() throws Exception {
        dao.update(Arrays.asList(new AccessToken()));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testCreate() throws Exception {
        dao.create(new AccessToken());
    }
}