package tchepannou.mora.insidesoccer.dao;

import com.google.common.collect.Multimap;
import tchepannou.mora.insidesoccer.domain.NodeAttribute;

import java.util.Collection;
import java.util.List;

public interface NodeAttributeDao {
    List<NodeAttribute> findByNodeByNames(long nodeId, String... names);
    Multimap<Long, NodeAttribute> findByNodesByNames(Collection<Long> nodeIds, String... names);
}
