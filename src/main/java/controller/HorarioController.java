package controller;

import base.Controller;
import cell.ProyectoCell;
import cell.TrabajadorCell;
import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Horario;
import model.Proyecto;
import model.Trabajador;
import router.HorarioRouter;
import delegate.AddHorarioDelegate;
import view.HorarioView;
import java.time.LocalTime;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

/**
 * Controlador de la vista HorarioView
 */
public final class HorarioController extends Controller {

    private HorarioView view;

    private HorarioRouter router;

    private Trabajador trabajador;

    private final ObjectProperty<LocalTime> entrada = new SimpleObjectProperty<>();

    private final ObjectProperty<LocalTime> salida = new SimpleObjectProperty<>();

    private AddHorarioDelegate delegate;

    public void fetchProyectos(Consumer<ObservableList<ProyectoCell>> callBack) {
        CompletableFuture.supplyAsync(() -> {
            ObservableList<ProyectoCell> cells = FXCollections.observableArrayList();

            trabajador.getProyectos().forEach(proyecto -> cells.add(new ProyectoCell(proyecto)));

            return cells;
        }).thenAccept(callBack);
    }


    /**
     * Agregar un Horario al modelo Trabajador
     * @param dia
     */
    public void agregarHorario(int dia, ProyectoCell cell) {
        if (entrada.get().compareTo(salida.get()) > 0) {
            router.showWarning("La hora de entrada no puede superar la hora de salida").show();
            return;
        }

        Horario horario = new Horario(dia, entrada.get(), salida.get());

        trabajador.agregarHorario(cell.getId(), horario);

        if (delegate != null)
            delegate.didAddHorario(horario);

        view.close();
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

    public void setRouter(HorarioRouter router) {
        this.router = router;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }
}
