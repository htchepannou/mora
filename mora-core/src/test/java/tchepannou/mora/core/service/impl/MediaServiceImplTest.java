package tchepannou.mora.core.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tchepannou.mora.core.dao.MediaDao;
import tchepannou.mora.core.domain.Media;
import tchepannou.mora.core.service.MediaService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith (MockitoJUnitRunner.class)
public class MediaServiceImplTest {
    @Mock
    private MediaDao dao;

    @InjectMocks
    private MediaService service = new MediaServiceImpl();

    @Test
    public void testFindByOwnerByAttachmentType() throws Exception {
        // Given
        Media m1 = new Media(1);
        Media m2 = new Media(1);
        Media m3 = new Media(1);
        when(dao.findByOwnerByAttachmentType(1, 2)).thenReturn(Arrays.asList(m1, m2, m3));

        // When
        List<Media> result = service.findByOwnerByAttachmentType(1, 2);

        // Then
        assertThat(result, hasSize(3));
        assertThat(result, hasItems(m1, m2, m3));
    }
}