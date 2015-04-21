package tchepannou.mora.rest.post.dto;

public class PostSummaryDto extends BasePostDto{
    //-- Builder
    public static class Builder extends BasePostDtoBuilder{
        @Override
        protected BasePostDto createDto() {
            return new PostSummaryDto();
        }
    }
}
