package controller;

import base.Controller;
import com.google.gson.*;
import exceptions.EmptyFieldsException;
import exceptions.InvalidUserException;
import io.reactivex.Maybe;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import io.reactivex.schedulers.Schedulers;
import javafx.util.Pair;
import json.InstantTypeConverter;
import json.LocalTimeTypeConverter;
import model.*;
import network.LoccetAPI;
import network.LoccetService;
import view.LoginView;
import java.time.Instant;
import java.time.LocalTime;
import java.util.Objects;

public final class LoginController extends Controller {

    private LoginView view;

    private LoccetService service = LoccetService.getInstace();

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void setView(LoginView view) {
        this.view = view;
    }

    /**
     * Inicia sesion
     */
    public void loginUser() throws EmptyFieldsException {

        /*if (view.getUsername().isEmpty() || view.getPassword().isEmpty() || view.getDNS().isEmpty()) {
            throw new EmptyFieldsException();
        }*/


        view.showLoading();

        JsonObject parameters = new JsonObject();
        parameters.addProperty("username", "mb");
        parameters.addProperty("password", "123");
        parameters.addProperty("dns", "test.loccet.cl");

        Disposable disposable = service.call(LoccetAPI.LOGIN, parameters)
                .subscribeOn(Schedulers.computation())
                .filter(Objects::nonNull)
                .map(JsonElement::getAsJsonObject)
                .observeOn(JavaFxScheduler.platform())
                .subscribe(login -> {
                    if (login.get("login").getAsBoolean())
                        view.didLogin();
                    else
                        view.onError(new InvalidUserException());
                }, throwable -> {
                    view.hideLoading();
                    view.onError(throwable);
                });

        compositeDisposable.add(disposable);
    }

