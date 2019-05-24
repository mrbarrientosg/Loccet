package controller;

import base.Controller;
import cell.TrabajadorCell;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import io.reactivex.schedulers.Schedulers;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import model.Constructora;
import model.Trabajador;
import util.SearchEmployeeDelegate;
import view.BuscarTrabajadorView;

import java.util.List;
import java.util.stream.Collectors;

public class BuscarTrabajadorController extends Controller {

    private Constructora model;

    private BuscarTrabajadorView view;

    private SearchEmployeeDelegate delegate;

    private ObjectProperty<TrabajadorCell> selectedItem;

    public BuscarTrabajadorController() {
        model = Constructora.getInstance();
        selectedItem = new SimpleObjectProperty<>();
    }
    public ObservableList<TrabajadorCell> loadData() {
        return FXCollections.observableList(model.getConjuntoTrabajadores().stream().map(TrabajadorCell::new).collect(Collectors.toList()));
    }

    public Single<ObservableList<TrabajadorCell>> searchEmployee(String text) {
        return Observable.fromIterable(model.getConjuntoTrabajadores())
                .filter(trabajador -> trabajador.getRut().contains(text))
                .map(TrabajadorCell::new)
                .toList()
                .map(FXCollections::observableList);
    }

    public void doneAction(ActionEvent event) {
        if (selectedItem.isNull().get()) return;

        Trabajador trabajador = model.obtenerTrabajador(selectedItem.get().getRut());

        if (delegate != null) {
            delegate.selectedEmployee(trabajador);
        }

        view.close();
    }

    public void setDelegate(SearchEmployeeDelegate delegate) {
        this.delegate = delegate;
    }

    public void setView(BuscarTrabajadorView view) {
        this.view = view;
    }

    public ObjectProperty<TrabajadorCell> selectedItemProperty() {
        return selectedItem;
    }

}
