package tchepannou.mora.rest.post.dto;

import org.apache.commons.lang.time.DateUtils;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import tchepannou.mora.core.domain.Post;
import tchepannou.mora.core.domain.Space;
import tchepannou.mora.core.domain.User;

import java.util.Date;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class PostSummaryDtoTest {
    @Test
    public void testBuilder (){
        // Given
        Date now = new Date ();
        Date yesterday = DateUtils.addDays(now, -1);

        Space space = new Space(1);
        space.setEmail("info@newyorksoccerclub.org");
        space.setWebsiteUrl("http://newyorksoccerclub.org");
        space.setLogoUrl("/logo.png");
        space.setAbbreviation("NYSC");
        space.setAddress("4040 linton");
        space.setDescription("This is a nice desc");
        space.setName("New York Soccer Club");

        User user = new User(10);
        user.setFirstName("Ray");
        user.setLastName("Sponsible");

        Post post = new Post (100, space, user);
        post.setTitle( "title1");
        post.setContent("bar");;
        post.setSummary("summary1");
        post.setLastUpdate(now);
        post.setCreationDate(yesterday);

        // When
        PostSummaryDto result = new PostSummaryDto.Builder()
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
        assertThat(result.getUser().getFirstName(), equalTo(user.getFirstName()));
        assertThat(result.getUser().getLastName(), equalTo(user.getLastName()));

    }
}