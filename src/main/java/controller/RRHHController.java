package controller;

import base.Controller;
import cell.ProyectoCell;
import cell.TrabajadorCell;
import com.google.gson.JsonObject;
import io.reactivex.Single;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import model.Constructora;
import model.Proyecto;
import model.Trabajador;
import network.endpoint.TrabajadorAPI;
import network.service.Router;
import specification.TrabajadorByQuerySpecification;
import view.RRHHView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class RRHHController extends Controller {

    private RRHHView view;

    private Constructora model = Constructora.getInstance();

    private Router<TrabajadorAPI> service = Router.getInstance();

    public void fetchTrabajadores(Consumer<ObservableList<TrabajadorCell>> callBack) {
        CompletableFuture.supplyAsync(() -> {
            ObservableList<TrabajadorCell> cells = FXCollections.observableArrayList(e -> new Observable[]{ new SimpleStringProperty(e.getRut())});

            model.getTrabajadores().forEach(trabajador -> cells.add(new TrabajadorCell(trabajador)));

            return cells;
        }).thenAccept(callBack);
    }

    public void fetchTrabajadores(String idProyecto, Consumer<ObservableList<TrabajadorCell>> callBack) {
        CompletableFuture.supplyAsync(() -> {
            Proyecto p = model.obtenerProyecto(idProyecto);

            ObservableList<TrabajadorCell> cells = FXCollections.observableArrayList(e -> new Observable[]{ new SimpleStringProperty(e.getRut())});

            if (p == null)
                return cells;

            model.getTrabajadores().forEach(trabajador -> cells.add(new TrabajadorCell(trabajador)));

            return cells;
        }).thenAccept(callBack);
    }

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

    public Trabajador obtenerTrabajador(String rut) {
        return model.obtenerTrabajador(rut);
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

    public List<ProyectoCell> getProyectos() {
        return model.getListaProyecto().stream().map(ProyectoCell::new).collect(Collectors.toList());
    }

    public void setView(RRHHView view) {
        this.view = view;
    }
}
