package util.chain;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.reactivex.disposables.Disposable;
import network.endpoint.LoccetAPI;
import network.service.Router;

import java.io.IOException;
import java.util.function.Consumer;

public abstract class FetchHandler {

    private FetchHandler next;

    protected Router<LoccetAPI> service = Router.getInstance();

    protected Disposable disposable;

    public abstract void handle(JsonObject parameters, Gson deserializer, Consumer<FetchResult> result);

    protected void handleNext(JsonObject parameters, Gson deserializer, Consumer<FetchResult> result) {
        if (next == null) {
            result.accept(FetchResult.success());
            return;
        }

        next.handle(parameters, deserializer, result);
    }

    public void setNext(FetchHandler next) {
        this.next = next;
    }

    public FetchHandler getNext() {
        return next;
    }

    public void dispose() {
        if (disposable != null)
            disposable.dispose();

        try {
            service.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
