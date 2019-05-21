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

public final class ListaHorarioController extends Controller implements AddHorarioDelegate {

    private ListaHorarioView view;

    private Trabajador trabajador;

    private ObservableList<HorarioCell> horarioList;

    private boolean add;

    public ListaHorarioController(ListaHorarioView view, Trabajador trabajador) {
        this.view = view;
        this.trabajador = trabajador;
    }

    /**
     * Carga la informacion desde el modelo
     */
    private void loadData() {
        if (horarioList != null) return;

        List<Horario> horarios = trabajador.obtenerListaHorario();

        horarioList = FXCollections.observableList(horarios.stream().map(HorarioCell::new).collect(Collectors.toList()));
    }

    /**
     * Si la lista esta asociada a la vista de agregar Horario
     * @param horario Horario agregado desde la otra vista
     */
    @Override
    public void didAddHorario(Horario horario) {
        if (horarioList == null)
            loadData();
        else
            horarioList.add(new HorarioCell(horario));
    }

    /**
     * Elimina un horario de un trabajador
     * @param cell Data de la tabla
     */
    public void eliminarHorario(HorarioCell cell) {
        if (cell == null) return;
        //trabajador.eliminarHorario(cell.getId());
        horarioList.remove(cell);
    }

    public ObservableList<HorarioCell> getHorarioList() {
        if (horarioList == null) loadData();
        return horarioList;
    }

    public String getNombreTrabajador() {
        return trabajador.getNombre();
    }

    public void setAdd(boolean add) {
        this.add = add;
        if (add)
            view.hideComponents();
        else
            view.showComponents();
    }
}
