package tchepannou.mora.core.dao.jdbc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tchepannou.mora.core.config.JdbcConfig;
import tchepannou.mora.core.dao.SpaceDao;
import tchepannou.mora.core.domain.Space;
import tchepannou.mora.core.domain.SpaceType;
import tchepannou.mora.core.domain.User;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThan;
import static org.springframework.test.util.MatcherAssertionErrors.assertThat;

@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration (classes = {JdbcConfig.class})
@SqlGroup ({
        @Sql (executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:db/core-clean.sql", "classpath:db/JdbcSpaceDao.sql"}),
})
public class JdbcSpaceDaoIT {
    @Autowired
    private SpaceDao dao;

    private DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Test
    public void testFindById () throws Exception{
        // Given

        // When
        Space result = dao.findById(1);

        // Then
        Space expected = new Space (1, new SpaceType(1), new User(1));
        expected.setName("New York Soccer Club");
        expected.setAbbreviation("NYSC");
        expected.setDescription("Best soccer club");
        expected.setEmail("info@newyorksoccerclub.org");
        expected.setLogoUrl("http://img.com/nysc.png");
        expected.setWebsiteUrl("http://newyorksoccerclub.org");
        expected.setDeleted(false);
        expected.setCreationDate(new Timestamp(fmt.parse("2014-01-01 10:30:55").getTime()));
        expected.setLastUpdate(new Timestamp(fmt.parse("2014-12-01 14:30:55").getTime()));
        assertThat(result, equalTo(expected));
    }

    @Test
    public void testFindById_notFound_returnsNull () throws Exception{
        // Given

        // When
        Space result = dao.findById(999);

        // Then
        assertThat(result, nullValue());
    }

    @Test
    public void testFindById_deleted_returnsNull () throws Exception{
        // Given

        // When
        Space result = dao.findById(2);

        // Then
        assertThat(result, nullValue());
    }


    @Test
    public void testFindByIds () throws Exception{
        // Given

        // When
        List<Space> result = dao.findByIds(Arrays.asList(1L, 2L, 3L));

        // Then
        Space expected1 = new Space (1, new SpaceType(1), new User(1));
        expected1.setName("New York Soccer Club");
        expected1.setAbbreviation("NYSC");
        expected1.setDescription("Best soccer club");
        expected1.setEmail("info@newyorksoccerclub.org");
        expected1.setLogoUrl("http://img.com/nysc.png");
        expected1.setWebsiteUrl("http://newyorksoccerclub.org");
        expected1.setDeleted(false);
        expected1.setCreationDate(new Timestamp(fmt.parse("2014-01-01 10:30:55").getTime()));
        expected1.setLastUpdate(new Timestamp(fmt.parse("2014-12-01 14:30:55").getTime()));

        Space expected2 = new Space (3, new SpaceType(1), new User(1));
        expected2.setName("Canadien de Montreal");
        expected2.setAbbreviation("CH");
        expected2.setDescription("Best hockey club");
        expected2.setEmail("info@canadiensdemontral.com");
        expected2.setLogoUrl("http://canadiensdemontral.com/logo.png");
        expected2.setWebsiteUrl("http://canadiensdemontral.com");
        expected2.setDeleted(false);
        expected2.setCreationDate(new Timestamp(fmt.parse("2014-01-01 10:30:55").getTime()));
        expected2.setLastUpdate(new Timestamp(fmt.parse("2014-12-01 14:30:55").getTime()));

        assertThat(result, hasSize(2));
        assertThat(result, hasItems(expected1, expected2));
    }


    @Test
    public void testCreate() throws Exception{
        // Given
        Date now = new Date ();

        Space space = new Space(new SpaceType(1), new User(1));
        space.setName("Foo Bar");
        space.setAbbreviation("FB");
        space.setCreationDate(now);
        space.setEmail("info@foobar.com");
        space.setAddress("340 pascal");
        space.setDescription("This is a nice test :-)");
        space.setLastUpdate(now);
        space.setLogoUrl("http://me.com/1.png");
        space.setWebsiteUrl("http://www.me.ca");

        Space expected = new Space(space);

        // When
        long id = dao.create(space);
        Space result = dao.findById(id);

        // Then
        assertThat(space.getId(), equalTo(id));

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
        Date now = new Date ();

        Space space = dao.findById(1);
        space.setName("Foo Bar");
        space.setAbbreviation("FB");
        space.setEmail("info@foobar.com");
        space.setAddress("340 pascal");
        space.setDescription("This is a nice test :-)");
        space.setLastUpdate(now);
        space.setLogoUrl("http://me.com/1.png");
        space.setWebsiteUrl("http://www.me.ca");

        Space expected = new Space(space);

        // When
        dao.update(space);
        Space result = dao.findById(1);

        // Then
        assertThat(result.getLastUpdate().getTime()-expected.getLastUpdate().getTime(), lessThan(2000L));

        expected.setLastUpdate(result.getLastUpdate());
        assertThat(result, equalTo(expected));
    }

    public void testDelete (){
        // Given
        Space space = dao.findById(1);

        // When
        dao.delete(space);
        Space result = dao.findById(1);

        // THen
        assertThat(space.isDeleted(), equalTo(true));
        assertThat(result, nullValue());
    }
}