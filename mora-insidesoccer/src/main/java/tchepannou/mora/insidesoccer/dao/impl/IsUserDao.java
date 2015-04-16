package tchepannou.mora.insidesoccer.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import tchepannou.mora.core.dao.UserDao;
import tchepannou.mora.core.domain.User;
import tchepannou.mora.insidesoccer.dao.PartyAttributeDao;
import tchepannou.mora.insidesoccer.dao.PartyDao;
import tchepannou.mora.insidesoccer.domain.Party;
import tchepannou.mora.insidesoccer.domain.PartyAttribute;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class IsUserDao implements UserDao{
    //-- Attributes
    public static final long PARTY_TYPE_ID = 1L;    // Person

    @Autowired
    private PartyDao partyDao;

    @Autowired
    private PartyAttributeDao partyAttributeDao;



    //-- UserDao overrides
    @Override
    public User findById(long id) {
        Party party = partyDao.findById(id);
        if (party == null || party.getTypeId() != PARTY_TYPE_ID){
            return null;
        }

        List<PartyAttribute> attributes = partyAttributeDao.findByPartyByNames(id, PartyAttribute.USER_ATTRIBUTE_NAMES.toArray(new String[]{}));

        return toUser(party, attributes);
    }

    @Override
    public List<User> findByUsername(String username) {
        return findByAttributeNameValue(PartyAttribute.USERNAME, username);
    }

    @Override
    public List<User> findByEmail(String email) {
        return findByAttributeNameValue(PartyAttribute.EMAIL, email);
    }

    @Override
    public long create(User user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(User user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(User user) {
        throw new UnsupportedOperationException();
    }

    //-- Private
    private User toUser (Party party, Collection<PartyAttribute> attributes){
        User result = new User ();
        party.toUser(result);
        PartyAttribute.toUser(attributes, result);
        return result;
    }

    private List<User> findByAttributeNameValue(String name, String value) {
        List<PartyAttribute> attributes = partyAttributeDao.findByNameByValueByPartyType(name, value, PARTY_TYPE_ID);
        Set<Long> partyIds = PartyAttribute.toPartyIdSet(attributes);
        List<Party> parties = partyDao.findByIds(partyIds);

        List<User> result = new ArrayList<>();
        for (Party party : parties){
            List<PartyAttribute> xattributes = partyAttributeDao.findByPartyByNames(party.getId(), PartyAttribute.USER_ATTRIBUTE_NAMES.toArray(new String[]{}));
            User user = toUser(party, xattributes);
            result.add(user);
        }
        return result;
    }


}
