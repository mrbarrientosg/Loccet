package network.service;

import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;

import java.net.URL;

public interface EndPointType {
    public String baseURL();
    public String path();
    public HttpMethod httpMethod();
    public HttpHeaders httpHeaders();
}
