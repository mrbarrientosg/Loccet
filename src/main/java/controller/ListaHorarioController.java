package controller;

import base.Controller;
import cell.HorarioCell;
import cell.ProyectoCell;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Constructora;
import model.Horario;
import model.Trabajador;
import network.endpoint.HorarioAPI;
import network.service.NetService;
import util.AsyncTask;
import view.ListaHorarioView;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.logging.Level;

public final class ListaHorarioController extends Controller {

    private ListaHorarioView view;

    private Constructora model;

    private String rutTrabajador;

    public ListaHorarioController() {
        model = Constructora.getInstance();
    }

    /**
     * Carga la informacion desde el modelo
     */
    public void fetchHorarios(Consumer<ObservableList<HorarioCell>> callBack) {
        AsyncTask.supplyAsync(() -> {
            ObservableList<HorarioCell> list = FXCollections.observableArrayList();

            model.getHorarios(rutTrabajador).forEach(horario -> list.add(new HorarioCell(horario)));

            return list;
        }).thenAccept(callBack);
    }

    /**
     * Elimina un horario de un trabajador
     * @param cell Data de la tabla
     */
    public void eliminarHorario(HorarioCell cell) {
        if (cell == null) return;

        model.eliminarHorario(rutTrabajador, cell.getId());

        view.didDeleteHorario(cell);

        NetService service = NetService.getInstance();

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

    public void setRutTrabajador(String rutTrabajador) {
        this.rutTrabajador = rutTrabajador;
    }

    public String getRutTrabajador() {
        return rutTrabajador;
    }
}
