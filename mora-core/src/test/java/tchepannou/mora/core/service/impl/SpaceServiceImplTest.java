package tchepannou.mora.core.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tchepannou.mora.core.dao.SpaceDao;
import tchepannou.mora.core.domain.Space;
import tchepannou.mora.core.domain.SpaceType;
import tchepannou.mora.core.domain.User;
import tchepannou.mora.core.service.SpaceService;

import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SpaceServiceImplTest {
    @Mock
    private SpaceDao spaceDao;

    @InjectMocks
    private SpaceService service = new SpaceServiceImpl();

    @Test
    public void testFindById() throws Exception {
        // Given
        Space space = new Space (1, new SpaceType(1), new User(1));
        when(spaceDao.findById(1)).thenReturn(space);

        // When
        Space result = service.findById(1);

        // Then
        assertThat(result, equalTo(space));
    }



    @Test
    public void testCreate() throws Exception {
        // Given
        Date now = new Date ();
        Space space = new Space (0, new SpaceType(1), new User(1));
        space.setEmail("info@newyorksoccerclub.org");
        space.setWebsiteUrl("http://newyorksoccerclub.org");
        space.setLogoUrl("http://newyorksoccerclub.org/logo.png");
        space.setAbbreviation("NYSC");
        space.setAddress("4040 linton");
        space.setDescription("This is a nice desc");
        space.setName("New York Soccer Club");

        Space expected = new Space(space);

        // When
        service.create(space);

        // Then
        verify(spaceDao).create(space);
        assertThat(space.getCreationDate(), greaterThanOrEqualTo(now));
        assertThat(space.getLastUpdate(), greaterThanOrEqualTo(now));

        expected.setLastUpdate(space.getLastUpdate());
        expected.setCreationDate(space.getCreationDate());
        assertThat(space, equalTo(expected));
    }

    @Test
    public void testUpdate() throws Exception {
        Date now = new Date ();
        Space space = new Space (1, new SpaceType(1), new User(1));
        space.setEmail("info@newyorksoccerclub.org");
        space.setWebsiteUrl("http://newyorksoccerclub.org");
        space.setLogoUrl("http://newyorksoccerclub.org/logo.png");
        space.setAbbreviation("NYSC");
        space.setAddress("4040 linton");
        space.setDescription("This is a nice desc");
        space.setName("New York Soccer Club");

        Space expected = new Space(space);

        // When
        service.update(space);

        // Then
        verify(spaceDao).update(space);
        assertThat(space.getLastUpdate(), greaterThanOrEqualTo(now));

        expected.setLastUpdate(space.getLastUpdate());
        assertThat(space, equalTo(expected));

    }

    @Test
    public void testDelete() throws Exception {
        // Given
        Space space = new Space (1, new SpaceType(1), new User(1));

        // When
        service.delete(space);

        // Then
        verify(spaceDao).delete(space);
    }
}