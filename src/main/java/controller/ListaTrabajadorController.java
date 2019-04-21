package controller;

import base.Controller;
import cell.TrabajadorCell;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Constructora;
import model.Trabajador;
import router.ListaTrabajadorRouter;
import router.TrabajadorRouter;
import state.EditTrabajadorDelegate;
import view.ListaTrabajadorView;
import view.TrabajadorView;

import java.util.ListIterator;
import java.util.stream.Collectors;

public class ListaTrabajadorController extends Controller implements EditTrabajadorDelegate {

    private final ListaTrabajadorView view;

    private final Constructora model;

    private final ListaTrabajadorRouter router;

    private ObjectProperty<TrabajadorCell> selectedTrabajador = new SimpleObjectProperty<>();

    private ObservableList<TrabajadorCell> trabajadorCells;

    public ListaTrabajadorController(ListaTrabajadorView view, Constructora model, ListaTrabajadorRouter router) {
        this.view = view;
        this.model = model;
        this.router = router;
        loadData();
    }

    public void loadData() {
        trabajadorCells = FXCollections.observableList(model.getConjuntoTrabajadores().stream().map(TrabajadorCell::new).collect(Collectors.toList()));
    }

    public TrabajadorView mostrarEditar() {
        if (selectedTrabajadorProperty().get() == null) return null;
        Trabajador t = model.obtenerTrabajador(selectedTrabajador.get().getRut());
        return TrabajadorRouter.create(model, t, this);
    }

    @Override
    public void didEdit(Trabajador old, Trabajador newT) {
        ListIterator<TrabajadorCell> iterator = trabajadorCells.listIterator();

        while (iterator.hasNext()) {
            if (iterator.next().getRut().equals(old.getRut())) {
                iterator.set(new TrabajadorCell(newT));
                break;
            }
        }
    }

    public ObjectProperty<TrabajadorCell> selectedTrabajadorProperty() {
        return selectedTrabajador;
    }

    public ObservableList<TrabajadorCell> getTrabajadorCells() {
        return trabajadorCells;
    }
}
