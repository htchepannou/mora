package tchepannou.mora.rest.space.dto;

import org.junit.Test;
import tchepannou.mora.core.domain.Space;
import tchepannou.mora.core.domain.SpaceType;
import tchepannou.mora.core.domain.User;

import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class SpaceDtoTest {
    @Test
    public void testBuilder (){
        // Given
        Date now = new Date();
        User user = new User(1);
        SpaceType type = new SpaceType (1, "club");
        Space space = new Space(1, type, user);
        space.setCreationDate(now);
        space.setLastUpdate(now);;
        space.setEmail("info@newyorksoccerclub.org");
        space.setWebsiteUrl("http://newyorksoccerclub.org");
        space.setLogoUrl("http://newyorksoccerclub.org/logo.png");
        space.setAbbreviation("NYSC");
        space.setAddress("4040 linton");
        space.setDescription("This is a nice desc");
        space.setName("New York Soccer Club");

        // When
        SpaceDto result = new SpaceDto.Builder().withSpaceType(type).withSpace(space).build();

        // Then
        assertThat(result.getId(), equalTo(space.getId()));
        assertThat(result.getType(), equalTo(new SpaceTypeDto (1, "club")));
        assertThat(result.getCreationDate(), equalTo(space.getCreationDate()));
        assertThat(result.getLastUpdate(), equalTo(space.getLastUpdate()));
        assertThat(result.getEmail(), equalTo(space.getEmail()));
        assertThat(result.getWebsiteUrl(), equalTo(space.getWebsiteUrl()));
        assertThat(result.getLogoUrl(), equalTo(space.getLogoUrl()));
        assertThat(result.getAbbreviation(), equalTo(space.getAbbreviation()));
        assertThat(result.getAddress(), equalTo(space.getAddress()));
        assertThat(result.getDescription(), equalTo(space.getDescription()));
        assertThat(result.getName(), equalTo(space.getName()));
    }
}