package controller;

import base.Controller;
import cell.HorarioCell;
import cell.ProyectoCell;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Horario;
import model.Trabajador;
import network.endpoint.HorarioAPI;
import network.service.NetService;
import view.ListaHorarioView;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.logging.Level;

public final class ListaHorarioController extends Controller {

    private ListaHorarioView view;

    private Trabajador model;

    /**
     * Carga la informacion desde el modelo
     */
    public void fetchHorarios(Consumer<ObservableList<HorarioCell>> callBack) {
        CompletableFuture.supplyAsync(() -> {
            ObservableList<HorarioCell> list = FXCollections.observableArrayList();

            model.obtenerListaHorario().forEach(horario -> list.add(new HorarioCell(horario)));

            return list;
        }).thenAccept(callBack);
    }

    /**
     * Elimina un horario de un trabajador
     * @param cell Data de la tabla
     */
    public void eliminarHorario(HorarioCell cell) {
        if (cell == null) return;

        model.eliminarHorario(cell.getId());

        view.didDeleteHorario(cell);

        NetService<HorarioAPI> service = NetService.getInstance();

        JsonObject json = new JsonObject();
        json.addProperty("id_horario", cell.getId());

        service.request(HorarioAPI.REMOVE, json)
                .subscribe(System.out::println, throwable -> {
                    LOGGER.log(Level.SEVERE, "", throwable);
                });
    }

    public void setView(ListaHorarioView view) {
        this.view = view;
    }

    public void setModel(Trabajador model) {
        this.model = model;
    }

    public Trabajador getModel() {
        return model;
    }
}
