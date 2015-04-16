package tchepannou.mora.core.dao.jdbc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tchepannou.mora.core.config.JdbcConfig;
import tchepannou.mora.core.dao.AccessTokenDao;
import tchepannou.mora.core.domain.AccessToken;
import tchepannou.mora.core.domain.User;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThan;
import static org.springframework.test.util.MatcherAssertionErrors.assertThat;

@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration (classes = {JdbcConfig.class})
@SqlGroup ({
        @Sql (executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:db/core-clean.sql", "classpath:db/JdbcAccessTokenDao.sql"}),
})
public class JdbcAccessTokenIT {
    @Autowired
    private AccessTokenDao dao;

    private DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Test
    public void testFindByValue() throws Exception {
        // Given

        // When
        AccessToken result = dao.findByValue("bc9a50e1e0085b13c4bba866f6dfe571");

        // Then
        AccessToken expected = new AccessToken (20, new User(2));
        expected.setValue("bc9a50e1e0085b13c4bba866f6dfe571");
        expected.setCreationDate(new Timestamp(fmt.parse("2014-01-01 10:30:55").getTime()));
        expected.setExpiryDate(new Timestamp(fmt.parse("2017-01-01 11:30:55").getTime()));

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
    public void testFindByUserByExpired_Expired() throws Exception {
        // Given

        // When
        List<AccessToken> result = dao.findByUserByExpired(2, true);

        // Then
        AccessToken token1 = new AccessToken (30, new User(2));
        token1.setValue("bc9a50e1e0085b13c4bba866f6dfe574");
        token1.setCreationDate(new Timestamp(fmt.parse("2014-01-03 10:30:55").getTime()));
        token1.setExpiryDate(new Timestamp(fmt.parse("2014-01-04 10:30:55").getTime()));

        AccessToken token2 = new AccessToken (31, new User(2));
        token2.setValue("bc9a50e1e0085b13c4bba866f6dfe575");
        token2.setCreationDate(new Timestamp(fmt.parse("2014-01-03 11:30:55").getTime()));
        token2.setExpiryDate(new Timestamp(fmt.parse("2014-01-04 12:30:55").getTime()));

        assertThat(result, hasSize(2));
        assertThat(result, hasItems(token1, token2));

    }

    @Test
    public void testFindByUserByExpired_NotExpired() throws Exception {
        // Given

        // When
        List<AccessToken> result = dao.findByUserByExpired(2, false);

        // Then
        AccessToken token1 = new AccessToken (20, new User(2));
        token1.setValue("bc9a50e1e0085b13c4bba866f6dfe571");
        token1.setCreationDate(new Timestamp(fmt.parse("2014-01-01 10:30:55").getTime()));
        token1.setExpiryDate(new Timestamp(fmt.parse("2017-01-01 11:30:55").getTime()));

        AccessToken token2 = new AccessToken (21, new User(2));
        token2.setValue("bc9a50e1e0085b13c4bba866f6dfe572");
        token2.setCreationDate(new Timestamp(fmt.parse("2014-01-02 10:30:55").getTime()));
        token2.setExpiryDate(new Timestamp(fmt.parse("2017-01-01 12:30:55").getTime()));

        AccessToken token3 = new AccessToken (22, new User(2));
        token3.setValue("bc9a50e1e0085b13c4bba866f6dfe573");
        token3.setCreationDate(new Timestamp(fmt.parse("2014-01-03 10:30:55").getTime()));
        token3.setExpiryDate(new Timestamp(fmt.parse("2017-01-01 13:30:55").getTime()));

        assertThat(result, hasSize(3));
        assertThat(result, hasItems(token1, token2, token3));

    }

    @Test
    public void testCreate() throws Exception {
        // Given
        AccessToken token = new AccessToken (new User(2));
        token.setValue("xbc9a50e1e0085b13c4bba866f6dfe57");
        token.setCreationDate(fmt.parse("2014-01-03 10:30:55"));
        token.setExpiryDate(fmt.parse("2014-01-04 10:30:55"));

        AccessToken expected = new AccessToken(token);

        // When
        long id = dao.create(token);
        AccessToken result = dao.findByValue("xbc9a50e1e0085b13c4bba866f6dfe57");

        // Then
        assertThat(id, equalTo(result.getId()));

        assertThat(result.getCreationDate().getTime()-expected.getCreationDate().getTime(), lessThan(2000L));
        assertThat(result.getExpiryDate().getTime()-expected.getExpiryDate().getTime(), lessThan(2000L));

        expected.setId(id);
        expected.setCreationDate(result.getCreationDate());
        expected.setExpiryDate(result.getExpiryDate());
        assertThat(result, equalTo(expected));
    }

    @Test
    public void testUpdate() throws Exception {
        // Given
        AccessToken token = dao.findByValue("bc9a50e1e0085b13c4bba866f6dfe571");
        token.setExpiryDate(fmt.parse("2011-01-04 10:30:55"));

        AccessToken expected = new AccessToken(token);

        // When
        dao.update(token);
        AccessToken result = dao.findByValue(token.getValue());

        // Then
        assertThat(result.getExpiryDate().getTime()-expected.getExpiryDate().getTime(), lessThan(2000L));

        expected.setExpiryDate(result.getExpiryDate());
        assertThat(result, equalTo(expected));
    }

    @Test
    public void testUpdateBatch() throws Exception {
        // Given
        AccessToken token1 = dao.findByValue("bc9a50e1e0085b13c4bba866f6dfe571");
        token1.setExpiryDate(fmt.parse("2011-01-04 10:30:55"));
        AccessToken expected1 = new AccessToken(token1);

        AccessToken token2 = dao.findByValue("bc9a50e1e0085b13c4bba866f6dfe573");
        token2.setExpiryDate(fmt.parse("2012-01-04 10:30:55"));
        AccessToken expected2 = new AccessToken(token2);

        // When
        dao.update(Arrays.asList(token1, token2));
        AccessToken result1 = dao.findByValue(token1.getValue());
        AccessToken result2 = dao.findByValue(token2.getValue());

        // Then
        assertThat(result1.getExpiryDate().getTime()/1000, equalTo(expected1.getExpiryDate().getTime()/1000));
        expected1.setExpiryDate(result1.getExpiryDate());
        assertThat(result1, equalTo(expected1));

        assertThat(result2.getExpiryDate().getTime()/1000, equalTo(expected2.getExpiryDate().getTime()/1000));
        expected2.setExpiryDate(result2.getExpiryDate());
        assertThat(result2, equalTo(expected2));

    }
}