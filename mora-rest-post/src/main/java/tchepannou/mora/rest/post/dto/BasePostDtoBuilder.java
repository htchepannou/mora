package tchepannou.mora.rest.post.dto;

import com.google.common.base.Preconditions;
import tchepannou.mora.core.domain.Post;
import tchepannou.mora.core.domain.Space;
import tchepannou.mora.core.domain.User;

//-- Builder
public abstract class BasePostDtoBuilder<T extends BasePostDto> {
    protected Post post;
    protected User user;
    protected Space space;

    protected abstract T createDto();

    public T build (){
        Preconditions.checkState(post != null, "post == null");

        Preconditions.checkState(space != null, "space == null");
        Preconditions.checkState(space.getId() == post.getSpaceId(), "post.spaceId != space.id");

        Preconditions.checkState(user != null, "user == null");
        Preconditions.checkState(user.getId() == post.getUserId(), "post.userId != user.id");

        T result = createDto();

        result.id = post.getId();
        result.title = post.getTitle();
        result.summary = post.getSummary();
        result.lastUpdate = post.getLastUpdate();
        result.space = new SpaceDto.Builder().withSpace(space).build();
        result.user = new UserDto.Builder().withUser(user).build();
        return result;
    }


    public BasePostDtoBuilder withPost(Post post){
        this.post = post;
        return this;
    }
    public BasePostDtoBuilder withSpace(Space space){
        this.space = space;
        return this;
    }
    public BasePostDtoBuilder withUser (User author){
        this.user = author;
        return this;
    }
}
