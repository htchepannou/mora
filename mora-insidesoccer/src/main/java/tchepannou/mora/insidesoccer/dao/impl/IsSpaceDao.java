package tchepannou.mora.insidesoccer.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import tchepannou.mora.core.dao.SpaceDao;
import tchepannou.mora.core.domain.Space;
import tchepannou.mora.insidesoccer.dao.PartyAttributeDao;
import tchepannou.mora.insidesoccer.dao.PartyDao;
import tchepannou.mora.insidesoccer.domain.Party;
import tchepannou.mora.insidesoccer.domain.PartyAttribute;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class IsSpaceDao implements SpaceDao {
    //-- Attributes
    public static final List<Long> PARTY_TYPE_IDS = Arrays.asList(3L, 4L);

    @Autowired
    private PartyDao partyDao;

    @Autowired
    private PartyAttributeDao partyAttributeDao;

    //-- SpaceDao overrides
    @Override
    public Space findById(long id) {
        Party party = partyDao.findById(id);
        if (party == null || !PARTY_TYPE_IDS.contains(party.getId())){
            return null;
        }

        List<PartyAttribute> attributes = partyAttributeDao.findByPartyByNames(id, PartyAttribute.SPACE_ATTRIBUTE_NAMES);

        return toSpace(party, attributes);
    }


    @Override
    public long create(Space space) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Space space) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Space space) {
        throw new UnsupportedOperationException();
    }

    //-- Private
    private Space toSpace (Party party, Collection<PartyAttribute> attributes){
        Space result = new Space ();
        party.toSpace(result);
        PartyAttribute.toSpace(attributes, result);
        return result;
    }

}
