package tchepannou.mora.insidesoccer.dao.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tchepannou.mora.insidesoccer.config.JdbcConfig;
import tchepannou.mora.insidesoccer.dao.PartyRelationshipDao;
import tchepannou.mora.insidesoccer.domain.PartyRelationship;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration (classes = {JdbcConfig.class})
@SqlGroup ({
        @Sql (executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:sql/dao/is-clean.sql", "classpath:sql/dao/PartyRelationshipDao.sql"}),
})
public class PartyRelationshipDaoImplIT {
    @Autowired
    private PartyRelationshipDao dao;

    @Test
    public void testFindBySourceByDestinationByType() throws Exception {
        PartyRelationship result = dao.findBySourceByDestinationByType(100, 110, 1);

        PartyRelationship expected = new PartyRelationship();
        expected.setId(100);
        expected.setSourceId(100);
        expected.setDestinationId(110);
        expected.setTypeId(1);
        expected.setRank(1);
        expected.setQualifier("foo");
        assertThat(result, equalTo(expected));
    }


    @Test
    public void testFindBySourceByType() throws Exception {
        List<PartyRelationship> result = dao.findBySourceByType(200, 1);

        PartyRelationship expected1 = new PartyRelationship();
        expected1.setId(200);
        expected1.setSourceId(200);
        expected1.setDestinationId(210);
        expected1.setTypeId(1);
        expected1.setRank(1);
        expected1.setQualifier("foo");
        
        PartyRelationship expected2 = new PartyRelationship();
        expected2.setId(201);
        expected2.setSourceId(200);
        expected2.setDestinationId(220);
        expected2.setTypeId(1);
        expected2.setRank(2);
        expected2.setQualifier("foo");

        assertThat(result, hasSize(2));
        assertThat(result, hasItems(expected1, expected1));

    }

    @Test
    public void testFindBySourceByDestinationsByType() throws Exception {
        List<PartyRelationship> result = dao.findBySourceByDestinationsByType(300, Arrays.asList(300L, 310L, 320L, 330L, 340L, 350L), 1);

        PartyRelationship expected1 = new PartyRelationship();
        expected1.setId(300);
        expected1.setSourceId(300);
        expected1.setDestinationId(310);
        expected1.setTypeId(1);
        expected1.setRank(1);
        expected1.setQualifier("foo");

        PartyRelationship expected2 = new PartyRelationship();
        expected2.setId(301);
        expected2.setSourceId(300);
        expected2.setDestinationId(320);
        expected2.setTypeId(1);
        expected2.setRank(2);
        expected2.setQualifier("foo");

        assertThat(result, hasSize(2));
        assertThat(result, hasItems(expected1, expected1));
    }
}