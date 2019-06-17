package util.chain;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import io.reactivex.schedulers.Schedulers;
import model.Constructora;
import network.endpoint.LoccetAPI;
import network.service.Router;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class ConstructoraFetchHandler extends FetchHandler {

    @Override
    public void handle(JsonObject parameters, Gson deserializer, Consumer<FetchResult> result) {

        disposable = service.request(LoccetAPI.GET_CONSTRUCTORA, parameters)
                .map(JsonElement::getAsJsonObject)
                .subscribeOn(Schedulers.computation())
                .observeOn(JavaFxScheduler.platform())
                .subscribe(jsonObject -> {
                    System.out.println(jsonObject);
                    Constructora.getInstance().init(jsonObject);
                    handleNext(parameters, deserializer, result);
                }, throwable -> {
                    result.accept(FetchResult.error(throwable));
                });
    }

}
