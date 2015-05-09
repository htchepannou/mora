package tchepannou.mora.rest.core.dto;

import org.junit.Test;
import tchepannou.mora.core.domain.Space;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class SpaceSummaryDtoTest {
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
        SpaceSummaryDto result = new SpaceSummaryDto.Builder()
                .withSpace(space)
                .build();

        // Then
        assertThat(result.getId(), equalTo(space.getId()));
        assertThat(result.getLogoUrl(), equalTo(space.getLogoUrl()));
        assertThat(result.getName(), equalTo(space.getName()));
    }


    @Test(expected = IllegalStateException.class)
    public void testBuilder_noSpace_throwsIllegalStateException (){
        new SpaceSummaryDto.Builder().build();

    }

}