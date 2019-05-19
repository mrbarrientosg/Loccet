package network;

import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import org.asynchttpclient.util.HttpConstants;

public enum LoccetAPI implements URLRequestConvertible  {
    TRABAJADORES_PROYECTO,
    LOGIN;

    private HttpHeaders _headers;

    public String baseURL() {
        switch (NetworkManager.enviroment) {
            case PRODUCTION:
                return "";
            case DEVELOPMENT:
                return "https://djmte8zah9.execute-api.us-east-1.amazonaws.com/dev/";
        }

        return null;
    }

    public String path() {
        switch (this) {
            case TRABAJADORES_PROYECTO:
                return "trabajadoresProyecto";
            case LOGIN:
                return "login";
        }

        return null;
    }

    @Override
    public String method() {
        return HttpConstants.Methods.POST;
    }

    @Override
    public HttpHeaders headers() {
        if (_headers == null) {
            _headers = new DefaultHttpHeaders();
            _headers.add("Content-Type", "application/json");
            _headers.add("Accept", "application/json");
        }

        return _headers;
    }
}
