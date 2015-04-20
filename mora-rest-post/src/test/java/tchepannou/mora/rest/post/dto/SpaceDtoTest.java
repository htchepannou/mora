package tchepannou.mora.rest.post.dto;

import org.junit.Test;
import tchepannou.mora.core.domain.Space;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class SpaceDtoTest {
    @Test
    public void testBuilder (){
        // Given
        Space space = new Space(1);
        space.setEmail("info@newyorksoccerclub.org");
        space.setWebsiteUrl("http://newyorksoccerclub.org");
        space.setLogoUrl("http://newyorksoccerclub.org/logo.png");
        space.setAbbreviation("NYSC");
        space.setAddress("4040 linton");
        space.setDescription("This is a nice desc");
        space.setName("New York Soccer Club");

        // When
        SpaceDto result = new SpaceDto.Builder().withSpace(space).build();

        // Then
        assertThat(result.getId(), equalTo(space.getId()));
        assertThat(result.getLogoUrl(), equalTo(space.getLogoUrl()));
        assertThat(result.getName(), equalTo(space.getName()));
    }
}