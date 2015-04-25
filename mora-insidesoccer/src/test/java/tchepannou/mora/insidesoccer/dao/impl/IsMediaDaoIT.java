package tchepannou.mora.insidesoccer.dao.impl;

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
import java.util.List;

import static org.hamcrest.Matchers.*;
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
        Media expected = new Media(100, new Space(100), new User(100), new MediaType(1, "foo"));
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
    public void testFindById_badType_shouldReturnNull() throws Exception {
        // Given
        Media result = dao.findById(200);

        // Then
        assertThat(result, nullValue());
    }

    @Test
    public void testFindById_notFound_shouldReturnNull() throws Exception {
        // Given
        Media result = dao.findById(9999);

        // Then
        assertThat(result, nullValue());
    }

    @Test
    public void testFindByOwnerByAttachmentType() throws Exception {
        // Given
        List<Media> result = dao.findByOwnerByAttachmentType(399, 100);

        // Then
        Media expected1 = new Media(300, new Space(300), new User(300), new MediaType(1, "foo"));
        expected1.setTitle("title1");
        expected1.setDescription("This is a content1");
        expected1.setUrl("http://www.google.ca/logo.jpg");
        expected1.setImageUrl("http://www.google.ca/logo.jpg");
        expected1.setDeleted(false);
        expected1.setContentType("image/jpeg");
        expected1.setTypeId(MediaType.IMAGE);
        expected1.setLastUpdate(new Timestamp(fmt.parse("2014-01-01 12:30:55").getTime()));

        Media expected2 = new Media(310, new Space(300), new User(300), new MediaType(1, "foo"));
        expected2.setTitle("title1");
        expected2.setDescription("This is a content1");
        expected2.setUrl("http://www.google.ca/logo.jpg");
        expected2.setImageUrl("http://www.google.ca/logo.jpg");
        expected2.setDeleted(false);
        expected2.setContentType("image/jpeg");
        expected2.setTypeId(MediaType.IMAGE);
        expected2.setLastUpdate(new Timestamp(fmt.parse("2014-01-01 12:30:55").getTime()));
        
        assertThat(result, hasSize(2));
        assertThat(result, hasItems(expected1));
    }
}