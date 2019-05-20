package network;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.reactivex.Maybe;

import java.io.IOException;

public class LoccetService extends RestClient {

    private static LoccetService instace;

    private LoccetService() {
        super();
    }

    public static LoccetService getInstace() {
        if (instace == null)
            instace = new LoccetService();
        return instace;
    }

    /*public JsonElement jefesProyecto(JsonObject parameters) throws IOException, NetworkException {
        return request(LoccetAPI.TRABAJADORES_PROYECTO, parameters);
    }*/

    public Maybe<JsonElement> call(LoccetAPI api, JsonObject parameters) {
        return request(api, parameters);
    }


}
