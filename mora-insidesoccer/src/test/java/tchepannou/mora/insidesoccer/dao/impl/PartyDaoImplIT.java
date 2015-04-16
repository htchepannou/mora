package tchepannou.mora.insidesoccer.dao.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tchepannou.mora.insidesoccer.config.JdbcConfig;
import tchepannou.mora.insidesoccer.dao.PartyDao;
import tchepannou.mora.insidesoccer.domain.Party;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration (classes = {JdbcConfig.class})
@SqlGroup ({
        @Sql (executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:sql/dao/is-clean.sql", "classpath:sql/dao/PartyDao.sql"}),
})
public class PartyDaoImplIT {
    @Autowired
    private PartyDao dao;

    private DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //-- Tests
    @Test
    public void testFindById () throws Exception{
        // when
        Party result = dao.findById(10);

        // Then
        Party expected = new Party();
        expected.setId(10);
        expected.setStatus(1);
        expected.setOwnerId(1);
        expected.setTypeId(3);
        expected.setOwnerId(1);
        expected.setDeleted(false);
        expected.setCreationDate(new Timestamp(fmt.parse("2014-01-01 10:30:55").getTime()));
        expected.setDate(new Timestamp(fmt.parse("2014-01-01 12:30:55").getTime()));
        assertThat(result, equalTo(expected));
    }

    @Test
    public void testFindById_notFound_returnsNull () throws Exception{
        // when
        Party result = dao.findById(999);

        // Then
        assertThat(result, nullValue());
    }


    @Test
    public void testFindById_deleted_returnsNull () throws Exception{
        // when
        Party result = dao.findById(11);

        // Then
        assertThat(result, nullValue());
    }
}