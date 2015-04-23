package tchepannou.mora.core.dao.jdbc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tchepannou.mora.core.config.JdbcConfig;
import tchepannou.mora.core.dao.MediaDao;
import tchepannou.mora.core.domain.Media;
import tchepannou.mora.core.domain.Space;
import tchepannou.mora.core.domain.User;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration (classes = {JdbcConfig.class})
@SqlGroup ({
        @Sql (executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:db/core-clean.sql", "classpath:db/JdbcMediaDao.sql"}),
})
public class JdbcMediaDaoIT {
    @Autowired
    private MediaDao mediaDao;

    private DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @Test
    public void testFindId() throws Exception {
        // When
        Media result = mediaDao.findById(100);

        // Then
        Media expected = new Media(100, new Space(1), new User(1));
        expected.setDeleted(false);
        expected.setTitle("title1");
        expected.setDescription("description1");
        expected.setContentType("image/png");
        expected.setOembed(true);
        expected.setSize(1024);
        expected.setUrl("http://t.com/foo.png");
        expected.setThumbnailUrl("http://t.com/foo_small.png");
        expected.setImageUrl("http://t.com/foo_large.png");
        expected.setCreationDate(new Timestamp(fmt.parse("2014-01-01 10:30:55").getTime()));
        expected.setLastUpdate(new Timestamp(fmt.parse("2014-12-01 14:30:55").getTime()));
        assertThat(result, equalTo(expected));
    }

    @Test
    public void testFindId_deleted_returnNull() throws Exception {
        // When
        Media result = mediaDao.findById(200);

        // Then
        assertThat(result, nullValue());
    }

    @Test
    public void testFindId_notFound_returnNull() throws Exception {
        // When
        Media result = mediaDao.findById(999);

        // Then
        assertThat(result, nullValue());
    }


    @Test
    public void testFindByOwnerByAttachmentType() throws Exception{
        // When
        List<Media> result = mediaDao.findByOwnerByAttachmentType(400, 400);

        // Then
        Media expected1 = new Media(410, new Space(1), new User(1));
        expected1.setDeleted(false);
        expected1.setTitle("title1");
        expected1.setDescription("description1");
        expected1.setContentType("image/png");
        expected1.setOembed(true);
        expected1.setSize(1024);
        expected1.setUrl("http://t.com/foo.png");
        expected1.setThumbnailUrl("http://t.com/foo_small.png");
        expected1.setImageUrl("http://t.com/foo_large.png");
        expected1.setCreationDate(new Timestamp(fmt.parse("2014-01-01 10:30:55").getTime()));
        expected1.setLastUpdate(new Timestamp(fmt.parse("2014-12-01 14:30:55").getTime()));

        Media expected2 = new Media(411, new Space(1), new User(1));
        expected2.setDeleted(false);
        expected2.setTitle("title2");
        expected2.setDescription("description2");
        expected2.setContentType("image/png");
        expected2.setOembed(true);
        expected2.setSize(2048);
        expected2.setUrl("http://t.com/foo.png");
        expected2.setThumbnailUrl("http://t.com/foo_small.png");
        expected2.setImageUrl("http://t.com/foo_large.png");
        expected2.setCreationDate(new Timestamp(fmt.parse("2014-01-01 10:30:55").getTime()));
        expected2.setLastUpdate(new Timestamp(fmt.parse("2014-12-01 14:30:55").getTime()));

        assertThat(result, hasSize(2));
        assertThat(result, hasItems(expected1, expected2));
    }
}