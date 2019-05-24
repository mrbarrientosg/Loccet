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
import view.RRHHView;

import java.util.List;
import java.util.stream.Collectors;

public class RRHHController extends Controller {

    private RRHHView view;

    private Constructora model = Constructora.getInstance();

    public Single<ObservableList<TrabajadorCell>> searchEmployee(String text) {
        return Observable.fromIterable(model.getConjuntoTrabajadores())
                .filter(trabajador -> trabajador.getRut().contains(text))
                .map(TrabajadorCell::new)
                .toList()
                .map(FXCollections::observableList);
    }

    public Single<ObservableList<TrabajadorCell>> searchEmployeeProject(String idProject, String text) {
        Proyecto p = null;//model.buscarProyecto(idProject);

        if (p == null) return Single.just(FXCollections.emptyObservableList());

        return Observable.fromIterable(p.getTrabajadores())
                .filter(trabajador -> trabajador.getRut().contains(text))
                .map(TrabajadorCell::new)
                .toList()
                .map(FXCollections::observableList);
    }

    public ObservableList<TrabajadorCell> getEmployeesProject(String id) {
        Proyecto p = null;//model.buscarProyecto(id);

        if (p == null) return FXCollections.emptyObservableList();

        return FXCollections.observableList(p.getTrabajadores().stream().map(TrabajadorCell::new).collect(Collectors.toList()));
    }

    public List<ProyectoCell> getProyectos() {
        return model.getListaProyecto().stream().map(ProyectoCell::new).collect(Collectors.toList());
    }

    public void setView(RRHHView view) {
        this.view = view;
    }
}
