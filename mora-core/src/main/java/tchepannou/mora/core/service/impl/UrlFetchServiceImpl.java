package tchepannou.mora.core.service.impl;

import tchepannou.mora.core.service.HttpRequest;
import tchepannou.mora.core.service.HttpResponse;
import tchepannou.mora.core.service.UrlFetchService;

import java.io.IOException;
import java.net.HttpURLConnection;

public class UrlFetchServiceImpl implements UrlFetchService{
    //-- Constants
    public static final String HEADER_USER_AGENT = "User-Agent";
    public static final String HEADER_REFERER = "Referer";
    public static final String HEADER_ACCEPT_CHARSET = "Accept-Charset";


    //-- UrlFetchService overrides
    @Override
    public HttpResponse fetch(HttpRequest request) throws IOException {
        HttpURLConnection cnn = (HttpURLConnection)request.openConnection();

        if (request.getUserAgent() != null) {
            cnn.setRequestProperty(HEADER_USER_AGENT, request.getUserAgent());
        }
        if (request.getReferer() != null) {
            cnn.setRequestProperty(HEADER_REFERER, request.getReferer());
        }
        if (request.getCharset() != null) {
            cnn.setRequestProperty(HEADER_ACCEPT_CHARSET, request.getCharset());
        }

        cnn.setInstanceFollowRedirects(true);
        cnn.setConnectTimeout(request.getTimeout());

        return createHttpResponse(cnn);
    }

    //-- Private
    private HttpResponse createHttpResponse (final HttpURLConnection cnn){
        return new HttpResponseImpl(cnn);
    }

}
