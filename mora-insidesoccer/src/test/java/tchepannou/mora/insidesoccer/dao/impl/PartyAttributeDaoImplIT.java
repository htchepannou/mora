package tchepannou.mora.insidesoccer.dao.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tchepannou.mora.insidesoccer.config.JdbcConfig;
import tchepannou.mora.insidesoccer.dao.PartyAttributeDao;
import tchepannou.mora.insidesoccer.domain.Party;
import tchepannou.mora.insidesoccer.domain.PartyAttribute;

import java.util.List;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration (classes = {JdbcConfig.class})
@SqlGroup ({
        @Sql (executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:sql/dao/is-clean.sql", "classpath:sql/dao/PartyAttributeDao.sql"}),
})
public class PartyAttributeDaoImplIT {
    @Autowired
    private PartyAttributeDao dao;

    @Test
    public void testFindByPartyByNames() throws Exception {
        // Given
        Party party = new Party(1);

        // When
        List<PartyAttribute> result = dao.findByPartyByNames(1, "title", "description");

        // Then
        assertThat(result, hasSize(2));
        assertThat(result, hasItems(
                new PartyAttribute(1, party, "title", "This is title")
                ,new PartyAttribute(2, party, "description", "This is description")
        ));
    }

    @Test
    public void testFindByPartyByNames_BadParty_returnsEmpty() throws Exception {
        // When
        List<PartyAttribute> result = dao.findByPartyByNames(999, "title", "description");

        // Then
        assertThat(result, hasSize(0));
    }
}