package tchepannou.mora.insidesoccer.dao.impl;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tchepannou.mora.core.domain.Media;
import tchepannou.mora.core.domain.MediaType;
import tchepannou.mora.core.domain.Space;
import tchepannou.mora.core.domain.User;
import tchepannou.mora.insidesoccer.config.JdbcConfig;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration (classes = {JdbcConfig.class})
@SqlGroup ({
        @Sql (executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:sql/dao/is-clean.sql", "classpath:sql/dao/IsMediaDao.sql"}),
})
public class IsMediaDaoIT {
    @Autowired
    private IsMediaDao dao;

    private DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Test
    public void testFindById() throws Exception {
        // Given
        Media result = dao.findById(100);

        // Then
        Media expected = new Media(100, new Space(100), new User(100));
        expected.setTitle("title1");
        expected.setDescription("This is a content1");
        expected.setUrl("http://www.google.ca/logo.jpg");
        expected.setImageUrl("http://www.google.ca/logo.jpg");
        expected.setDeleted(false);
        expected.setContentType("image/jpeg");
        expected.setTypeId(MediaType.IMAGE);
        expected.setLastUpdate(new Timestamp(fmt.parse("2014-01-01 12:30:55").getTime()));
        assertThat(result, equalTo(expected));
    }

    @Test
    @Ignore
    public void testFindByOwnerByAttachmentType() throws Exception {

    }
}