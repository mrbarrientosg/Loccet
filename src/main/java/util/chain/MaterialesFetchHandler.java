package util.chain;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import io.reactivex.schedulers.Schedulers;
import model.Constructora;
import model.Material;
import model.Proyecto;
import network.endpoint.LoccetAPI;
import network.service.Router;

import java.util.function.Consumer;

public class MaterialesFetchHandler extends FetchHandler {

    private Router<LoccetAPI> service = new Router<>();

    private Disposable disposable;

    @Override
    public void handle(JsonObject parameters, Gson deserializer, Consumer<Result> result) {
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
                    result.accept(Result.error(throwable));
                });
    }

    public void dispose() {
        if (disposable != null)
            disposable.dispose();
    }
}
