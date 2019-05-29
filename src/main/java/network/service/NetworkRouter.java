package network.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.reactivex.Maybe;

import java.io.IOException;

public interface NetworkRouter<EndPoint extends  EndPointType> {
    public Maybe<JsonElement> request(EndPoint router, JsonObject parameters);
    public void close() throws IOException;
}
