package tchepannou.mora.insidesoccer.domain;

import tchepannou.mora.core.domain.Post;

public class Node extends AbstractNode{
    //-- Attribute
    private long channelId;

    // Public
    public Node(){
    }
    public Node(long id){
        super(id);
    }

    //-- Public
    public void toPost(Post post) {
        post.setId(this.getId());
        post.setLastUpdate(this.date);
        post.setUserId(this.ownerId);
        post.setDeleted(this.deleted);
        post.setSpaceId(this.channelId);
    }

    //-- Getter/Setter
    public long getChannelId() {
        return channelId;
    }

    public void setChannelId(long channelId) {
        this.channelId = channelId;
    }
}
