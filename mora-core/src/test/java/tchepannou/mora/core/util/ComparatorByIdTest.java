package tchepannou.mora.core.util;

import org.junit.Test;
import tchepannou.mora.core.domain.Role;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class ComparatorByIdTest {

    @Test
    public void testCompare() throws Exception {
        List<Long> ids = Arrays.asList(2L, 4L, 3L, 1L);
        Role r1 = new Role(1, "foo1");
        Role r2 = new Role(2, "foo2");

        int result = new ComparatorById<Role>(ids).compare(r1, r2);

        assertThat(result, equalTo(3));
    }

    @Test
    public void testCompare_BadId() throws Exception {
        List<Long> ids = Arrays.asList(2L, 4L, 3L, 1L);
        Role r1 = new Role(9999, "foo1");
        Role r2 = new Role(2, "foo2");

        int result = new ComparatorById<Role>(ids).compare(r1, r2);

        assertThat(result, equalTo(Integer.MAX_VALUE));
    }
}