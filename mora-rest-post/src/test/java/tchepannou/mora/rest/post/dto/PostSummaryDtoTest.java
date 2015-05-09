package tchepannou.mora.rest.post.dto;

import org.apache.commons.lang.time.DateUtils;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import tchepannou.mora.core.domain.Post;
import tchepannou.mora.core.domain.Space;
import tchepannou.mora.core.domain.User;

import java.util.Date;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class PostSummaryDtoTest {
    Date now = new Date ();
    Date yesterday = DateUtils.addDays(now, -1);
    User user = new User(10);
    Space space = new Space(1);
    Post post = new Post (100, space, user);

    @Before
    public void setUp (){
        space.setEmail("info@newyorksoccerclub.org");
        space.setWebsiteUrl("http://newyorksoccerclub.org");
        space.setLogoUrl("/logo.png");
        space.setAbbreviation("NYSC");
        space.setAddress("4040 linton");
        space.setDescription("This is a nice desc");
        space.setName("New York Soccer Club");

        user.setFirstName("Ray");
        user.setLastName("Sponsible");

        post.setTitle( "title1");
        post.setContent("bar");;
        post.setSummary("summary1");
        post.setLastUpdate(now);
        post.setCreationDate(yesterday);        
    }
    
    @Test
    public void testBuilder (){

        // When
        PostSummaryDto result = (PostSummaryDto)new PostSummaryDto.Builder()
                .withSpace(space)
                .withUser(user)
                .withPost(post)
                .build();

        // Then
        assertThat(result.getId(), CoreMatchers.equalTo(post.getId()));
        assertThat(result.getTitle(), CoreMatchers.equalTo(post.getTitle()));
        assertThat(result.getSummary(), CoreMatchers.equalTo(post.getSummary()));
        assertThat(result.getLastUpdate(), CoreMatchers.equalTo(post.getLastUpdate()));

        assertThat(result.getSpace().getId(), CoreMatchers.equalTo(space.getId()));
        assertThat(result.getSpace().getLogoUrl(), CoreMatchers.equalTo(space.getLogoUrl()));
        assertThat(result.getSpace().getName(), CoreMatchers.equalTo(space.getName()));

        assertThat(result.getUser().getId(), equalTo(user.getId()));
        assertThat(result.getUser().getName(), equalTo("Ray Sponsible"));
    }
    

    @Test(expected = IllegalStateException.class)
    public void testBuilder_noPost_throwsIllegalStateException () {
        // When
        new PostSummaryDto.Builder().withSpace(space).withUser(user).withPost(null).build();
    }

    @Test(expected = IllegalStateException.class)
    public void testBuilder_noSpace_throwsIllegalStateException () {
        // When
        new PostSummaryDto.Builder()
                .withSpace(null)
                .withUser(user)
                .withPost(post)
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void testBuilder_badSpace_throwsIllegalStateException () {
        // When
        new PostSummaryDto.Builder()
                .withSpace(new Space(999))
                .withUser(user)
                .withPost(post)
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void testBuilder_noUser_throwsIllegalStateException () {
        // When
        new PostSummaryDto.Builder()
                .withSpace(space)
                .withUser(null)
                .withPost(post)
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void testBuilder_badUser_throwsIllegalStateException () {
        // When
        new PostSummaryDto.Builder()
                .withSpace(space)
                .withUser(new User(99))
                .withPost(post)
                .build();
    }    
}