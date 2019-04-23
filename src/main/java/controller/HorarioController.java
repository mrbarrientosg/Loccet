package controller;

import base.Controller;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import model.Horario;
import model.Proyecto;
import model.Trabajador;
import router.HorarioRouter;
import state.AddHorarioDelegate;
import view.HorarioView;
import java.time.LocalTime;

/**
 * Controlador de la vista HorarioView
 */
public final class HorarioController extends Controller {

    private HorarioView view;

    private HorarioRouter router;

    private Proyecto proyecto;

    private Trabajador trabajador;

    private final ObjectProperty<LocalTime> entrada = new SimpleObjectProperty<>();

    private final ObjectProperty<LocalTime> salida = new SimpleObjectProperty<>();

    private AddHorarioDelegate delegate;


    /**
     * Agregar un Horario al modelo Trabajador
     * @param dia
     */
    public void agregarHorario(int dia) {
        if (entrada.get().compareTo(salida.get()) > 0) {
            router.showWarning("La hora de entrada no puede superar la hora de salida").show();
            return;
        }

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

    public void setView(HorarioView view) {
        this.view = view;
        view.refreshView();
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public void setRouter(HorarioRouter router) {
        this.router = router;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }
}
