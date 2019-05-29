package network.API;

import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import network.NetworkManager;
import network.URLRequestConvertible;
import org.asynchttpclient.util.HttpConstants;

public enum ProyectoAPI implements URLRequestConvertible {
    CREATE,
    UPDATE,
    REMOVE;

    @Override
    public String baseURL() {
        switch (NetworkManager.enviroment) {
            case PRODUCTION:
                return "";
            case DEVELOPMENT:
                return "https://djmte8zah9.execute-api.us-east-1.amazonaws.com/dev/";
        }

        return "";
    }

    @Override
    public String path() {
        switch (this) {
            case CREATE:
                return "loccet-addproyecto";
            case UPDATE:
                return "loccet-updateproyecto";
            case REMOVE:
                return "loccet-removeproyecto";
        }

        return "";
    }

    @Override
    public String method() {
        return HttpConstants.Methods.POST;
    }

    @Override
    public HttpHeaders headers() {
        HttpHeaders headers;
        headers = new DefaultHttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "application/json");
        return headers;
    }
}
