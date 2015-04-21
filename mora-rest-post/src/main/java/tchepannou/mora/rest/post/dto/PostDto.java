package tchepannou.mora.rest.post.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import tchepannou.mora.rest.core.json.JsonDateSerializer;

import java.util.Date;

public class PostDto extends BasePostDto{
    //-- Attributes
    private String content;
    private Date creationDate;


    //-- Builder
    public static class Builder extends BasePostDtoBuilder{
        @Override
        protected BasePostDto createDto() {
            return new PostDto();
        }

        public PostDto build (){
            PostDto result = (PostDto)super.build();
            result.content = post.getContent();
            result.creationDate = post.getCreationDate();
            return result;
        }
    }

    //-- Getter
    @JsonSerialize (using=JsonDateSerializer.class)
    public Date getCreationDate() {
        return creationDate;
    }

    public String getContent() {
        return content;
    }
}
