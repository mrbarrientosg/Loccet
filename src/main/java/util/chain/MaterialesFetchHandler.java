package util.chain;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import io.reactivex.schedulers.Schedulers;
import model.Constructora;
import model.Material;
import model.Proyecto;
import network.endpoint.LoccetAPI;

import java.util.function.Consumer;

public final class MaterialesFetchHandler extends FetchHandler {

    @Override
    public void handle(JsonObject parameters, Gson deserializer, Consumer<FetchResult> result) {
        disposable = service.request(LoccetAPI.GET_MATERIALES_PROYECTOS, parameters)
                .map(JsonElement::getAsJsonArray)
                .subscribeOn(Schedulers.computation())
                .observeOn(JavaFxScheduler.platform())
                .subscribe(jsonArray -> {
                    System.out.println("materiales");

                    for (JsonElement jsonElement : jsonArray) {
                        JsonObject json = jsonElement.getAsJsonObject();
                        Material m = deserializer.fromJson(json, Material.class);
                        Proyecto p = Constructora.getInstance().obtenerProyecto(json.get("id_proyecto").getAsString());
                        p.agregarMaterial(m);
                    }

                    handleNext(parameters, deserializer, result);
                }, throwable -> {
                    result.accept(FetchResult.error(throwable));
                });
    }

}
