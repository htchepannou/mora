package tchepannou.mora.insidesoccer.domain;

import com.google.common.base.Preconditions;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class NodeAttribute extends Attribute {
    //-- Attributes
    private long nodeId;

    public NodeAttribute(){
    }
    public NodeAttribute(long id, Node node, String name, String value){
        super(id, name, value);

        Preconditions.checkArgument(node != null, "party == null");
        Preconditions.checkArgument(node.getId()>0, "party.id <= 0");

        this.nodeId = node.getId();
    }

    //-- Public
    public static Set<Long> toNodeIdSet(Collection<NodeAttribute> attributes){
        Set<Long> result = new HashSet<>();
        for (NodeAttribute attribute : attributes){
            result.add(attribute.getNodeId());
        }
        return result;
    }

    //-- Getter/Setter
    public long getNodeId() {
        return nodeId;
    }

    public void setNodeId(long nodeId) {
        this.nodeId = nodeId;
    }
}
