package tchepannou.mora.insidesoccer.dao.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tchepannou.mora.core.dao.AttachmentTypeDao;
import tchepannou.mora.core.domain.AttachmentType;
import tchepannou.mora.insidesoccer.config.JdbcConfig;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration (classes = {JdbcConfig.class})
public class IsAttachmentTypeDaoIT {
    @Autowired
    AttachmentTypeDao dao;

    @Test
    public void testFindAll() throws Exception {
        List<AttachmentType> result = new IsAttachmentTypeDao().findAll();

        assertThat(result, hasSize(1));
        assertThat(result, hasItems(new AttachmentType(1, "post")));
    }



    @Test
    public void testFindById() throws Exception {
        AttachmentType result = new IsAttachmentTypeDao().findById(1);

        assertThat(result, equalTo(new AttachmentType(1, "post")));
    }}