package network;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class LoccetService extends RestClient {

    public LoccetService() {
        super();
    }

    public Result<JsonElement> jefesProyecto(JsonObject parameters) {
        return request(LoccetAPI.TRABAJADORES_PROYECTO, parameters);
    }
}
