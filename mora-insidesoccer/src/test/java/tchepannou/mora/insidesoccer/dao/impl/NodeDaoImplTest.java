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
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration (classes = {JdbcConfig.class})
@SqlGroup ({
        @Sql (executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:sql/dao/is-clean.sql", "classpath:sql/dao/NodeDao.sql"}),
})
public class NodeDaoImplTest {
    @Autowired
    private NodeDao dao;

    private DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    //-- Attributes
    @Test
    public void testFindById () throws Exception{
        // When
        Node result = dao.findById(100);

        // Then
        Node expected = new Node();
        expected.setId(100L);
        expected.setTypeId(1);
        expected.setOwnerId(100);
        expected.setChannelId(100);
        expected.setDeleted(false);
        expected.setStatus(1);
        expected.setDate(new Timestamp(fmt.parse("2014-01-01 12:30:55").getTime()));
        assertThat(result, equalTo(expected));
    }

    @Test
    public void testFindById_deleted_returnsNull () throws Exception{
        // When
        Node result = dao.findById(200);

        // Then
        assertThat(result, nullValue());
    }

    @Test
    public void testFindById_notFound_returnsNull () throws Exception{
        // When
        Node result = dao.findById(99);

        // Then
        assertThat(result, nullValue());
    }


    @Test
    public void testFindIdsByRelationshhipTypeByParties() throws Exception {
        // When
        List<Long> result = dao.findIdsByRelationshhipTypeByParties(1, Arrays.asList(300L, 310L), 100, 0);

        // Then
        assertThat(result, hasSize(3));
        assertThat(result, hasItems(300L, 301L, 310L));
    }
}