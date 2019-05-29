package network;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.reactivex.Maybe;

import java.io.IOException;

public class LoccetService extends RestClient {

    private static LoccetService instance;

    private LoccetService() {
        super();
    }

    public static LoccetService getInstance() {
        if (instance == null)
            instance = new LoccetService();
        return instance;
    }

    public Maybe<JsonElement> call(URLRequestConvertible api, JsonObject parameters) {
        return request(api, parameters);
    }

}
