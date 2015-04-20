package tchepannou.mora.core.domain;

import com.google.common.base.Preconditions;

public class Post extends LifecycleAwareModel {
    //-- Attributes
    private long userId;
    private long spaceId;
    private String title;
    private String summary;
    private String content;


    //-- Constructor
    public Post(){
    }
    public Post(long id){
        super(id);
    }
    public  Post (long id, Space space, User user){
        super(id);

        Preconditions.checkArgument(space != null, "space==null");
        Preconditions.checkArgument(space.getId()>0, "space.id<=0");

        Preconditions.checkArgument(user != null, "user==null");
        Preconditions.checkArgument(user.getId()>0, "user.id<=0");
        
        this.spaceId = space.getId();
        this.userId = user.getId();
    }
    public Post (Post post){
        super(post);

        this.userId = post.userId;
        this.spaceId = post.spaceId;
        this.title = post.title;
        this.summary = post.summary;
        this.content = post.content;
    }
    
    //-- Getter/Setter
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(long spaceId) {
        this.spaceId = spaceId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
