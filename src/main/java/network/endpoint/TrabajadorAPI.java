package network.endpoint;

import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import network.manager.NetworkManager;
import network.service.EndPointType;

public enum TrabajadorAPI implements EndPointType {
    CREATE,
    UPDATE,
    REMOVE,
    ADD_TO_PROJECT,
    REMOVE_FROM_PROJECT;

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
                return "loccet-addtrabajador";
            case UPDATE:
                return "loccet-updatetrabajador";
            case REMOVE:
                return "loccet-removetrabajador";
            case ADD_TO_PROJECT:
                return "loccet-addtrabajadorproyecto";
            case REMOVE_FROM_PROJECT:
                return "loccet-removetrabajadorproyecto";
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
