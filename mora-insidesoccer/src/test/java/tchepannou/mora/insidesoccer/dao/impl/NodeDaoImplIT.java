package tchepannou.mora.insidesoccer.dao.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tchepannou.mora.insidesoccer.config.JdbcConfig;
import tchepannou.mora.insidesoccer.dao.NodeDao;
import tchepannou.mora.insidesoccer.domain.Node;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration (classes = {JdbcConfig.class})
@SqlGroup ({
        @Sql (executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:sql/dao/is-clean.sql", "classpath:sql/dao/NodeDao.sql"}),
})
public class NodeDaoImplIT {
    @Autowired
    private NodeDao dao;

    private DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    //-- Test
    @Test
    public void testFindById () throws Exception{
        Node result = dao.findById(100);

        Node expected = new Node(100);
        expected.setChannelId(101);
        expected.setOwnerId(102);
        expected.setTypeId(1);
        expected.setDeleted(false);
        expected.setDate(new Timestamp(fmt.parse("2014-01-01 12:30:55").getTime()));
        expected.setStatus(3);
        assertThat(result, equalTo(expected));
    }

    @Test
    public void testFindById_deleted_shouldReturnNull () throws Exception{
        Node result = dao.findById(200);

        assertThat(result, nullValue());
    }

    @Test
    public void testFindById_notFound_shouldReturnNull () throws Exception{
        Node result = dao.findById(9999);

        assertThat(result, nullValue());
    }
}