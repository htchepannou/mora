package tchepannou.mora.rest.post.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import tchepannou.mora.rest.core.json.JsonDateSerializer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostDto extends BasePostDto{
    //-- Attributes
    private String content;
    private Date creationDate;
    private List<MediaDto> medias = new ArrayList<>();  // NOSONAR - This field is serializable


    //-- Builder
    public static class Builder extends BasePostDtoBuilder<PostDto>{@Override
        protected PostDto createDto() {
            return new PostDto();
        }

        @Override
        public PostDto build (){
            PostDto result = super.build();
            result.content = post.getContent();
            result.creationDate = post.getCreationDate();
            return result;
        }
    }

    //-- Public
    public void addMedia(MediaDto media){
        medias.add(media);
    }

    //-- Getter
    @JsonSerialize (using=JsonDateSerializer.class)
    public Date getCreationDate() {
        return creationDate;
    }

    public String getContent() {
        return content;
    }

    public List<MediaDto> getMedias() {
        return medias;
    }
}
