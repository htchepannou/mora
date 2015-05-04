package tchepannou.mora.insidesoccer.dao.impl;

//-- Inner class
class NodeRelationshipEntry {
    private long relationshipId;
    private long nodeId;

    //-- Constructor
    public NodeRelationshipEntry(long relationshipId, long nodeId) {
        this.nodeId = nodeId;
        this.relationshipId = relationshipId;
    }

    //-- Getter
    public long getNodeId() {
        return nodeId;
    }

    public long getRelationshipId() {
        return relationshipId;
    }
}
