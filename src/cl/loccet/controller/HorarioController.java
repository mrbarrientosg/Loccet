package cl.loccet.controller;

import cl.loccet.base.Controller;
import cl.loccet.cell.HorarioCell;
import cl.loccet.model.Dias;
import cl.loccet.model.Horario;
import cl.loccet.model.Proyecto;
import cl.loccet.model.Trabajador;
import cl.loccet.state.AddHorarioDelegate;
import cl.loccet.view.HorarioView;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class HorarioController extends Controller {

    private HorarioView view;

    private Proyecto proyecto;

    private Trabajador trabajador;

    private ObjectProperty<LocalTime> entrada;

    private ObjectProperty<LocalTime> salida;

    private AddHorarioDelegate delegate;

    public HorarioController(HorarioView view, Proyecto proyecto, Trabajador trabajador) {
        this.view = view;
        this.proyecto = proyecto;
        this.trabajador = trabajador;

        entrada = new SimpleObjectProperty<>();
        salida = new SimpleObjectProperty<>();
    }

    public void agregarHorario(int dia) {
        if (entrada.get().compareTo(salida.get()) > 0) return;

        Horario horario = new Horario.Builder(dia, proyecto.getId(), proyecto.getNombreProyecto())
                .fechaInicio(entrada.get())
                .fechaTermino(salida.get())
                .build();

        trabajador.agregarHorario(horario);

        if (delegate != null)
            delegate.didAddHorario(horario);
    }

    public void addListView() {
        view.addListView(trabajador);
    }

    public String getNombreTrabajador() {
        return trabajador.getNombre();
    }

    public String getNombreProyecto() {
        return proyecto.getNombreProyecto();
    }

    public ObjectProperty<LocalTime> entradaProperty() {
        return entrada;
    }

    public ObjectProperty<LocalTime> salidaProperty() {
        return salida;
    }

    public void setDelegate(AddHorarioDelegate delegate) {
        this.delegate = delegate;
    }
}
