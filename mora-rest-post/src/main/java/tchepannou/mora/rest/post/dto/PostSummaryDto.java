package tchepannou.mora.rest.post.dto;

public class PostSummaryDto extends BasePostDto{
    //-- Builder
    public static class Builder extends BasePostDtoBuilder<PostSummaryDto>{
        @Override
        protected PostSummaryDto createDto() {
            return new PostSummaryDto();
        }
    }
}
