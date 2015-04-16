package tchepannou.mora.insidesoccer.dao;

import tchepannou.mora.insidesoccer.domain.Party;

public interface PartyDao {
    Party findById (long id);
}
