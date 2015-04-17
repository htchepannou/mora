package tchepannou.mora.core.service;

import java.io.IOException;
import java.io.InputStream;

public interface HttpResponse {
    public int getStatusCode();

    public String getContentType();

    public InputStream getInputStream() throws IOException;

    public void close() throws IOException;
}
