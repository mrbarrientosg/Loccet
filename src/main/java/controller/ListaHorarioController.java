package controller;

import base.Controller;
import cell.HorarioCell;
import cell.ProyectoCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Trabajador;
import view.ListaHorarioView;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public final class ListaHorarioController extends Controller {

    private ListaHorarioView view;

    private Trabajador model;

    /**
     * Carga la informacion desde el modelo
     */
    public void fetchHorarios(Consumer<ObservableList<HorarioCell>> callBack) {
        CompletableFuture.supplyAsync(() -> {
            ObservableList<HorarioCell> list = FXCollections.observableArrayList();

            model.obtenerListaHorario().forEach(horario -> list.add(new HorarioCell(horario)));

            return list;
        }).thenAccept(callBack);
    }

    /**
     * Elimina un horario de un trabajador
     * @param cell Data de la tabla
     */
    public void eliminarHorario(HorarioCell cell) {
        if (cell == null) return;
        model.eliminarHorario(cell.getId());
        view.didDeleteHorario(cell);
    }

    public void setView(ListaHorarioView view) {
        this.view = view;
    }

    public void setModel(Trabajador model) {
        this.model = model;
    }

    public Trabajador getModel() {
        return model;
    }
}
