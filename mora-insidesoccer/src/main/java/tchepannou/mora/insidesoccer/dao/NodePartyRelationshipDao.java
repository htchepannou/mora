package tchepannou.mora.insidesoccer.dao;

import tchepannou.mora.insidesoccer.domain.NodePartyRelationship;

import java.util.Collection;
import java.util.List;

public interface NodePartyRelationshipDao {
    NodePartyRelationship findById(long id);
    List<NodePartyRelationship> findByIds(Collection<Long> ids);
    List<Long> findIdsByTypeByParties(long relationshipType, Collection<Long> partyIds, int limit, int offset);
}
