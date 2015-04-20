package tchepannou.mora.rest.post.controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import tchepannou.mora.rest.post.dto.PostSummaryDto;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@Api (value="Posts", description = "Manage Posts")
public class PostController {
    private static final Logger LOG = LoggerFactory.getLogger(PostController.class);

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
    @ApiOperation ("Retrieve all Post published to current user")
    @RequestMapping("/users/me/posts")
    public List<PostSummaryDto> getCurrentUserPosts (@AuthenticationPrincipal Principal currentToken, @RequestParam int limit, @RequestParam int offset) {
        long userId = getCurrentUserId(currentToken);
        return userId > 0
                ? getUserPosts(userId, limit, offset)
                : Collections.emptyList();
    }

    @ApiOperation ("Retrieve all Post published to a User")
    @RequestMapping("/users/{userId}/posts")
    public List<PostSummaryDto> getUserPosts (@PathVariable long userId, @RequestParam int limit, @RequestParam int offset){
        /* get the posts */
        List<Long> ids = postService.findIdsPublishedForUser(userId, limit, offset);
        List<Post> posts = postService.findByIds(ids);
        Map<Long, Space> spaces = toSpaceMap(posts);
        Map<Long, User> users = toUserMap(posts);

        for (Post post : new ArrayList<>(posts)){
            User user = findUser(post.getUserId(), users);
            if (user == null){
                continue;
            }

            Space space = findSpace(post.getSpaceId(), spaces);
            if (space == null){
                continue;
            }
            posts.add(post);
        }

        /* convert to DTO */
        List<PostSummaryDto> result = new LinkedList<>();
        for (Post post : posts){
            PostSummaryDto dto = new PostSummaryDto.Builder()
                    .withPost(post)
                    .withSpace(spaces.get(post.getSpaceId()))
                    .withUser(users.get(post.getUserId()))
                    .build();

            result.add(dto);
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
        Set<Long> ids = new HashSet<>();
        for (Post post : posts){
            ids.add(post.getUserId());
        }
        LOG.info("userIds: " + ids);

        List<User> users = userService.findByIds(ids);
        LOG.info("users: " + users);
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
        LOG.info("spaceIds: " + ids);

        List<Space> spaces = spaceService.findByIds(ids);
        LOG.info("spaces: " + spaces);

        Map<Long, Space> result = new HashMap<>();
        for (Space space : spaces){
            result.put(space.getId(), space);
        }
        return result;
    }
    
    private User findUser (long userId, Map<Long, User> users){
        User result = users.get(userId);
        if (result == null){
            result = userService.findById(userId);
            if (result != null){
                users.put(userId, result);
            }
        }
        return result;
    }

    private Space findSpace (long spaceId, Map<Long, Space> spaces){
        Space result = spaces.get(spaceId);
        if (result == null){
            result = spaceService.findById(spaceId);
            if (result != null){
                spaces.put(spaceId, result);
            }
        }
        return result;
    }
}
