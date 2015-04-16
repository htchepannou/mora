package tchepannou.mora.insidesoccer.dao;

import tchepannou.mora.insidesoccer.domain.Party;

import java.util.Collection;
import java.util.List;

public interface PartyDao {
    Party findById (long id);
    List<Party> findByIds (Collection<Long> ids);
}
