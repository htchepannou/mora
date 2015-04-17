package tchepannou.mora.core.service;

import java.io.IOException;

public interface UrlFetchService {
    HttpResponse fetch(HttpRequest request) throws IOException;
}
