package network;

import io.netty.handler.codec.http.HttpHeaders;

public interface URLRequestConvertible  {
    public String baseURL();
    public String path();
    public String method();
    public HttpHeaders headers();
}
