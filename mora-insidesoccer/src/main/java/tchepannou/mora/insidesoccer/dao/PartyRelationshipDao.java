package tchepannou.mora.insidesoccer.dao;

import tchepannou.mora.insidesoccer.domain.PartyRelationship;

import java.util.Collection;
import java.util.List;

public interface PartyRelationshipDao {
    PartyRelationship findBySourceByDestinationByType (long sourceId, long destinationId, long typeId);
    List<PartyRelationship> findBySourceByDestinationsByType (long sourceId, Collection<Long> destinationIds, long typeId);
    List<PartyRelationship> findBySourceByType(long sourceId, long typeId);
}
