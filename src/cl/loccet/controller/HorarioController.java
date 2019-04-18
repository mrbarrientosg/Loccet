package cl.loccet.controller;

import cl.loccet.base.Controller;
import cl.loccet.model.Dias;
import cl.loccet.model.Horario;
import cl.loccet.model.Proyecto;
import cl.loccet.model.Trabajador;
import cl.loccet.view.HorarioView;
import javafx.beans.binding.ObjectBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.stream.Collectors;

public class HorarioController extends Controller {

    private HorarioView view;

    private Proyecto proyecto;

    private Trabajador trabajador;

    private ObservableList<String> horarioList;
    
    public HorarioController(HorarioView view, Proyecto proyecto, Trabajador trabajador) {
        this.view = view;
        this.proyecto = proyecto;
        this.trabajador = trabajador;
    }

    private void loadData() {
        horarioList = FXCollections.observableList(trabajador.obtenerListaHorario(proyecto.getId()).stream().map(Horario::toString).collect(Collectors.toList()));
    }

    public void agregarHorario(int dia) {
        switch (dia) {
            case Dias.LUNES:
                System.out.println("Lunes");
                break;
        }
    }

    public ObservableList<String> getHorarioList() {
        return horarioList;
    }


    public String getNombreTrabajador() {
        return trabajador.getNombre();
    }

    public String getNombreProyecto() {
        return proyecto.getNombreProyecto();
    }
}
