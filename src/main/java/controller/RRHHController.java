package controller;

import base.Controller;
import cell.ProyectoCell;
import cell.TrabajadorCell;
import com.google.gson.JsonObject;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Constructora;
import model.Proyecto;
import model.Trabajador;
import network.endpoint.TrabajadorAPI;
import network.service.NetService;
import specification.TrabajadorByQuerySpecification;
import util.AsyncTask;
import view.RRHHView;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class RRHHController extends Controller {

    private RRHHView view;

    private Constructora model = Constructora.getInstance();

    private NetService service = NetService.getInstance();

    public ObservableList<TrabajadorCell> searchEmployee(String text) {
        ObservableList<TrabajadorCell> cells = FXCollections.observableArrayList(e -> new Observable[]{ new SimpleStringProperty(e.getRut())});

        StreamSupport.stream(model.buscarTrabajador(new TrabajadorByQuerySpecification(text)).spliterator(), false)
                .map(TrabajadorCell::new)
                .forEach(cells::add);

        return cells;

    }

    public ObservableList<TrabajadorCell> searchEmployeeProject(String idProject, String text) {
        Proyecto p = model.obtenerProyecto(idProject);

        ObservableList<TrabajadorCell> cells = FXCollections.observableArrayList(e -> new Observable[]{ new SimpleStringProperty(e.getRut())});

        if (p == null)
            return cells;

        StreamSupport.stream(p.buscarTrabajador(new TrabajadorByQuerySpecification(text)).spliterator(), false)
                .map(TrabajadorCell::new)
                .forEach(cells::add);

        return cells;
    }

    public ObservableList<TrabajadorCell> getEmployeesProject(String id) {
        return searchEmployeeProject(id, "");
    }

    public void deleteTrabajador(String rut) {
        Trabajador t = model.eliminarTrabajador(rut);

        if (t == null) return;

        view.didDeleteTrabajador(t.getRut());

        JsonObject json = new JsonObject();

        json.addProperty("rut", rut);

        service.request(TrabajadorAPI.REMOVE, json)
                .subscribe(System.out::println, throwable -> {
                    LOGGER.log(Level.SEVERE, "", throwable);
                });
    }

    public void fetchProyectos(Consumer<ObservableList<ProyectoCell>> callback) {
        AsyncTask.supplyAsync(() -> {
            ObservableList<ProyectoCell> cells = FXCollections.observableArrayList();

            model.getProyectos().forEach(proyecto -> cells.add(new ProyectoCell(proyecto)));

            return cells;
        }).thenAccept(callback);
    }

    public void setView(RRHHView view) {
        this.view = view;
    }
}
