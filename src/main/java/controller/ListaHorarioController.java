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

public class ListaHorarioController extends Controller implements AddHorarioDelegate {

    private ListaHorarioView view;

    private Trabajador trabajador;

    private ObservableList<HorarioCell> horarioList;

    private boolean add;

    private void loadData() {
        if (horarioList != null) return;

        List<Horario> horarios = trabajador.obtenerListaHorario();

        if (horarios.isEmpty()) return;

        horarioList = FXCollections.observableList(horarios.stream().map(HorarioCell::new).collect(Collectors.toList()));
    }

    @Override
    public void didAddHorario(Horario horario) {
        if (horarioList == null)
            loadData();
        else
            horarioList.add(new HorarioCell(horario));

        view.refreshTable();
    }

    public void eliminarHorario(HorarioCell cell) {
        if (cell == null) return;
        trabajador.eliminarHorario(cell.getId());
        horarioList.remove(cell);
        view.refreshTable();
    }

    public ObservableList<HorarioCell> getHorarioList() {
        return horarioList;
    }

    public String getNombreTrabajador() {
        return trabajador.getNombre();
    }

    public void setView(ListaHorarioView view) {
        this.view = view;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
        loadData();
    }

    public void setAdd(boolean add) {
        this.add = add;
        if (add)
            view.hideComponents();
    }

    public boolean isAdd() {
        return add;
    }
}
