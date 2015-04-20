package tchepannou.mora.insidesoccer.dao;

import com.google.common.collect.Multimap;
import tchepannou.mora.insidesoccer.domain.PartyAttribute;

import java.util.Collection;
import java.util.List;

public interface PartyAttributeDao {
    List<PartyAttribute> findByPartyByNames(long partyId, String...names);
    Multimap<Long, PartyAttribute> findByPartiesByNames(Collection<Long> parties, String...names);

    List<PartyAttribute> findByNameByValueByPartyType(String name, String value, long partyTypeId);
}
