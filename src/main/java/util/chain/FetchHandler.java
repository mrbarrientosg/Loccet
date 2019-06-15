package util.chain;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.function.Consumer;

public abstract class FetchHandler {

    private FetchHandler next;

    public abstract void handle(JsonObject parameters, Gson deserializer, Consumer<Result> result);

    protected void handleNext(JsonObject parameters, Gson deserializer, Consumer<Result> result) {
        if (next == null) {
            result.accept(Result.success());
            return;
        }

        next.handle(parameters, deserializer, result);
    }

    public void setNext(FetchHandler next) {
        this.next = next;
    }
}
