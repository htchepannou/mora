package tchepannou.mora.insidesoccer.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tchepannou.mora.core.dao.RoleDao;
import tchepannou.mora.insidesoccer.dao.PartyDao;
import tchepannou.mora.insidesoccer.dao.PartyRelationshipDao;
import tchepannou.mora.insidesoccer.domain.IsRole;
import tchepannou.mora.insidesoccer.domain.Party;
import tchepannou.mora.insidesoccer.domain.PartyRelationship;
import tchepannou.mora.insidesoccer.service.TeamResolver;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TeamResolverImpl implements TeamResolver {
    //-- Attributes
    private static final Logger LOG = LoggerFactory.getLogger(TeamResolverImpl.class);

    @Autowired
    private PartyRelationshipDao partyRelationshipDao;

    @Autowired
    private PartyDao partyDao;

    @Autowired
    private RoleDao roleDao;


    //-- TeamResolver overrides
    @Override
    public Set<Long> getTeamIdsForUser(long userId){
        Set<Long> result = new HashSet<>();

        List<PartyRelationship> rels = partyRelationshipDao.findBySourceByType(userId, PartyRelationship.TYPE_MEMBER);
        Set<Long> channelIds = PartyRelationship.destinationIds(rels);
        List<Party> channels = partyDao.findByIds(channelIds);
        for (Party channel : channels){
            long typeId = channel.getTypeId();
            if (typeId == Party.TYPE_TEAM){
                result.add (channel.getId());
            } else if (typeId == Party.TYPE_CLUB){
                Set<Long> teamIds = teamsForUser(userId, channel);
                result.addAll(teamIds);
            }
        }

        return result;
    }

    private Set<Long> teamsForUser(long userId, Party club) {
        PartyRelationship member = partyRelationshipDao.findBySourceByDestinationByType(userId, club.getId(), PartyRelationship.TYPE_MEMBER);
        IsRole role = getRole(member);
        if (role == null){
            return Collections.emptySet();
        } else {
            Set<Long> result = new HashSet<>();

            List<PartyRelationship> rels = teamsForClub(club);
            Set<Long> teamIds = PartyRelationship.destinationIds(rels);
            if (role.isAccessAllTeams()){
                result.addAll(teamIds);
            } else {
                List<PartyRelationship> teams = partyRelationshipDao.findBySourceByDestinationsByType(userId, teamIds, PartyRelationship.TYPE_MEMBER);
                result.addAll(PartyRelationship.destinationIds(teams));
            }

            return result;
        }
    }

    private List<PartyRelationship> teamsForClub (Party club){
        return partyRelationshipDao.findBySourceByType(club.getId(), PartyRelationship.TYPE_CLUB);
    }

    private IsRole getRole(PartyRelationship member){
        if (member == null){
            return null;
        } else {
            try {
                long id = Long.parseLong(member.getQualifier());
                return (IsRole)roleDao.findById(id);
            } catch (Exception e){
                LOG.warn("Invalid roleId: " + member.getQualifier(), e);
                return null;
            }
        }
    }
}