    public void loadData() {
        JsonObject parameters = new JsonObject();
        parameters.addProperty("dns", "test.loccet.cl");

        Constructora.getInstance();

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(Instant.class, new InstantTypeConverter())
                .registerTypeAdapter(Trabajador.class, new Trabajador.TrabajadorDeserializer())
                .registerTypeAdapter(LocalTime.class, new LocalTimeTypeConverter())
                .registerTypeAdapter(Proyecto.class, new Proyecto.ProyetoDeserializer())
                .create();

        Disposable disposable = service.call(LoccetAPI.GET_CONSTRUCTORA, parameters)
                .subscribeOn(Schedulers.computation())
                .filter(Objects::nonNull)
                .map(JsonElement::getAsJsonObject)
                .flatMap(jsonObject -> {
                    Constructora.getInstance().init(jsonObject);
                    LOGGER.info("Constructora lista");
                    return service.call(LoccetAPI.GET_PROYECTOS, parameters).map(JsonElement::getAsJsonArray);
                })
                .flatMap(jsonArray -> {
                    for (JsonElement jsonElement : jsonArray) {
                        Proyecto p = gson.fromJson(jsonElement, Proyecto.class);
                        Constructora.getInstance().agregarProyecto(p);
                    }
                    LOGGER.info("proyectos listos");
                    return Maybe.zip(service.call(LoccetAPI.GET_TRABAJADORES_PROYECTOS, parameters).map(JsonElement::getAsJsonArray),
                            service.call(LoccetAPI.GET_TRABAJADORES_CONSTRUCTORA, parameters).map(JsonElement::getAsJsonArray), Pair::new);
                })
                .flatMap(pair -> {
                    for (JsonElement jsonElement : pair.getKey()) {
                        JsonObject json = jsonElement.getAsJsonObject();
                        Trabajador t = gson.fromJson(json, Trabajador.class);
                        Constructora.getInstance().agregarTrabajador(json.get("id_proyecto").getAsString(), t);
                    }

                    for (JsonElement jsonElement : pair.getValue()) {
                        JsonObject json = jsonElement.getAsJsonObject();
                        Trabajador t = gson.fromJson(json, Trabajador.class);
                        Constructora.getInstance().agregarTrabajador(t);
                    }
                    LOGGER.info("trabajadores listos");
                    return Maybe.zip(service.call(LoccetAPI.GET_HORARIOS_TRABAJADORES, parameters).map(JsonElement::getAsJsonArray),
                            service.call(LoccetAPI.GET_ASISTENCIAS_TRABAJADORES, parameters).map(JsonElement::getAsJsonArray), Pair::new);
                })
                .flatMap(pair -> {
                    for (JsonElement jsonElement : pair.getKey()) {
                        JsonObject json = jsonElement.getAsJsonObject();
                        Horario h = gson.fromJson(json, Horario.class);
                        Trabajador t = Constructora.getInstance().obtenerTrabajador(json.get("rut_trabajador").getAsString());
                        t.agregarHorario(json.get("id_proyecto").getAsString(), h);
                    }

                    for (JsonElement jsonElement : pair.getValue()) {
                        JsonObject json = jsonElement.getAsJsonObject();
                        Asistencia a = gson.fromJson(json, Asistencia.class);
                        Proyecto p = Constructora.getInstance().buscarProyecto(json.get("id_proyecto").getAsString());
                        p.agregarAsistencia(json.get("rut_trabajador").getAsString(), a);
                    }
                    LOGGER.info("horarios y asistencias listas");
                    return Maybe.zip(service.call(LoccetAPI.GET_FASES_PROYECTOS, parameters).map(JsonElement::getAsJsonArray),
                            service.call(LoccetAPI.GET_TAREAS_FASES, parameters).map(JsonElement::getAsJsonArray), Pair::new);
                })
                .flatMap(pair -> {
                    for (JsonElement jsonElement : pair.getKey()) {
                        JsonObject json = jsonElement.getAsJsonObject();
                        Fase f = gson.fromJson(json, Fase.class);
                        Proyecto p = Constructora.getInstance().buscarProyecto(json.get("id_proyecto").getAsString());
                        p.agregarFase(f);
                    }

                    for (JsonElement jsonElement : pair.getValue()) {
                        JsonObject json = jsonElement.getAsJsonObject();
                        Tarea t = gson.fromJson(json, Tarea.class);
                        Proyecto p = Constructora.getInstance().buscarProyecto(json.get("id_proyecto").getAsString());
                        p.agregarTarea(json.get("id_fase").getAsInt(), t);
                    }
                    LOGGER.info("Fases y tares listas");
                    return Maybe.zip(service.call(LoccetAPI.GET_MATERIALES_PROYECTOS, parameters).map(JsonElement::getAsJsonArray),
                            service.call(LoccetAPI.GET_REGISTRO_MATERIALES, parameters).map(JsonElement::getAsJsonArray), Pair::new);
                })
                .flatMap(pair -> {
                    for (JsonElement jsonElement : pair.getKey()) {
                        JsonObject json = jsonElement.getAsJsonObject();
                        Material m = gson.fromJson(json, Material.class);
                        Proyecto p = Constructora.getInstance().buscarProyecto(json.get("id_proyecto").getAsString());
                        p.agregarMaterial(m);
                    }

                    for (JsonElement jsonElement : pair.getValue()) {
                        JsonObject json = jsonElement.getAsJsonObject();
                        RegistroMaterial rm = gson.fromJson(json, RegistroMaterial.class);
                        Proyecto p = Constructora.getInstance().buscarProyecto(json.get("id_proyecto").getAsString());
                        p.agregarRegistroMaterial(json.get("id_material").getAsString(), rm);
                    }

                    LOGGER.info("Mareteriales listos");

                    return Maybe.just(true);
                })
                .observeOn(JavaFxScheduler.platform())
                .subscribe(empty -> {
                    view.hideLoading();
                    view.gotoHome();
                }, throwable -> {
                    view.hideLoading();
                    view.onError(throwable);
                });


        compositeDisposable.add(disposable);
    }

    public void clear() {
        compositeDisposable.dispose();
    }

}
