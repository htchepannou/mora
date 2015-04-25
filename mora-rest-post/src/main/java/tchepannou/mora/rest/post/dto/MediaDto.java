package tchepannou.mora.rest.post.dto;

import com.google.common.base.Preconditions;
import tchepannou.mora.core.domain.Media;
import tchepannou.mora.core.domain.MediaType;
import tchepannou.mora.rest.core.dto.ModelDto;

public class MediaDto extends ModelDto{
    //-- Attributes
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
    private MediaDto (){
    }

    //-- Builder
    public static class Builder {
        private Media media;
        private MediaType type;

        public MediaDto build (){
            Preconditions.checkState(media != null, "media == null");
            Preconditions.checkState(type != null, "type == null");
            Preconditions.checkState(media.getTypeId() == type.getId(), "media.typeId != type.id");

            MediaDto result = new MediaDto();
            result.title = media.getTitle();
            result.description = media.getDescription();
            result.contentType = media.getContentType();
            result.size = media.getSize();
            result.url = media.getUrl();
            result.thumbnailUrl = media.getThumbnailUrl();
            result.imageUrl = media.getImageUrl();
            result.oembed = media.isOembed();
            result.typeId = type.getId();
            return result;
        }

        public Builder withMedia (Media media){
            this.media = media;
            return this;
        }
        public Builder withMediaType (MediaType type){
            this.type = type;
            return this;
        }
    }

    //-- Getters
    public String getContentType() {
        return contentType;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public boolean isOembed() {
        return oembed;
    }

    public long getSize() {
        return size;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public long getTypeId() {
        return typeId;
    }
}
