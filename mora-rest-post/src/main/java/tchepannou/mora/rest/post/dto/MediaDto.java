package tchepannou.mora.rest.post.dto;

import com.google.common.base.Preconditions;
import tchepannou.mora.core.domain.Media;
import tchepannou.mora.core.domain.MediaType;
import tchepannou.mora.core.service.MediaTypeService;
import tchepannou.mora.core.service.OembedService;
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
    private MediaTypeDto type;

    //-- Public
    private MediaDto (){
    }

    //-- Builder
    public static class Builder {
        private Media media;
        private OembedService oembedService;
        private MediaTypeService mediaTypeService;

        public MediaDto build (){
            Preconditions.checkState(media != null, "media == null");
            Preconditions.checkState(oembedService != null, "oembedService == null");
            Preconditions.checkState(mediaTypeService != null, "mediaTypeService == null");

            MediaDto result = new MediaDto();
            result.title = media.getTitle();
            result.description = media.getDescription();
            result.contentType = media.getContentType();
            result.size = media.getSize();
            result.url = media.getUrl();
            result.thumbnailUrl = media.getThumbnailUrl();
            result.imageUrl = media.getImageUrl();
            result.oembed = media.isOembed();

            MediaType mediaType = mediaTypeService.findById(media.getTypeId());
            if (mediaType == null){
                throw new IllegalStateException("Invalid MediaType: " + media.getTypeId());
            }
            result.type = new MediaTypeDto.Builder().withMediaType(mediaType).build();

            if (media.isOembed()){
                result.embedUrl = oembedService.getEmbedUrl(media.getUrl());
            } else {
                result.embedUrl = null;
            }

            return result;
        }

        public Builder withMedia (Media media){
            this.media = media;
            return this;
        }
        public Builder withOembedService (OembedService oembedService){
            this.oembedService = oembedService;
            return this;
        }
        public Builder withMediaTypeService (MediaTypeService mediaTypeService){
            this.mediaTypeService = mediaTypeService;
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

    public MediaTypeDto getType() {
        return type;
    }

    public String getEmbedUrl() {
        return embedUrl;
    }
}
