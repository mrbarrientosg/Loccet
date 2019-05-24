package controller;

import base.Controller;
import cell.ProyectoCell;
import cell.TrabajadorCell;
import io.reactivex.Observable;
import io.reactivex.Single;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import model.Constructora;
import model.Proyecto;
import model.Trabajador;
import specification.TrabajadorByQuerySpecification;
import view.RRHHView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RRHHController extends Controller {

    private RRHHView view;

    private Constructora model = Constructora.getInstance();

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

    public void deleteTrabajador(String rut) {
        Trabajador t = model.eliminarTrabajador(rut);
        if (t == null) return;
        view.didDeleteTrabajador(t.getRut());
    }

    public List<ProyectoCell> getProyectos() {
        return model.getListaProyecto().stream().map(ProyectoCell::new).collect(Collectors.toList());
    }

    public void setView(RRHHView view) {
        this.view = view;
    }
}
