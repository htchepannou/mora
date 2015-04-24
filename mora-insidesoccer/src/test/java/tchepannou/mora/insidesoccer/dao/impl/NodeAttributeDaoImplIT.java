package tchepannou.mora.insidesoccer.dao.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tchepannou.mora.insidesoccer.config.JdbcConfig;
import tchepannou.mora.insidesoccer.dao.NodeAttributeDao;
import tchepannou.mora.insidesoccer.domain.NodePartyRelationship;
import tchepannou.mora.insidesoccer.domain.NodeAttribute;

import java.util.List;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration (classes = {JdbcConfig.class})
@SqlGroup ({
        @Sql (executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:sql/dao/is-clean.sql", "classpath:sql/dao/NodeAttributeDao.sql"}),
})
public class NodeAttributeDaoImplIT {
    @Autowired
    private NodeAttributeDao dao;


    //-- Test
    @Test
    public void testFindByNodePartyRelationshipByNames() throws Exception {
        // Given
        NodePartyRelationship party = new NodePartyRelationship(101);

        // When
        List<NodeAttribute> result = dao.findByNodePartyRelationshipByNames(101, "title", "description");

        // Then
        assertThat(result, hasSize(2));
        assertThat(result, hasItems(
                new NodeAttribute(100, party, "title", "title1")
                ,new NodeAttribute(101, party, "description", "description1")
        ));
    }

    @Test
    public void testFindByNodePartyRelationshipByNames_deletedNode_returnsEmpty() throws Exception {
        // When
        List<NodeAttribute> result = dao.findByNodePartyRelationshipByNames(201, "title", "description");

        // Then
        assertThat(result, hasSize(0));
    }

    @Test
    public void testFindByNodePartyRelationshipsByNames_badNode_returnsEmpty() throws Exception {
        // When
        List<NodeAttribute> result = dao.findByNodePartyRelationshipByNames(999, "title", "description");

        // Then
        assertThat(result, hasSize(0));
    }



    @Test
    public void testFindByNodeByNames() throws Exception {
        // Given
        NodePartyRelationship party = new NodePartyRelationship(100);

        // When
        List<NodeAttribute> result = dao.findByNodeByNames(100, "title", "description");

        // Then
        assertThat(result, hasSize(2));
        assertThat(result, hasItems(
                new NodeAttribute(100, party, "title", "title1")
                ,new NodeAttribute(101, party, "description", "description1")
        ));
    }



    @Test
    public void testFindByNodeByNames_deletedNode_returnsEmpty() throws Exception {
        // When
        List<NodeAttribute> result = dao.findByNodeByNames(200, "title", "description");

        // Then
        assertThat(result, hasSize(0));
    }

    @Test
    public void testFindByNodeByNames_badNode_returnsEmpty() throws Exception {
        // When
        List<NodeAttribute> result = dao.findByNodeByNames(999, "title", "description");

        // Then
        assertThat(result, hasSize(0));
    }

}