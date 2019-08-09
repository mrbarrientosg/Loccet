package util.chain;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.function.Consumer;

public class DatabaseFetcher {

    private FetchHandler head;

    private FetchHandler tail;

    public void fetch(JsonObject parameters, Gson deserializer, Consumer<FetchResult> result) {
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

    public void clear() {
        for (FetchHandler x = head; x != null; ) {
            FetchHandler next = x.getNext();
            x.setNext(null);
            x.dispose();
            x = next;
        }

        head = tail = null;
    }
}
