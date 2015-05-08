package tchepannou.mora.insidesoccer.domain;

import tchepannou.mora.core.domain.Event;
import tchepannou.mora.core.domain.Post;

import java.sql.Timestamp;
import java.util.Calendar;

public class NodePartyRelationship extends Node{
    //-- Attribute
    public static final long TYPE_BLOG = 1;
    public static final long TYPE_EVENT = 4;

    private long nodeId;
    private long partyId;
    private long rank;
    private String qualifier;
    private boolean primary;

    // Public
    public NodePartyRelationship(){
    }
    public NodePartyRelationship(long id){
        super(id);
    }

    //-- Public
    public void toPost(Post post) {
        post.setId(this.getId());
        post.setLastUpdate(new Timestamp(this.getRank()));
        post.setUserId(this.getOwnerId());
        post.setDeleted(this.isDeleted());
        post.setSpaceId(this.getChannelId());
    }

    public void toEvent(Event event){
        event.setId(getId());
        event.setLastUpdate(new Timestamp(this.getRank()));
        event.setUserId(this.getOwnerId());
        event.setDeleted(this.isDeleted());
        event.setSpaceId(this.getChannelId());

        Calendar cal = Calendar.getInstance();
        cal.setTime(this.getDate());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        event.setStartDateTime(cal.getTime());
        event.setEndDateTime(cal.getTime());
        event.setTimezone(null);
    }


    //-- Getter/Setter
    public long getNodeId() {
        return nodeId;
    }

    public void setNodeId(long nodeId) {
        this.nodeId = nodeId;
    }

    public String getQualifier() {
        return qualifier;
    }

    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }

    public long getRank() {
        return rank;
    }

    public void setRank(long rank) {
        this.rank = rank;
    }

    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

    public long getPartyId() {
        return partyId;
    }

    public void setPartyId(long partyId) {
        this.partyId = partyId;
    }
}
