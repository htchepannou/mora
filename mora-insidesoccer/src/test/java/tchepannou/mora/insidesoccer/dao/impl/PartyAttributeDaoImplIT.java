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

    @Test
    public void testFindByPartyByNames_DeletedParty_returnsEmpty() throws Exception {
        // When
        List<PartyAttribute> result = dao.findByPartyByNames(2, "title", "description");

        // Then
        assertThat(result, hasSize(0));
    }

    public void testFindByNameByValue () throws Exception{
        // When
        List<PartyAttribute> result = dao.findByNameByValue("email", "user1@gmail.com");

        // Then
        assertThat(result, hasSize(2));

        PartyAttribute expected1 = new PartyAttribute(4, new Party(1), "email", "email1@gmail.com");
        PartyAttribute expected2 = new PartyAttribute(21, new Party(11), "email", "email1@gmail.com");
        assertThat(result, hasItems(expected1, expected2));
    }

    @Test
    public void testFindByNameByValue_notFound_returnsEmptyList () throws Exception{
        // When
        List<PartyAttribute> result = dao.findByNameByValue("email", "???");

        // Then
        assertThat(result, hasSize(0));
    }
}