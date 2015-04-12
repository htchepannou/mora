package tchepannou.mora.core.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tchepannou.mora.core.dao.SpaceTypeDao;
import tchepannou.mora.core.domain.SpaceType;
import tchepannou.mora.core.service.SpaceTypeService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith (MockitoJUnitRunner.class)
public class SpaceTypeServiceImplTest {
    @Mock
    private SpaceTypeDao dao;

    @InjectMocks
    private SpaceTypeService service = new SpaceTypeServiceImpl();

    @Test
    public void testFindById() throws Exception {
        // Given
        SpaceType type = new SpaceType(1, "club");
        when(dao.findById(1)).thenReturn(type);

        // When
        SpaceType result = service.findById(1);

        // Then
        assertThat(result, equalTo(type));
    }

    @Test
    public void testFindAll() throws Exception {
        // Given
        SpaceType type1 = new SpaceType(1, "club");
        SpaceType type2 = new SpaceType(2, "team");
        when(dao.findAll()).thenReturn(Arrays.asList(type1, type2));

        // When
        List<SpaceType> result = service.findAll();

        // Then
        assertThat(result, hasSize(2));
        assertThat(result, hasItems(type1, type2));
    }
}