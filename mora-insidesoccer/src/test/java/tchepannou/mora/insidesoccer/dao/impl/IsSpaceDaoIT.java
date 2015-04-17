package tchepannou.mora.insidesoccer.dao.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tchepannou.mora.core.dao.SpaceDao;
import tchepannou.mora.core.domain.Space;
import tchepannou.mora.core.domain.SpaceType;
import tchepannou.mora.core.domain.User;
import tchepannou.mora.insidesoccer.config.JdbcConfig;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration (classes = {JdbcConfig.class})
@SqlGroup ({
        @Sql (executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:sql/dao/is-clean.sql", "classpath:sql/dao/IsSpaceDao.sql"}),
})
public class IsSpaceDaoIT {
    @Autowired
    private SpaceDao dao;

    private DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Test
    public void testFindById_team() throws Exception {
        // When
        Space result = dao.findById(100);

        // Then
        Space expected = new Space (100, new SpaceType(3), new User(1));
        expected.setDescription("description1");
        expected.setName("name1");
        expected.setLogoUrl("http://google.ca/logo.png");
        expected.setWebsiteUrl("http://google.ca");
        expected.setEmail("info@google.ca");
        expected.setAddress("Montreal, CA");
        expected.setDeleted(false);
        expected.setCreationDate(new Timestamp(fmt.parse("2014-01-01 10:30:55").getTime()));
        expected.setLastUpdate(new Timestamp(fmt.parse("2014-01-01 12:30:55").getTime()));
        assertThat(result, equalTo(expected));
    }

    @Test
    public void testFindById_club() throws Exception {
        // When
        Space result = dao.findById(500);

        // Then
        Space expected = new Space (500, new SpaceType(4), new User(1));
        expected.setDescription("description1");
        expected.setName("name1");
        expected.setLogoUrl("http://google.ca/logo.png");
        expected.setWebsiteUrl("http://google.ca");
        expected.setEmail("info@google.ca");
        expected.setAddress("Montreal, CA");
        expected.setDeleted(false);
        expected.setCreationDate(new Timestamp(fmt.parse("2014-01-01 10:30:55").getTime()));
        expected.setLastUpdate(new Timestamp(fmt.parse("2014-01-01 12:30:55").getTime()));
        assertThat(result, equalTo(expected));
    }

    @Test
    public void testFindById_badId_returnsNull() throws Exception {
        // When
        Space result = dao.findById(9999);

        // Then
        assertThat(result, nullValue());
    }

    @Test
    public void testFindById_deleted_returnsNull() throws Exception {
        // When
        Space result = dao.findById(200);

        // Then
        assertThat(result, nullValue());
    }

    @Test
    public void testFindById_person_returnsNull() throws Exception {
        // When
        Space result = dao.findById(300);

        // Then
        assertThat(result, nullValue());
    }

    @Test
    public void testFindById_family_returnsNull() throws Exception {
        // When
        Space result = dao.findById(400);

        // Then
        assertThat(result, nullValue());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testCreate() throws Exception {
        dao.create(new Space());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUpdate() throws Exception {
        dao.update(new Space());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testDelete() throws Exception {
        dao.delete(new Space());
    }

}