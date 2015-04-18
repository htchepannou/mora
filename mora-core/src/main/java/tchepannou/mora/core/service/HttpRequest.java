package tchepannou.mora.core.service;

import com.google.common.base.Preconditions;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class HttpRequest {
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
        private int timeout = TIMEOUT;
        private String referer;

        public HttpRequest build (){
            Preconditions.checkState(this.url != null, "url==null");
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
