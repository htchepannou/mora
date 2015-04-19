package tchepannou.mora.insidesoccer.dao;

import tchepannou.mora.insidesoccer.domain.Node;

import java.util.Collection;
import java.util.List;

public interface NodeDao {
    Node findById(long id);
    List<Long> findIdsByParties(Collection<Long> partyIds, int limit, int offset);
}
