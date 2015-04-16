package tchepannou.mora.insidesoccer.dao;

import tchepannou.mora.insidesoccer.domain.PartyAttribute;

import java.util.List;

public interface PartyAttributeDao {
    List<PartyAttribute> findByPartyByNames(long partyId, String...names);
}
