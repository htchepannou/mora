package tchepannou.mora.rest.post.controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tchepannou.mora.core.domain.Post;
import tchepannou.mora.core.domain.Space;
import tchepannou.mora.core.domain.User;
import tchepannou.mora.core.service.PostService;
import tchepannou.mora.core.service.SpaceService;
import tchepannou.mora.core.service.UserService;
import tchepannou.mora.rest.post.dto.PostSummaryDto;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@Api (value="Posts", description = "Manage Posts")
public class PostController {
    //-- Attribute
    @Autowired
    private PostService postService;
    
    @Autowired
    private SpaceService spaceService;
    
    @Autowired
    private UserService userService;
    
    
    //-- REST methods
    @ApiOperation ("Retrieve all Post published to a User")
    @RequestMapping("/users/{userId}/posts")
    public List<PostSummaryDto> post (@PathVariable long userId, @RequestParam int limit, @RequestParam int offset){
        /* get the posts */
        List<Long> ids = postService.findIdsPublishedForUser(userId, limit, offset);
        List<Post> posts = new LinkedList<>();
        Map<Long, Space> spaces = new HashMap<>();
        Map<Long, User> users = new HashMap<>();
        for (long id : ids){
            Post post = postService.findById(id);
            if (post == null){
                continue;
            }
            
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
