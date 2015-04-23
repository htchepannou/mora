package tchepannou.mora.insidesoccer.dao.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tchepannou.mora.core.dao.MediaTypeDao;
import tchepannou.mora.core.domain.MediaType;
import tchepannou.mora.insidesoccer.config.JdbcConfig;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration (classes = {JdbcConfig.class})
public class IsMediaTypeDaoIT {
    @Autowired
    MediaTypeDao dao;

    @Test
    public void testFindAll() throws Exception {
        List<MediaType> result = new IsMediaTypeDao().findAll();

        assertThat(result, hasSize(3));
        assertThat(result, hasItems(new MediaType(1, "image"),
                    new MediaType(2, "video"),
                    new MediaType(3, "oembed"))
        );
    }



    @Test
    public void testFindById() throws Exception {
        MediaType result = new IsMediaTypeDao().findById(1);

        assertThat(result, equalTo(new MediaType(1, "image")));
    }
}