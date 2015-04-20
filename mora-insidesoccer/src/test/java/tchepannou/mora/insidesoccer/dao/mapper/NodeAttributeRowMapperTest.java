package tchepannou.mora.insidesoccer.dao.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tchepannou.mora.insidesoccer.domain.NodeAttribute;

import java.sql.ResultSet;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith (MockitoJUnitRunner.class)
public class NodeAttributeRowMapperTest {
    @Mock
    private ResultSet rs;

    @Test
    public void testMapRow() throws Exception {
        // Given
        when(rs.getLong("nattr_id")).thenReturn(1L);
        when(rs.getLong("nattr_node_fk")).thenReturn(11L);
        when(rs.getString("nattr_name")).thenReturn("title");
        when(rs.getString("nattr_value")).thenReturn("Hello world");

        // When
        NodeAttribute result = new NodeAttributeRowMapper().mapRow(rs, 0);

        // Then
        NodeAttribute expected = new NodeAttribute();
        expected.setId(1);
        expected.setNodeId(11);
        expected.setName("title");
        expected.setValue("Hello world");
        assertThat(result, equalTo(expected));
    }
}