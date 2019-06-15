package util.chain;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.function.Consumer;

public class DatabaseFetcher {

    private FetchHandler head;

    private FetchHandler tail;

    public void fetch(JsonObject parameters, Gson deserializer, Consumer<Result> result) {
       head.handle(parameters, deserializer, result);
    }

    public DatabaseFetcher add(FetchHandler handler) {
        if (head == null) {
            head = handler;
        } else {
            tail.setNext(handler);
        }

        tail = handler;

        return this;
    }
}
