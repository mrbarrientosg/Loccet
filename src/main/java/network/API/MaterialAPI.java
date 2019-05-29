package network.API;

import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import network.NetworkManager;
import network.URLRequestConvertible;
import org.asynchttpclient.util.HttpConstants;

public enum MaterialAPI implements URLRequestConvertible {
    CREATE,
    UPDATE,
    REMOVE,
    ADD_REGISTROMATERIAL;

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
                return "loccet-addmaterial";
            case UPDATE:
                return "loccet-updatematerial";
            case REMOVE:
                return "loccet-removematerial";
            case ADD_REGISTROMATERIAL:
                return "loccet-addregistromaterial";
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
