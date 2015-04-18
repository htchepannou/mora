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
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.util.MatcherAssertionErrors.assertThat;

@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration (classes = {JdbcConfig.class})
@SqlGroup ({
        @Sql (executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:sql/dao/is-clean.sql", "classpath:sql/dao/IsAccessTokenDao.sql"}),
})
public class IsAccessTokenDaoIT {
    @Autowired
    private AccessTokenDao accessTokenDao;

    private DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //-- Before
    @Test
    public void testFindByValue() throws Exception {
        // When
        AccessToken result = accessTokenDao.findByValue("100");

        // Then
        AccessToken expected = new AccessToken (100, new User(100));
        expected.setValue("100");
        expected.setCreationDate(new Timestamp(fmt.parse("2014-01-01 10:30:55").getTime()));
        expected.setExpiryDate(null);

        assertThat(result, equalTo(expected));
    }

    @Test
    public void testFindByValue_notFound_returnNull() throws Exception {
        // Given

        // When
        AccessToken result = accessTokenDao.findByValue("???");

        // Then
        assertThat(result, nullValue());
    }


    @Test
    public void testFindByUserByExpired_notExpired() throws Exception {
        // When
        List<AccessToken> result = accessTokenDao.findByUserByExpired(200, false);

        // Then
        AccessToken expected1 = new AccessToken (200, new User(200));
        expected1.setValue("200");
        expected1.setCreationDate(new Timestamp(fmt.parse("2014-01-01 10:30:55").getTime()));
        expected1.setExpiryDate(null);

        AccessToken expected2 = new AccessToken (201, new User(200));
        expected2.setValue("201");
        expected2.setCreationDate(new Timestamp(fmt.parse("2014-01-01 10:30:55").getTime()));
        expected2.setExpiryDate(null);

        assertThat(result, hasSize(2));
        assertThat(result, hasItems(expected1, expected2));
    }

    @Test
    public void testFindByUserByExpired_expired() throws Exception {
        // When
        List<AccessToken> result = accessTokenDao.findByUserByExpired(200, true);

        // Then
        AccessToken expected1 = new AccessToken (202, new User(200));
        expected1.setValue("202");
        expected1.setCreationDate(new Timestamp(fmt.parse("2014-01-01 10:30:55").getTime()));
        expected1.setExpiryDate(new Timestamp(fmt.parse("2014-01-01 12:30:55").getTime()));

        assertThat(result, hasSize(1));
        assertThat(result, hasItems(expected1));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUpdate() throws Exception {
        accessTokenDao.update(new AccessToken());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUpdateBatch() throws Exception {
        accessTokenDao.update(Arrays.asList(new AccessToken()));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testCreate() throws Exception {
        accessTokenDao.create(new AccessToken());
    }
}