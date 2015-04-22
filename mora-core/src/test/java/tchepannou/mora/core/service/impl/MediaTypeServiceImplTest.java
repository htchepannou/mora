package tchepannou.mora.core.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tchepannou.mora.core.dao.MediaTypeDao;
import tchepannou.mora.core.domain.MediaType;
import tchepannou.mora.core.service.MediaTypeService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith (MockitoJUnitRunner.class)
public class MediaTypeServiceImplTest {
    @Mock
    private MediaTypeDao dao;

    @InjectMocks
    private MediaTypeService service = new MediaTypeServiceImpl();

    @Test
    public void testFindById() throws Exception {
        // Given
        MediaType type = new MediaType(1, "club");
        when(dao.findById(1)).thenReturn(type);

        // When
        MediaType result = service.findById(1);

        // Then
        assertThat(result, equalTo(type));
    }

    @Test
    public void testFindAll() throws Exception {
        // Given
        MediaType type1 = new MediaType(1, "club");
        MediaType type2 = new MediaType(2, "team");
        when(dao.findAll()).thenReturn(Arrays.asList(type1, type2));

        // When
        List<MediaType> result = service.findAll();

        // Then
        assertThat(result, hasSize(2));
        assertThat(result, hasItems(type1, type2));
    }
}