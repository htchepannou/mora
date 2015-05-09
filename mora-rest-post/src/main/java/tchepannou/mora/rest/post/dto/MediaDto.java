package tchepannou.mora.rest.post.dto;

import com.google.common.base.Preconditions;
import tchepannou.mora.core.domain.Media;
import tchepannou.mora.core.domain.MediaType;
import tchepannou.mora.rest.core.dto.EnumDto;
import tchepannou.mora.rest.core.dto.ModelDto;

public class MediaDto extends ModelDto{
    //-- Attributes
    private String title;
    private String description;
    private String contentType;
    private long size;
    private String url;
    private String thumbnailUrl;
    private String imageUrl;
    private boolean oembed;
    private String embedUrl;
    private EnumDto type;

    //-- Public
    private MediaDto (){
    }

    //-- Builder
    public static class Builder {
        private Media media;
        private MediaType mediaType;
        private String embedUrl;


        public MediaDto build (){
            Preconditions.checkState(media != null, "media == null");

            Preconditions.checkState(mediaType != null, "mediaType == null");
            Preconditions.checkState(mediaType.getId() == media.getTypeId(), "mediaType.id != media.typeId");

            MediaDto result = new MediaDto();
            result.title = media.getTitle();
            result.description = media.getDescription();
            result.contentType = media.getContentType();
            result.size = media.getSize();
            result.url = media.getUrl();
            result.thumbnailUrl = media.getThumbnailUrl();
            result.imageUrl = media.getImageUrl();
            result.oembed = media.isOembed();

            result.type = new EnumDto.Builder().withEnum(mediaType).build();

            if (media.isOembed()) {
                result.embedUrl = embedUrl;
            }

            return result;
        }

        public Builder withMedia (Media media){
            this.media = media;
            return this;
        }
        public Builder withEmbedUrl (String embedUrl){
            this.embedUrl = embedUrl;
            return this;
        }
        public Builder withMediaType (MediaType mediaType){
            this.mediaType = mediaType;
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

    public EnumDto getType() {
        return type;
    }

    public String getEmbedUrl() {
        return embedUrl;
    }
}
