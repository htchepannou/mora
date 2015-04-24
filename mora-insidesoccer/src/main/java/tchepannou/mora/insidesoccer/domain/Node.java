package tchepannou.mora.insidesoccer.domain;

import tchepannou.mora.core.domain.Media;
import tchepannou.mora.core.domain.Post;

public class Node extends AbstractNode{
    //-- Attribute
    public static final long TYPE_BLOG = 1;
    public static final long RELATION_BLOG = 1;

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

    public void toMedia(Media media) {
        media.setId(this.getId());
        media.setLastUpdate(this.date);
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
