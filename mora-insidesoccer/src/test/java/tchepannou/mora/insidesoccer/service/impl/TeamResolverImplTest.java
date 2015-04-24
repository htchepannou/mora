package tchepannou.mora.insidesoccer.service.impl;

import com.google.common.collect.Sets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tchepannou.mora.core.dao.RoleDao;
import tchepannou.mora.insidesoccer.dao.PartyDao;
import tchepannou.mora.insidesoccer.dao.PartyRelationshipDao;
import tchepannou.mora.insidesoccer.domain.IsRole;
import tchepannou.mora.insidesoccer.domain.Party;
import tchepannou.mora.insidesoccer.domain.PartyRelationship;
import tchepannou.mora.insidesoccer.service.TeamResolver;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TeamResolverImplTest {

    @Mock
    private PartyRelationshipDao partyRelationshipDao;

    @Mock
    private PartyDao partyDao;

    @Mock
    private RoleDao roleDao;

    @InjectMocks
    private TeamResolver resolver = new TeamResolverImpl();

    @Test
    public void testGetTeamIdsForUser_teamOnly() throws Exception {
        // Given
        long userId = 10;
        PartyRelationship rel1 = new PartyRelationship(userId, 10, PartyRelationship.TYPE_MEMBER);
        PartyRelationship rel2 = new PartyRelationship(userId, 20, PartyRelationship.TYPE_MEMBER);
        PartyRelationship rel3 = new PartyRelationship(userId, 30, PartyRelationship.TYPE_MEMBER);
        when(partyRelationshipDao.findBySourceByType(userId, PartyRelationship.TYPE_MEMBER)).thenReturn(Arrays.asList(rel1, rel2, rel3));

        Party party1 = new Party(10, Party.TYPE_TEAM);
        Party party2 = new Party(20, Party.TYPE_TEAM);
        Party party3 = new Party(30, Party.TYPE_TEAM);
        when(partyDao.findByIds(any(Collection.class))).thenReturn(Arrays.asList(party1, party2, party3));

        // When
        Set<Long> result = resolver.getTeamIdsForUser(userId);

        // Then
        assertThat(result, hasSize(3));
        assertThat(result, hasItems(party1.getId(), party2.getId(), party3.getId()));
    }

    @Test
    public void testGetTeamIdsForUser_clubTeams_includeAll() throws Exception {
        // Given
        long userId = 10;
        long clubId = 10;
        PartyRelationship club = new PartyRelationship(userId, clubId, PartyRelationship.TYPE_MEMBER, "1");
        when(partyRelationshipDao.findBySourceByType(userId, PartyRelationship.TYPE_MEMBER)).thenReturn(Arrays.asList(club));
        when(partyRelationshipDao.findBySourceByDestinationByType(userId, clubId, PartyRelationship.TYPE_MEMBER)).thenReturn(club);

        Party party1 = new Party(10, Party.TYPE_CLUB);
        when(partyDao.findByIds(any(Collection.class))).thenReturn(Arrays.asList(party1));

        IsRole role = new IsRole(1, "admin", true);
        when(roleDao.findById(1)).thenReturn(role);

        PartyRelationship team1 = new PartyRelationship(userId, 20, PartyRelationship.TYPE_MEMBER);
        PartyRelationship team2 = new PartyRelationship(userId, 30, PartyRelationship.TYPE_MEMBER);
        when(partyRelationshipDao.findBySourceByType(clubId, PartyRelationship.TYPE_CLUB)).thenReturn(Arrays.asList(team1, team2));


        // When
        Set<Long> result = resolver.getTeamIdsForUser(userId);

        // Then
        assertThat(result, hasSize(2));
        assertThat(result, hasItems(20L, 30L));
    }


    @Test
    public void testGetTeamIdsForUser_clubTeams_includeSome() throws Exception {
        // Given
        long userId = 10;
        long clubId = 10;
        PartyRelationship club = new PartyRelationship(userId, clubId, PartyRelationship.TYPE_MEMBER, "1");
        when(partyRelationshipDao.findBySourceByType(userId, PartyRelationship.TYPE_MEMBER)).thenReturn(Arrays.asList(club));
        when(partyRelationshipDao.findBySourceByDestinationByType(userId, clubId, PartyRelationship.TYPE_MEMBER)).thenReturn(club);

        Party party1 = new Party(10, Party.TYPE_CLUB);
        when(partyDao.findByIds(any(Collection.class))).thenReturn(Arrays.asList(party1));

        IsRole role = new IsRole(1, "coach", false);
        when(roleDao.findById(1)).thenReturn(role);

        PartyRelationship team1 = new PartyRelationship(userId, 20, PartyRelationship.TYPE_MEMBER);
        PartyRelationship team2 = new PartyRelationship(userId, 30, PartyRelationship.TYPE_MEMBER);
        PartyRelationship team3 = new PartyRelationship(userId, 40, PartyRelationship.TYPE_MEMBER);
        PartyRelationship team4 = new PartyRelationship(userId, 50, PartyRelationship.TYPE_MEMBER);
        when(partyRelationshipDao.findBySourceByType(clubId, PartyRelationship.TYPE_CLUB)).thenReturn(Arrays.asList(team1, team2, team3, team4));

        when(partyRelationshipDao.findBySourceByDestinationsByType(clubId, Sets.newHashSet(20L, 30L, 40L, 50L), PartyRelationship.TYPE_MEMBER)).thenReturn(Arrays.asList(team1, team2));


        // When
        Set<Long> result = resolver.getTeamIdsForUser(userId);

        // Then
        assertThat(result, hasSize(2));
        assertThat(result, hasItems(20L, 30L));
    }

    @Test
    public void testGetTeamIdsForUser_badRole_shouldReturnEmpty() throws Exception {
        // Given
        long userId = 10;
        long clubId = 10;
        PartyRelationship club = new PartyRelationship(userId, clubId, PartyRelationship.TYPE_MEMBER, "1");
        when(partyRelationshipDao.findBySourceByType(userId, PartyRelationship.TYPE_MEMBER)).thenReturn(Arrays.asList(club));
        when(partyRelationshipDao.findBySourceByDestinationByType(userId, clubId, PartyRelationship.TYPE_MEMBER)).thenReturn(club);

        Party party1 = new Party(10, Party.TYPE_CLUB);
        when(partyDao.findByIds(any(Collection.class))).thenReturn(Arrays.asList(party1));

        when(roleDao.findById(1)).thenReturn(null);

        // When
        Set<Long> result = resolver.getTeamIdsForUser(userId);

        // Then
        assertThat(result, hasSize(0));
    }

}