package tchepannou.mora.core.domain;

import com.google.common.base.Preconditions;

import java.util.Date;

public class Post extends Model implements SoftDeleteSupport {
    //-- Attributes
    private long userId;
    private long spaceId;
    private String title;
    private String summary;
    private String content;
    private boolean deleted;
    private Date creationDate;
    private Date lastUpdate;


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
        this.deleted = post.deleted;
        this.creationDate = post.creationDate;
        this.lastUpdate = post.lastUpdate;
    }
    
    //-- SoftDeleteSupport overrides
    @Override
    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
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
