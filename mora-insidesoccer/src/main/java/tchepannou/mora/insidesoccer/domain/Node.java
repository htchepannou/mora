package tchepannou.mora.insidesoccer.domain;

import tchepannou.mora.core.domain.Media;

public class Node extends AbstractNode{
    //-- Attribute
    public static final long TYPE_ATTACHMENT = 100;

    private long channelId;

    //--- Constructor
    public Node(){
    }
    public Node(long id){
        super(id);
    }

    //-- Public
    public void toMedia(Media media) {
        media.setId(this.getId());
        media.setLastUpdate(this.getDate());
        media.setUserId(this.ownerId);
        media.setDeleted(this.deleted);
        media.setSpaceId(this.channelId);
    }

    //-- Getter/Setter
    public long getChannelId() {
        return channelId;
    }

    public void setChannelId(long channelId) {
        this.channelId = channelId;
    }
}
