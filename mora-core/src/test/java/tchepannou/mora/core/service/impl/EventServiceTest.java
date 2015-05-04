package tchepannou.mora.core.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tchepannou.mora.core.dao.EventDao;
import tchepannou.mora.core.domain.Event;
import tchepannou.mora.core.service.EventService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;

@RunWith (MockitoJUnitRunner.class)
public class EventServiceTest {
    @Mock
    private EventDao dao;

    @InjectMocks
    private EventService service = new EventServiceImpl();


    @Test
    public void testFindById() throws Exception {
        Event expected = new Event(1);
        when(dao.findById(1)).thenReturn(expected);

        Event result = service.findById(1);

        assertThat(result, equalTo(expected));
    }



    @Test
    public void testFindByIds() throws Exception {
        List<Event> expected = Arrays.asList(new Event(11), new Event(12));
        when(dao.findByIds(Arrays.asList(11L, 12L))).thenReturn(expected);

        List<Event> result = service.findByIds(Arrays.asList(11L, 12L));

        assertThat(result, hasSize(2));
        assertThat(result, hasItems(new Event(11), new Event(12)));
    }

    @Test
    public void testFindIdsUpcomingForUser() throws Exception {
        List<Long> expected = Arrays.asList(11L, 12L, 13L);
        when(dao.findIdsUpcomingForUser(1, 100, 10)).thenReturn(expected);

        List<Long> result = service.findIdsUpcomingForUser(1L, 100, 10);

        assertThat(result, hasSize(3));
        assertThat(result, equalTo(expected));
    }
}