package tchepannou.mora.insidesoccer.dao;

import com.google.common.collect.Multimap;
import tchepannou.mora.insidesoccer.domain.NodeAttribute;

import java.util.Collection;
import java.util.List;

public interface NodeAttributeDao {
    List<NodeAttribute> findByNodePartyRelationshipByNames(long nodePartyRelationshipId, String... names);
    Multimap<Long, NodeAttribute> findByNodePartyRelationshipsByNames(Collection<Long> nodePartyRelationshipId, String... names);
}
