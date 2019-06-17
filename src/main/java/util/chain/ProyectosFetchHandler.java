package util.chain;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import io.reactivex.schedulers.Schedulers;
import model.Constructora;
import model.Proyecto;
import network.endpoint.LoccetAPI;
import network.service.Router;

import java.util.function.Consumer;

public class ProyectosFetchHandler extends FetchHandler {

    @Override
    public void handle(JsonObject parameters, Gson deserializer, Consumer<FetchResult> result) {
        disposable = service.request(LoccetAPI.GET_PROYECTOS, parameters)
                .map(JsonElement::getAsJsonArray)
                .subscribeOn(Schedulers.computation())
                .observeOn(JavaFxScheduler.platform())
                .subscribe(jsonArray -> {
                    System.out.println(jsonArray);

                    for (JsonElement jsonElement : jsonArray) {
                        Proyecto p = deserializer.fromJson(jsonElement, Proyecto.class);
                        Constructora.getInstance().agregarProyecto(p);
                    }

                    handleNext(parameters, deserializer, result);
                }, throwable -> {
                    result.accept(FetchResult.error(throwable));
                });
    }

}
