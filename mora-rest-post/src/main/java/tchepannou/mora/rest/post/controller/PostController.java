package tchepannou.mora.rest.post.controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tchepannou.mora.core.domain.AccessToken;
import tchepannou.mora.core.domain.Post;
import tchepannou.mora.core.domain.Space;
import tchepannou.mora.core.domain.User;
import tchepannou.mora.core.service.AccessTokenService;
import tchepannou.mora.core.service.PostService;
import tchepannou.mora.core.service.SpaceService;
import tchepannou.mora.core.service.UserService;
import tchepannou.mora.rest.core.controller.BaseRestController;
import tchepannou.mora.rest.core.exception.NotFoundException;
import tchepannou.mora.rest.post.dto.PostDto;
import tchepannou.mora.rest.post.dto.PostSummaryDto;

import java.security.Principal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@Api (value="Users", description = "Manage Users' Posts")
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

    
    //-- REST methods
    @ApiOperation ("Retrieve all Posts published to current user")
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

    @ApiOperation ("Retrieve a Post")
    @RequestMapping("/posts/{postId}")
    public PostDto getPost (@PathVariable long postId){
        Post post = postService.findById(postId);
        if (post == null){
            throw new NotFoundException(postId);
        }

        Space space = spaceService.findById(post.getSpaceId());
        User user = userService.findById(post.getUserId());

        return (PostDto)new PostDto.Builder()
                .withPost(post)
                .withSpace(space)
                .withUser(user)
                .build();
    }

    //-- Private
    protected long getCurrentUserId (Principal currentToken) {
        String token = currentToken.getName();
        AccessToken accessToken = accessTokenService.findByValue(token);
        return accessToken != null ? accessToken.getUserId() : -1;
    }
    
    protected Map<Long, User> toUserMap(List<Post> posts){
        Set<Long> ids = new HashSet<>();
        for (Post post : posts){
            ids.add(post.getUserId());
        }

        List<User> users = userService.findByIds(ids);
        Map<Long, User> result = new HashMap<>();
        for (User user : users){
            result.put(user.getId(), user);
        }
        return result;
    }

    protected Map<Long, Space> toSpaceMap(List<Post> posts){
        Set<Long> ids = new HashSet<>();
        for (Post post : posts){
            ids.add(post.getSpaceId());
        }

        List<Space> spaces = spaceService.findByIds(ids);
        Map<Long, Space> result = new HashMap<>();
        for (Space space : spaces){
            result.put(space.getId(), space);
        }
        return result;
    }
}
