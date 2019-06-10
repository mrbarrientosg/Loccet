package controller;

import base.Controller;
import cell.ProyectoCell;
import cell.TrabajadorCell;
import com.google.gson.JsonObject;
import io.reactivex.Observable;
import io.reactivex.Single;
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
import java.util.logging.Level;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class RRHHController extends Controller {

    private RRHHView view;

    private Constructora model = Constructora.getInstance();

    private Router<TrabajadorAPI> service = new Router<>();

    public FilteredList<TrabajadorCell> fetchTrabajadores() {
        return new FilteredList<>(FXCollections.observableList(model.getConjuntoTrabajadores().stream().map(TrabajadorCell::new).collect(Collectors.toList())));
    }

    public FilteredList<TrabajadorCell> fetchTrabajadores(String idProyecto) {
        Proyecto p = model.obtenerProyecto(idProyecto);

        if (p == null) return new FilteredList<>(FXCollections.emptyObservableList());

        return new FilteredList<>(FXCollections.observableList(p.getTrabajadores().stream().map(TrabajadorCell::new).collect(Collectors.toList())));
    }

    public FilteredList<TrabajadorCell> searchEmployee(String text) {
        List<TrabajadorCell> list = StreamSupport.stream(model.buscarTrabajador(new TrabajadorByQuerySpecification(text)).spliterator(), false)
                .map(TrabajadorCell::new)
                .collect(Collectors.toList());

        return new FilteredList<>(FXCollections.observableList(list));

}

    public FilteredList<TrabajadorCell> searchEmployeeProject(String idProject, String text) {
        Proyecto p = model.obtenerProyecto(idProject);

        if (p == null) return new FilteredList<>(FXCollections.emptyObservableList());

        List<TrabajadorCell> list = StreamSupport.stream(p.buscarTrabajador(new TrabajadorByQuerySpecification(text)).spliterator(), false)
                .map(TrabajadorCell::new)
                .collect(Collectors.toList());

        return new FilteredList<>(FXCollections.observableList(list));

    }

    public FilteredList<TrabajadorCell> getEmployeesProject(String id) {
        return searchEmployeeProject(id, "");
                //FXCollections.observableList(p.getTrabajadores().stream().map(TrabajadorCell::new).collect(Collectors.toList()));
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
