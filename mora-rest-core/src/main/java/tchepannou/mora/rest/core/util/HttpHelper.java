package tchepannou.mora.rest.core.util;

import org.springframework.http.HttpHeaders;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

public class HttpHelper {
    public static final String LAST_MODIFIED_PATTERN = "EEE, dd MMM yyyy HH:mm:ss zzz";

    private HttpHelper(){

    }

    public static void addLastModified(HttpHeaders headers, Date date){
        String lastModified = new SimpleDateFormat(LAST_MODIFIED_PATTERN).format(date);
        headers.put("Last-Modified", Collections.singletonList(lastModified));
    }
}
