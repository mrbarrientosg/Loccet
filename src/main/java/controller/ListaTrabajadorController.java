package controller;

import base.Controller;
import cell.TrabajadorCell;
import io.reactivex.Observable;
import io.reactivex.Single;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Proyecto;
import model.Trabajador;
import delegate.SearchEmployeeDelegate;
import view.ListaTrabajadorView;

import java.util.stream.Collectors;

public final class ListaTrabajadorController extends Controller implements SearchEmployeeDelegate {

    private final ListaTrabajadorView view;

    private final Proyecto model;

    public ListaTrabajadorController(ListaTrabajadorView view, Proyecto model) {
        this.view = view;
        this.model = model;
    }


    public ObservableList<TrabajadorCell> loadData() {
        return FXCollections.observableList(model.getTrabajadores().stream().map(TrabajadorCell::new).collect(Collectors.toList()));
    }

    public Single<ObservableList<TrabajadorCell>> searchEmployee(String text) {
        return Observable.fromIterable(model.getTrabajadores())
                .filter(trabajador -> trabajador.getRut().contains(text))
                .map(TrabajadorCell::new)
                .toList()
                .map(FXCollections::observableList);
    }

    @Override
    public void selectedEmployee(Trabajador value) {
        if (model.obtenerTrabajador(value.getRut()) != null) return;
        model.agregarTrabajador(value);
        view.addEmployee(new TrabajadorCell(value));
    }

    public Trabajador obtenerTrabajador(String rut) {
        return model.obtenerTrabajador(rut);
    }
}
