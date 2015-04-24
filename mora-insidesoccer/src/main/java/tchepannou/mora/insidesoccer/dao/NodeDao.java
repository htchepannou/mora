package tchepannou.mora.insidesoccer.dao;

import tchepannou.mora.insidesoccer.domain.Node;

import java.util.List;

public interface NodeDao {
    Node findById (long id);
    List<Node> findInNodes(long nodeId, long nodeRelationshipTypeId);
}
