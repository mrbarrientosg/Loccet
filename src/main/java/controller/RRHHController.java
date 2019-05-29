package controller;

import base.Controller;
import cell.ProyectoCell;
import cell.TrabajadorCell;
import com.google.gson.JsonObject;
import io.reactivex.Observable;
import io.reactivex.Single;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Constructora;
import model.Proyecto;
import model.Trabajador;
import network.LoccetService;
import network.API.TrabajadorAPI;
import specification.TrabajadorByQuerySpecification;
import view.RRHHView;

import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

public class RRHHController extends Controller {

    private RRHHView view;

    private Constructora model = Constructora.getInstance();

    public ObservableList<TrabajadorCell> fetchTrabajadores() {
        return FXCollections.observableList(model.getConjuntoTrabajadores().stream().map(TrabajadorCell::new).collect(Collectors.toList()));
    }

    public ObservableList<TrabajadorCell> fetchTrabajadores(String idProyecto) {
        Proyecto p = model.obtenerProyecto(idProyecto);

        if (p == null) return FXCollections.emptyObservableList();

        return FXCollections.observableList(p.getTrabajadores().stream().map(TrabajadorCell::new).collect(Collectors.toList()));
    }

    public Single<ObservableList<TrabajadorCell>> searchEmployee(String text) {
        return Observable.fromIterable(model.buscarTrabajador(new TrabajadorByQuerySpecification(text)))
                .map(TrabajadorCell::new)
                .toList()
                .map(FXCollections::observableList);
    }

    public Single<ObservableList<TrabajadorCell>> searchEmployeeProject(String idProject, String text) {
        Proyecto p = model.obtenerProyecto(idProject);

        if (p == null) return Single.just(FXCollections.emptyObservableList());

        return Observable.fromIterable(p.buscarTrabajador(new TrabajadorByQuerySpecification(text)))
                .map(TrabajadorCell::new)
                .toList()
                .map(FXCollections::observableList);
    }

    public Single<ObservableList<TrabajadorCell>> getEmployeesProject(String id) {
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

        LoccetService.getInstance().call(TrabajadorAPI.REMOVE, json)
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
