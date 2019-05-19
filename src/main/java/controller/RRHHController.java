package controller;

import base.Controller;
import cell.TrabajadorCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import model.Constructora;
import model.Trabajador;
import view.RRHHView;

import java.util.stream.Collectors;

public class RRHHController extends Controller {

    private RRHHView view;

    private Constructora model = Constructora.getInstance();

    public SortedList<TrabajadorCell> obtenerTrabajadores() {
        ObservableList<TrabajadorCell> observableList = FXCollections
                .observableList(model.getConjuntoTrabajadores()
                        .stream()
                        .map(TrabajadorCell::new)
                        .collect(Collectors.toList()));
        return new SortedList<>(observableList);
    }

    public void setView(RRHHView view) {
        this.view = view;
    }
}
