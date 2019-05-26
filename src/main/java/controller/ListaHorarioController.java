package controller;

import base.Controller;
import cell.HorarioCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Horario;
import model.Trabajador;
import state.AddHorarioDelegate;
import view.ListaHorarioView;

import java.util.List;
import java.util.stream.Collectors;

public final class ListaHorarioController extends Controller {

    private ListaHorarioView view;

    private Trabajador trabajador;

    public ListaHorarioController(ListaHorarioView view, Trabajador trabajador) {
        this.view = view;
        this.trabajador = trabajador;
    }

    /**
     * Carga la informacion desde el modelo
     */
    public ObservableList<HorarioCell> fetchHorarios() {
        ObservableList<HorarioCell> list = FXCollections.observableArrayList();

        trabajador.obtenerListaHorario().forEach(horario -> list.add(new HorarioCell(horario)));

        return list;
    }

    /**
     * Elimina un horario de un trabajador
     * @param cell Data de la tabla
     */
    public void eliminarHorario(HorarioCell cell) {
        if (cell == null) return;
        trabajador.eliminarHorario(cell.getId());
        view.didDeleteHorario(cell);
    }

}
