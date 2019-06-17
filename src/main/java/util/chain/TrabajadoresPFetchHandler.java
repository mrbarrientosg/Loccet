package util.chain;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import io.reactivex.schedulers.Schedulers;
import model.Constructora;
import model.Trabajador;
import network.endpoint.LoccetAPI;
import network.service.Router;
import java.util.function.Consumer;

public class TrabajadoresPFetchHandler extends FetchHandler {

    @Override
    public void handle(JsonObject parameters, Gson deserializer, Consumer<FetchResult> result) {
        disposable = service.request(LoccetAPI.GET_TRABAJADORES_PROYECTOS, parameters)
                .map(JsonElement::getAsJsonArray)
                .subscribeOn(Schedulers.computation())
                .observeOn(JavaFxScheduler.platform())
                .subscribe(jsonArray -> {
                    System.out.println("trabajadore proyecto");

                    for (JsonElement jsonElement: jsonArray) {
                        JsonObject json = jsonElement.getAsJsonObject();
                        Trabajador t = deserializer.fromJson(json, Trabajador.class);
                        Constructora.getInstance().agregarTrabajador(json.get("id_proyecto").getAsString(), t);
                    }
                    handleNext(parameters, deserializer, result);
                }, throwable -> {
                    result.accept(FetchResult.error(throwable));
                });
    }

}
