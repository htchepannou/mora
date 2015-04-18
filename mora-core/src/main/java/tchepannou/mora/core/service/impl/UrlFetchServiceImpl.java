package tchepannou.mora.core.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tchepannou.mora.core.service.HttpRequest;
import tchepannou.mora.core.service.HttpResponse;
import tchepannou.mora.core.service.UrlFetchService;

import java.io.IOException;
import java.net.HttpURLConnection;

public class UrlFetchServiceImpl implements UrlFetchService{
    //-- Constants
    private static final Logger LOG = LoggerFactory.getLogger(UrlFetchServiceImpl.class);

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


        HttpResponse resp = createHttpResponse(cnn);

        if (LOG.isDebugEnabled()) {
            LOG.debug(String.format("GET %s %s\n%d", request.getUrl(), resp.getStatusCode()));
        }
        return resp;
    }

    //-- Private
    private HttpResponse createHttpResponse (final HttpURLConnection cnn){
        return new HttpResponseImpl(cnn);
    }
}
