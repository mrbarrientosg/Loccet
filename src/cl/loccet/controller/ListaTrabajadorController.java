package cl.loccet.controller;

import cl.loccet.base.Controller;
import cl.loccet.cell.TrabajadorCell;
import cl.loccet.model.Constructora;
import cl.loccet.model.Trabajador;
import cl.loccet.router.ListaTrabajadorRouter;
import cl.loccet.router.TrabajadorRouter;
import cl.loccet.view.ListaTrabajadorView;
import cl.loccet.view.TrabajadorView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.stream.Collectors;

public class ListaTrabajadorController extends Controller {

    private final ListaTrabajadorView view;

    private final Constructora model;

    private final ListaTrabajadorRouter router;

    public ListaTrabajadorController(ListaTrabajadorView view, Constructora model, ListaTrabajadorRouter router) {
        this.view = view;
        this.model = model;
        this.router = router;
    }

    public ObservableList<TrabajadorCell> trabajadorCells() {
        return FXCollections.observableList(model.getConjuntoTrabajadores().stream().map(TrabajadorCell::new).collect(Collectors.toList()));
    }

    public TrabajadorView mostrarEditar(TrabajadorCell trabajador) {
        Trabajador t = model.buscarTrabajador(trabajador.getNombre()).get(0);
        TrabajadorView trabajadorView = TrabajadorRouter.create(model, t);
        return trabajadorView;
    }
}
