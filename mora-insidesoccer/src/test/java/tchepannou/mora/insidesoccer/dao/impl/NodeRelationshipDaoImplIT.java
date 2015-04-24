package tchepannou.mora.insidesoccer.dao.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tchepannou.mora.insidesoccer.config.JdbcConfig;
import tchepannou.mora.insidesoccer.dao.NodePartyRelationshipDao;
import tchepannou.mora.insidesoccer.domain.NodePartyRelationship;

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
        @Sql (executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:sql/dao/is-clean.sql", "classpath:sql/dao/NodeRelationshipDao.sql"}),
})
public class NodeRelationshipDaoImplIT {
    @Autowired
    private NodePartyRelationshipDao dao;

    private DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    //-- Attributes
    @Test
    public void testFindById () throws Exception{
        // When
        NodePartyRelationship result = dao.findById(101);

        // Then
        NodePartyRelationship expected = new NodePartyRelationship();
        expected.setId(101L);
        expected.setTypeId(1);
        expected.setOwnerId(100);
        expected.setChannelId(100);
        expected.setDeleted(false);
        expected.setStatus(1);
        expected.setNodeId(100);
        expected.setPartyId(100);
        expected.setRank(1000);
        expected.setDate(new Timestamp(fmt.parse("2014-01-01 12:30:55").getTime()));
        assertThat(result, equalTo(expected));
    }

    @Test
    public void testFindById_deleted_returnsNull () throws Exception{
        // When
        NodePartyRelationship result = dao.findById(200);

        // Then
        assertThat(result, nullValue());
    }

    @Test
    public void testFindById_notFound_returnsNull () throws Exception{
        // When
        NodePartyRelationship result = dao.findById(99);

        // Then
        assertThat(result, nullValue());
    }

    @Test
    public void testFindByIds () throws Exception{
        // When
        List<NodePartyRelationship> result = dao.findByIds(Arrays.asList(101L, 200L, 300L));

        // Then
        NodePartyRelationship expected1 = new NodePartyRelationship();
        expected1.setId(101L);
        expected1.setTypeId(1);
        expected1.setOwnerId(100);
        expected1.setChannelId(100);
        expected1.setDeleted(false);
        expected1.setStatus(1);
        expected1.setNodeId(100);
        expected1.setPartyId(100);
        expected1.setRank(1000);
        expected1.setDate(new Timestamp(fmt.parse("2014-01-01 12:30:55").getTime()));

        NodePartyRelationship expected2 = new NodePartyRelationship();
        expected2.setId(300L);
        expected2.setTypeId(1);
        expected2.setOwnerId(300);
        expected2.setChannelId(300);
        expected2.setDeleted(false);
        expected2.setStatus(1);
        expected2.setNodeId(300);
        expected2.setPartyId(300);
        expected2.setChannelId(300);
        expected2.setRank(3000);
        expected2.setDate(new Timestamp(fmt.parse("2014-01-01 12:30:55").getTime()));

        assertThat(result, hasSize(2));
        assertThat(result, hasItems(expected1, expected2));
    }

    @Test
    public void testFindIdsByRelationshhipTypeByParties() throws Exception {
        // When
        List<Long> result = dao.findIdsByTypeByParties(1, Arrays.asList(300L, 310L), 100, 0);

        // Then
        assertThat(result, hasSize(3));
        assertThat(result, hasItems(300L, 301L, 310L));
    }
}