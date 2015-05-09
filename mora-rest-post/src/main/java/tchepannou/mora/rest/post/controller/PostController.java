package tchepannou.mora.rest.post.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tchepannou.mora.core.domain.AccessToken;
import tchepannou.mora.core.domain.AttachmentType;
import tchepannou.mora.core.domain.Media;
import tchepannou.mora.core.domain.Post;
import tchepannou.mora.core.domain.Space;
import tchepannou.mora.core.domain.User;
import tchepannou.mora.core.service.AccessTokenService;
import tchepannou.mora.core.service.MediaService;
import tchepannou.mora.core.service.MediaTypeService;
import tchepannou.mora.core.service.OembedService;
import tchepannou.mora.core.service.PostService;
import tchepannou.mora.core.service.SpaceService;
import tchepannou.mora.core.service.UserService;
import tchepannou.mora.rest.core.controller.BaseRestController;
import tchepannou.mora.rest.core.exception.NotFoundException;
import tchepannou.mora.rest.post.dto.MediaDto;
import tchepannou.mora.rest.post.dto.PostDto;
import tchepannou.mora.rest.post.dto.PostSummaryDto;

import java.security.Principal;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class PostController extends BaseRestController{
    //-- Attribute
    @Autowired
    private PostService postService;
    
    @Autowired
    private SpaceService spaceService;
    
    @Autowired
    private UserService userService;

    @Autowired
    private AccessTokenService accessTokenService;

    @Autowired
    private MediaService mediaService;

    @Autowired
    private MediaTypeService mediaTypeService;

    @Autowired
    private OembedService oembedService;

    
    //-- REST methods
    @RequestMapping("/posts")
    public List<PostSummaryDto> getCurrentUserPosts (@AuthenticationPrincipal Principal currentToken, @RequestParam int limit, @RequestParam int offset) {
        /* get the posts */
        long userId = getCurrentUserId(currentToken);
        List<Long> ids = postService.findIdsPublishedForUser(userId, limit, offset);
        List<Post> posts = postService.findByIds(ids);
        Map<Long, Space> spaces = toSpaceMap(posts);
        Map<Long, User> users = toUserMap(posts);

        /* convert to DTO */
        List<PostSummaryDto> result = new LinkedList<>();
        for (Post post : posts){
            Space space = spaces.get(post.getSpaceId());
            User user = users.get(post.getUserId());
            if (space == null || user == null){
                continue;
            }

            PostSummaryDto dto = (PostSummaryDto)new PostSummaryDto.Builder()
                    .withPost(post)
                    .withSpace(space)
                    .withUser(user)
                    .build();

            result.add(dto);
        }
        return result;
    }

    @RequestMapping("/posts/{postId}")
    public PostDto getPost (@PathVariable long postId){
        Post post = postService.findById(postId);
        if (post == null){
            throw new NotFoundException(postId);
        }

        Space space = spaceService.findById(post.getSpaceId());
        User user = userService.findById(post.getUserId());

        PostDto result = (PostDto)new PostDto.Builder()
                .withPost(post)
                .withSpace(space)
                .withUser(user)
                .build();

        List<Media> medias = mediaService.findByOwnerByAttachmentType(postId, AttachmentType.POST);
        for (Media media : medias){
            MediaDto mediaDto = new MediaDto.Builder()
                    .withMedia(media)
                    .withOembedService(oembedService)
                    .withMediaTypeService(mediaTypeService)
                    .build();

            result.addMedia(mediaDto);
        }
        return result;
    }

    //-- Private
    protected long getCurrentUserId (Principal currentToken) {
        String token = currentToken.getName();
        AccessToken accessToken = accessTokenService.findByValue(token);
        return accessToken != null ? accessToken.getUserId() : -1;
    }
    
    protected Map<Long, User> toUserMap(List<Post> posts){
        Set<Long> ids = posts.stream()
                .map(event -> event.getUserId())
                .collect(Collectors.toSet());

        List<User> users = userService.findByIds(ids);
        return User.map(users);
    }

    protected Map<Long, Space> toSpaceMap(List<Post> posts){
        Set<Long> ids = posts.stream()
                .map(event -> event.getSpaceId())
                .collect(Collectors.toSet());

        List<Space> spaces = spaceService.findByIds(ids);
        return Space.map(spaces);
    }
}
