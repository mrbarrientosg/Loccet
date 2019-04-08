package cl.loccet.controller;

import cl.loccet.base.Controller;
import cl.loccet.cell.TrabajadorCell;
import cl.loccet.model.Constructora;
import cl.loccet.model.Trabajador;
import cl.loccet.router.ListaTrabajadorRouter;
import cl.loccet.router.TrabajadorRouter;
import cl.loccet.state.EditTrabajadorDelegate;
import cl.loccet.view.ListaTrabajadorView;
import cl.loccet.view.TrabajadorView;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

    private void loadData() {
        trabajadorCells = FXCollections.observableList(model.getConjuntoTrabajadores().stream().map(TrabajadorCell::new).collect(Collectors.toList()));
    }

    public TrabajadorView mostrarEditar() {
        if (selectedTrabajadorProperty().get() == null) return null;
        Trabajador t = model.buscarTrabajador(selectedTrabajador.get().getRut()).get(0);
        TrabajadorView trabajadorView = TrabajadorRouter.create(model, t);
        trabajadorView.getController().setDelegate(this);
        return trabajadorView;
    }

    @Override
    public void didEdit(Trabajador old, Trabajador newT) {
        System.out.println(old);
        ListIterator<TrabajadorCell> iterator = trabajadorCells.listIterator();

        while (iterator.hasNext()) {
            if (iterator.next().getRut() == old.getRut()) {
                iterator.set(new TrabajadorCell(newT));
                break;
            }
        }

        view.refresh();
    }

    public ObjectProperty<TrabajadorCell> selectedTrabajadorProperty() {
        return selectedTrabajador;
    }

    public ObservableList<TrabajadorCell> getTrabajadorCells() {
        return trabajadorCells;
    }
}
