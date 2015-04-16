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


    //-- Test
    @Test
    public void testFindByPartyByNames() throws Exception {
        // Given
        Party party = new Party(100);

        // When
        List<PartyAttribute> result = dao.findByPartyByNames(100, "title", "description");

        // Then
        assertThat(result, hasSize(2));
        assertThat(result, hasItems(
                new PartyAttribute(100, party, "title", "title1")
                ,new PartyAttribute(101, party, "description", "description1")
        ));
    }

    @Test
    public void testFindByPartyByNames_DeletedParty_returnsEmpty() throws Exception {
        // When
        List<PartyAttribute> result = dao.findByPartyByNames(200, "title", "description");

        // Then
        assertThat(result, hasSize(0));
    }

    @Test
    public void testFindByPartyByNames_BadParty_returnsEmpty() throws Exception {
        // When
        List<PartyAttribute> result = dao.findByPartyByNames(999, "title", "description");

        // Then
        assertThat(result, hasSize(0));
    }


    @Test
    public void testFindByNameByValueByPartyType () throws Exception{
        // When
        List<PartyAttribute> result = dao.findByNameByValueByPartyType("title", "duplicate", 1);

        // Then
        assertThat(result, hasSize(2));

        PartyAttribute expected1 = new PartyAttribute(300, new Party(300), "title", "duplicate");
        PartyAttribute expected2 = new PartyAttribute(310, new Party(310), "title", "duplicate");
        assertThat(result, hasItems(expected1, expected2));
    }

    @Test
    public void testFindByNameByValueByPartyType_badValue_returnsEmptyList () throws Exception{
        // When
        List<PartyAttribute> result = dao.findByNameByValueByPartyType("email", "???", 1);

        // Then
        assertThat(result, hasSize(0));
    }

    @Test
    public void testFindByNameByValueByPartyType_badPartyType_returnsEmptyList () throws Exception{
        // When
        List<PartyAttribute> result = dao.findByNameByValueByPartyType("email", "email1@gmail.com", 999);

        // Then
        assertThat(result, hasSize(0));
    }
}