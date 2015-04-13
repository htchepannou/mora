package tchepannou.mora.rest.space.dto;

import org.junit.Test;
import tchepannou.mora.core.domain.SpaceType;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class SpaceTypeDtoTest {
    @Test
    public void testBuilder (){
        SpaceType type = new SpaceType (1, "club");

        SpaceTypeDto result = new SpaceTypeDto.Builder().withSpaceType(type).build();

        assertThat(result.getId(), equalTo(type.getId()));
        assertThat(result.getName(), equalTo(type.getName()));
    }

}