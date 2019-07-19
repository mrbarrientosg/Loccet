package controller;

import base.Controller;
import cell.ProyectoCell;
import com.google.gson.JsonObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Constructora;
import model.Proyecto;
import network.endpoint.ProyectoAPI;
import network.service.NetService;
import util.AsyncTask;
import view.ProyectoView;

import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.stream.Collectors;

public class ProyectoController extends Controller {

    private Constructora model = Constructora.getInstance();

    private ProyectoView view;

    public void setView(ProyectoView view) {
        this.view = view;
    }

    public void fetchProyectos(Consumer<ObservableList<ProyectoCell>> callback) {
        AsyncTask.supplyAsync(() -> {
            ObservableList<ProyectoCell> cells = FXCollections.observableArrayList();

            model.getProyectos().forEach(proyecto -> cells.add(new ProyectoCell(proyecto)));

            return cells;
        }).thenAccept(callback);
    }

    public Proyecto buscarProyecto(String id) {
        return model.obtenerProyecto(id);
    }

    public void deleteProyect(String id){
        model.eliminarProyecto(id);

        NetService<ProyectoAPI> service = NetService.getInstance();

        JsonObject json = new JsonObject();

        json.addProperty("id_proyecto", id);

        service.request(ProyectoAPI.REMOVE, json)
                .subscribe(System.out::println, throwable -> {
                    LOGGER.log(Level.SEVERE, "", throwable);
                });
    }

}

