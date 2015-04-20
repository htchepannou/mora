package tchepannou.mora.core.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tchepannou.mora.core.dao.PostDao;
import tchepannou.mora.core.domain.Post;
import tchepannou.mora.core.service.PostService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith (MockitoJUnitRunner.class)
public class PostServiceImplTest {

    @Mock
    private PostDao dao;

    @InjectMocks
    private PostService service = new PostServiceImpl();

    @Test
    public void testFindById() throws Exception {
        // Given
        Post expected = new Post(1);
        when(dao.findById(1L)).thenReturn(expected);

        // Then
        Post result = service.findById(1);

        // Then
        assertThat(result, equalTo(expected));
    }

    @Test
    public void testFindIdsPublishedForUser() throws Exception {
        // Given
        List<Long> expected = Arrays.asList(1L, 2L, 3L);
        when(dao.findIdsPublishedForUser(1, 100, 0)).thenReturn(expected);

        // Then
        List<Long> result = service.findIdsPublishedForUser(1, 100, 0);

        // Then
        assertThat(result, equalTo(expected));

    }

    @Test
    public void testCreate() throws Exception {
        // Given
        Post post = new Post();

        // When
        Post result = service.create(post);

        // Then
        assertThat(result, equalTo(post));
        assertThat(result.getCreationDate(), notNullValue());
        assertThat(result.getLastUpdate(), notNullValue());

        verify(dao).create(post);
    }

    @Test
    public void testUpdate() throws Exception {
        // Given
        Post post = new Post();

        // When
        Post result = service.update(post);

        // Then
        assertThat(result, equalTo(post));
        assertThat(result.getLastUpdate(), notNullValue());

        verify(dao).update(post);
    }

    @Test
    public void testDelete() throws Exception {
        // Given
        Post post = new Post();

        // When
        Post result = service.delete(post);

        // Then
        assertThat(result, equalTo(post));
        verify(dao).delete(post);
    }
}