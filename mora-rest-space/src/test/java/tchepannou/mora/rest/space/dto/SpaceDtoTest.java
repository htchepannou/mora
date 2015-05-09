package tchepannou.mora.rest.space.dto;

import org.junit.Before;
import org.junit.Test;
import tchepannou.mora.core.domain.Space;
import tchepannou.mora.core.domain.SpaceType;
import tchepannou.mora.core.domain.User;
import tchepannou.mora.rest.core.dto.EnumDto;

import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class SpaceDtoTest {
    private Date now = new Date();;
    private User user = new User(1);;
    private SpaceType type = new SpaceType (1, "club");
    private Space space = new Space(1, type, user);

    @Before
    public void setUp (){
        space.setCreationDate(now);
        space.setLastUpdate(now);;
        space.setEmail("info@newyorksoccerclub.org");
        space.setWebsiteUrl("http://newyorksoccerclub.org");
        space.setLogoUrl("http://newyorksoccerclub.org/logo.png");
        space.setAbbreviation("NYSC");
        space.setAddress("4040 linton");
        space.setDescription("This is a nice desc");
        space.setName("New York Soccer Club");

    }

    @Test
    public void testBuilder (){
        // When
        SpaceDto result = new SpaceDto.Builder().withSpaceType(type).withSpace(space).build();

        // Then
        EnumDto expectedType = new EnumDto.Builder().withEnum(type).build();

        assertThat(result.getId(), equalTo(space.getId()));
        assertThat(result.getType(), equalTo(expectedType));
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

    @Test
    public void testBuilder_noSpace_throwsIllegalStateException (){
        new SpaceDto.Builder()
                .withSpaceType(type)
                .withSpace(null)
                .build();
    }


    @Test
    public void testBuilder_noType_throwsIllegalStateException (){
        new SpaceDto.Builder()
                .withSpaceType(null)
                .withSpace(space)
                .build();
    }


    @Test
    public void testBuilder_badType_throwsIllegalStateException (){
        new SpaceDto.Builder()
                .withSpaceType(new SpaceType(999))
                .withSpace(space)
                .build();
    }
}