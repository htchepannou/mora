package tchepannou.mora.core.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tchepannou.mora.core.dao.RoleDao;
import tchepannou.mora.core.domain.Role;
import tchepannou.mora.core.service.RoleService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith (MockitoJUnitRunner.class)
public class RoleServiceImplTest {
    @Mock
    private RoleDao dao;

    @InjectMocks
    private RoleService service = new RoleServiceImpl();

    @Test
    public void testFindById() throws Exception {
        // Given
        Role type = new Role(1, "club");
        when(dao.findById(1)).thenReturn(type);

        // When
        Role result = service.findById(1);

        // Then
        assertThat(result, equalTo(type));
    }

    @Test
    public void testFindAll() throws Exception {
        // Given
        Role type1 = new Role(1, "club");
        Role type2 = new Role(2, "team");
        when(dao.findAll()).thenReturn(Arrays.asList(type1, type2));

        // When
        List<Role> result = service.findAll();

        // Then
        assertThat(result, hasSize(2));
        assertThat(result, hasItems(type1, type2));
    }
}