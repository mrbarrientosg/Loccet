package controller;

import base.Controller;
import cell.MaterialCell;
import cell.TrabajadorCell;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import model.Constructora;
import model.Proyecto;
import model.Trabajador;
import router.ListaTrabajadorRouter;
import router.TrabajadorRouter;
import state.EditTrabajadorDelegate;
import view.ListaTrabajadorView;
import view.TrabajadorView;

import java.util.ListIterator;
import java.util.stream.Collectors;

public final class ListaTrabajadorController extends Controller {

    private final ListaTrabajadorView view;

    private final Proyecto model;

    public ListaTrabajadorController(ListaTrabajadorView view, Proyecto model) {
        this.view = view;
        this.model = model;
    }

    /**
     * Carga la informacion del modelo en la lista
     */
    public ObservableList<TrabajadorCell> loadData() {
        return FXCollections.observableList(model.getTrabajadores().stream().map(TrabajadorCell::new).collect(Collectors.toList()));
    }

}
