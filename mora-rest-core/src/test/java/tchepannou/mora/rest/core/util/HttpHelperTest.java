package tchepannou.mora.rest.core.util;

import org.junit.Test;
import org.springframework.http.HttpHeaders;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertThat;

public class HttpHelperTest {
    HttpHeaders headers = new HttpHeaders();

    @Test
    public void testAddLastModified() throws Exception {
        // Given
        Date date = new Date ();

        // When
        HttpHelper.addLastModified(headers, date);

        // Then
        List<String> lst = headers.get("Last-Modified");
        SimpleDateFormat fmt = new SimpleDateFormat(HttpHelper.LAST_MODIFIED_PATTERN);
        String xdate = fmt.format(date);

        assertThat(lst, allOf(hasItems(xdate)));

    }
}