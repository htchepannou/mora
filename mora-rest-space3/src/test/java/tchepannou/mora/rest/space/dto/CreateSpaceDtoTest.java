package tchepannou.mora.rest.space.dto;

import org.junit.Test;
import tchepannou.mora.core.domain.Space;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class CreateSpaceDtoTest {

    @Test
    public void testToSpace() throws Exception {
        // Given
        CreateSpaceDto dto = new CreateSpaceDto();
        dto.setTypeId(1);
        dto.setDescription("desc");
        dto.setAbbreviation("abbrev");
        dto.setAddress("address");
        dto.setEmail("email@gmail.com");
        dto.setName("name");
        dto.setWebsiteUrl("http://www.web.com");

        Space space = new Space();

        // When
        dto.toSpace(space);
        
        // Then
        Space expected = new Space();
        expected.setTypeId(1);
        expected.setDescription("desc");
        expected.setAbbreviation("abbrev");
        expected.setAddress("address");
        expected.setEmail("email@gmail.com");
        expected.setName("name");
        expected.setWebsiteUrl("http://www.web.com");

        assertThat(space, equalTo(expected));
    }
}