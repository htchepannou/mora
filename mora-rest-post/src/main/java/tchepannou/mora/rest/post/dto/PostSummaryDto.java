package tchepannou.mora.rest.post.dto;

import com.google.common.base.Preconditions;
import tchepannou.mora.core.domain.Post;
import tchepannou.mora.core.domain.Space;
import tchepannou.mora.core.domain.User;
import tchepannou.mora.rest.core.dto.ModelDto;

import java.util.Date;

public class PostSummaryDto extends ModelDto{
    //-- Attributes
    private long id;
    private String title;
    private String summary;
    private Date lastUpdate;
    private SpaceDto space;
    private UserDto user;


    //-- Builder
    public static class Builder{
        private Post post;
        private User user;
        private Space space;

        public PostSummaryDto build (){
            Preconditions.checkState(post != null, "post == null");

            Preconditions.checkState(space != null, "space == null");
            Preconditions.checkState(space.getId() == post.getSpaceId(), "post.spaceId != space.id");

            Preconditions.checkState(user != null, "user == null");
            Preconditions.checkState(user.getId() == post.getUserId(), "post.userId != user.id");

            PostSummaryDto result = new PostSummaryDto();
            result.id = post.getId();
            result.title = post.getTitle();
            result.summary = post.getSummary();
            result.lastUpdate = post.getLastUpdate();
            result.space = new SpaceDto.Builder().withSpace(space).build();
            result.user = new UserDto.Builder().withUser(user).build();
            return result;
        }

        public Builder withPost(Post post){
            this.post = post;
            return this;
        }
        public Builder withSpace(Space space){
            this.space = space;
            return this;
        }
        public Builder withUser (User author){
            this.user = author;
            return this;
        }
    }

    //-- Getter
    public UserDto getUser() {
        return user;
    }

    public long getId() {
        return id;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public String getSummary() {
        return summary;
    }

    public String getTitle() {
        return title;
    }

    public SpaceDto getSpace() {
        return space;
    }
}
