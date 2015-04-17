package tchepannou.mora.core.service.impl;

import tchepannou.mora.core.service.HttpResponse;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

class HttpResponseImpl implements HttpResponse {
    private final HttpURLConnection cnn;

    public HttpResponseImpl(HttpURLConnection cnn) {
        this.cnn = cnn;
    }

    @Override
    public int getStatusCode() {
        try {
            return cnn.getResponseCode();
        } catch (IOException e){
            throw new IllegalStateException("Unable to get status code", e);
        }
    }

    @Override
    public String getContentType() {
        return cnn.getContentType();
    }

    @Override
    public InputStream getInputStream() throws IOException{
        return cnn.getInputStream();
    }

    @Override
    public void close() {
        cnn.disconnect();
    }
}
