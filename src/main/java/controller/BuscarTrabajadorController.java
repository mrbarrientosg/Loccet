package controller;

import cell.TrabajadorCell;
import io.reactivex.Observable;
import io.reactivex.Single;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import model.Constructora;
import model.Trabajador;
import delegate.SearchEmployeeDelegate;
import view.BuscarTrabajadorView;

/**
 * Controlador de la vista BuscarTrabajadorView.
 *
 * @see view.BuscarTrabajadorView
 *
 * @author Matias Barrientos
 */
public final class BuscarTrabajadorController {

    private Constructora model;

    private BuscarTrabajadorView view;

    private SearchEmployeeDelegate delegate;

    private ObjectProperty<TrabajadorCell> selectedItem;

    public BuscarTrabajadorController() {
        model = Constructora.getInstance();
        selectedItem = new SimpleObjectProperty<>();
    }

    public Single<ObservableList<TrabajadorCell>> searchEmployee(String text) {
        return Observable.fromIterable(model.getTrabajadores())
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
