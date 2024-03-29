package network.endpoint;

import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import network.manager.NetworkManager;
import network.service.EndPointType;
import org.asynchttpclient.util.HttpConstants;

public enum MaterialAPI implements EndPointType {
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
    public HttpMethod httpMethod() {
        return HttpMethod.POST;
    }

    @Override
    public HttpHeaders httpHeaders() {
        HttpHeaders headers;
        headers = new DefaultHttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "application/json");
        return headers;
    }
}
