package tchepannou.mora.core.service;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class HttpRequest {
    public static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_3) AppleWebKit/537.75.14 (KHTML, like Gecko) Version/7.0.3 Safari/7046A194A";
    public static final String CHARSET = "utf-8";
    public static final String REFERER = "http://www.google.ca";
    public static final int TIMEOUT = 30000;

    //-- Attributes
    private URL url;
    private String userAgent;
    private String charset;
    private String referer;
    private int timeout = TIMEOUT;


    //-- Constructor
    private HttpRequest (Builder builder){
        this.url = builder.url;
        this.userAgent = builder.userAgent;
        this.timeout = builder.timeout;
        this.charset = builder.charset;
        this.referer = builder.referer;
    }

    //-- Public
    public URLConnection openConnection() throws IOException{
        return url.openConnection();
    }

    //-- Getter
    public URL getUrl() {
        return url;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public int getTimeout() {
        return timeout;
    }

    public String getCharset() {
        return charset;
    }

    public String getReferer() {
        return referer;
    }

    //-- Inner classes
    public static class Builder {
        private URL url;
        private String userAgent;
        private String charset;
        private int timeout;
        private String referer;

        public HttpRequest build (){
            if (url == null){
                throw new IllegalStateException("url not set");
            }
            return new HttpRequest(this);
        }

        public Builder withUrl (URL url){
            this.url = url;
            return this;
        }

        public Builder withUserAgent (String userAgent){
            this.userAgent = userAgent;
            return this;
        }

        public Builder withTimeout (int timeout){
            this.timeout = timeout;
            return this;
        }
        public Builder withCharset (String charset){
            this.charset = charset;
            return this;
        }
        public Builder withReferer (String referer){
            this.referer = referer;
            return this;
        }
    }
}
