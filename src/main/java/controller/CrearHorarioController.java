package controller;

import base.Controller;
import cell.ProyectoCell;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Constructora;
import model.Horario;
import model.Trabajador;
import network.endpoint.HorarioAPI;
import network.service.NetService;
import router.HorarioRouter;
import delegate.AddHorarioDelegate;
import util.AsyncTask;
import view.CrearHorarioView;
import java.time.LocalTime;
import java.util.function.Consumer;
import java.util.logging.Level;

/**
 * Controlador de la vista CrearHorarioView
 */
public final class CrearHorarioController extends Controller {

    private CrearHorarioView view;

    private Constructora model;

    private AddHorarioDelegate delegate;

    private ObjectProperty<LocalTime> entrada;

    private ObjectProperty<LocalTime> salida;

    private String rutTrabajador;

    public CrearHorarioController() {
        entrada = new SimpleObjectProperty<>();
        salida = new SimpleObjectProperty<>();

        model = Constructora.getInstance();
    }

    public void fetchProyectos(Consumer<ObservableList<ProyectoCell>> callBack) {
        AsyncTask.supplyAsync(() -> {
            ObservableList<ProyectoCell> cells = FXCollections.observableArrayList();

            model.getProyectos(rutTrabajador).forEach(proyecto -> cells.add(new ProyectoCell(proyecto)));

            return cells;
        }).thenAccept(callBack);
    }


    /**
     * Agregar un Horario al modelo Trabajador
     * @param dia
     */
    public void agregarHorario(int dia, ProyectoCell cell) {
        if (entrada.get().compareTo(salida.get()) > 0) {
            // TODO: falta excepcion
            //router.showWarning("La hora de entrada no puede superar la hora de salida").show();
            return;
        }

        Horario horario = new Horario(dia, entrada.get(), salida.get());

        model.agregarHorario(rutTrabajador, cell.getId(), horario);

        if (delegate != null)
            delegate.didAddHorario(horario);

        NetService service = NetService.getInstance();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Horario.class, new Horario.HorarioSerializer())
                .create();

        JsonObject json = gson.toJsonTree(horario).getAsJsonObject();

        System.out.println(json);

        service.request(HorarioAPI.CREATE, json)
                .subscribe(jsonElement -> {
                    horario.setId(jsonElement.getAsJsonObject().get("id_horario").getAsInt());
                }, throwable -> {
                    LOGGER.log(Level.SEVERE, "", throwable);
                });

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

    public void setView(CrearHorarioView view) {
        this.view = view;
    }

    public void setRutTrabajador(String rutTrabajador) {
        this.rutTrabajador = rutTrabajador;
    }
}
