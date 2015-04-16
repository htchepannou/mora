package tchepannou.mora.insidesoccer.dao.impl;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tchepannou.mora.core.domain.Space;
import tchepannou.mora.insidesoccer.config.JdbcConfig;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration (classes = {JdbcConfig.class})
public class IsSpaceDaoTest {
    @Autowired
    private IsSpaceDao dao;

    private DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Test
    @Ignore
    public void testFindById() throws Exception {

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