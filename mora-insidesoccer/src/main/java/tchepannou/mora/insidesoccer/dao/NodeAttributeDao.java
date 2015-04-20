package tchepannou.mora.insidesoccer.dao;

import tchepannou.mora.insidesoccer.domain.NodeAttribute;

import java.util.List;

public interface NodeAttributeDao {
    List<NodeAttribute> findByNodeByNames(long nodeId, String... names);
}
