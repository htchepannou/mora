package tchepannou.mora.core.domain;

import com.google.common.base.Preconditions;

public class Media extends LifecycleAwareModel{
    //-- Attributes
    private long userId;
    private long spaceId;
    private long typeId;
    private String title;
    private String description;
    private String contentType;
    private long size;
    private String url;
    private String thumbnailUrl;
    private String imageUrl;
    private boolean oembed;


    //-- Public
    public Media (){
    }
    public Media (long id){
        super(id);
    }
    public Media (long id, Space space, User user, MediaType type) {
        super(id);
        
        Preconditions.checkArgument(space != null, "space==null");
        Preconditions.checkArgument(space.getId()>0, "space.id <=0");
        
        Preconditions.checkArgument(user != null, "user==null");
        Preconditions.checkArgument(user.getId() > 0, "user.id <=0");
        
        Preconditions.checkArgument(type != null, "type==null");
        Preconditions.checkArgument(type.getId() > 0, "type.id <=0");
        
        this.spaceId = space.getId();
        this.userId = user.getId();
        this.typeId = type.getId();
    }
    public Media (Media media){
        super(media);
        this.spaceId = media.spaceId;
        this.typeId = media.typeId;
        this.userId = media.userId;
        this.title = media.title;
        this.description = media.description;
        this.size = media.size;
        this.url = media.url;
        this.imageUrl = media.imageUrl;
        this.thumbnailUrl = media.thumbnailUrl;
        this.oembed = media.oembed;
    }

    //-- Getter/Setter
    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long typeId) {
        this.typeId = typeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isOembed() {
        return oembed;
    }

    public void setOembed(boolean oembed) {
        this.oembed = oembed;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(long spaceId) {
        this.spaceId = spaceId;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
