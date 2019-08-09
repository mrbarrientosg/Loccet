package util.chain;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import io.reactivex.schedulers.Schedulers;
import model.*;
import network.endpoint.LoccetAPI;
import java.util.function.Consumer;

public final class AsistenciasFetchHandler extends FetchHandler {

    @Override
    public void handle(JsonObject parameters, Gson deserializer, Consumer<FetchResult> result) {
        disposable = service.request(LoccetAPI.GET_ASISTENCIAS_TRABAJADORES, parameters)
                .map(JsonElement::getAsJsonArray)
                .subscribeOn(Schedulers.computation())
                .observeOn(JavaFxScheduler.platform())
                .subscribe(jsonArray -> {
                    System.out.println("asistencia");

                    for (JsonElement jsonElement : jsonArray) {
                        JsonObject json = jsonElement.getAsJsonObject();
                        Asistencia a = deserializer.fromJson(json, Asistencia.class);
                        Proyecto p = Constructora.getInstance().obtenerProyecto(json.get("id_proyecto").getAsString());
                        p.agregarAsistencia(json.get("rut_trabajador").getAsString(), a);
                    }

                    handleNext(parameters, deserializer, result);
                }, throwable -> {
                    result.accept(FetchResult.error(throwable));
                });
    }

}
