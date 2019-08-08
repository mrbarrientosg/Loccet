package controller;

import base.Controller;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import delegate.SaveProyectoDelegate;
import exceptions.EmptyFieldException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Constructora;
import model.Proyecto;
import network.endpoint.ProyectoAPI;
import network.service.NetService;
import view.DetalleProyectoView;

import java.time.LocalDate;
import java.util.logging.Level;

/**
 * Controlador para la vista DetalleProyecto
 *
 * @see view.DetalleProyectoView
 *
 * @author Matias Barrientos
 * @author Matias Zuñiga
 */
public final class DetalleProyectoController extends Controller {

    private Constructora model;

    private Proyecto actualProyecto;

    private Proyecto oldProyecto;

    private StringProperty name;

    private StringProperty address;

    private StringProperty country;

    private StringProperty state;

    private StringProperty city;

    private StringProperty client;

    private ObjectProperty<LocalDate> startDate;

    private ObjectProperty<LocalDate> endDate;

    private NetService service;

    private SaveProyectoDelegate delegate;

    private String idProyecto;

    public DetalleProyectoController() {
        name = new SimpleStringProperty(null);
        client = new SimpleStringProperty(null);

        address = new SimpleStringProperty(null);
        country = new SimpleStringProperty(null);
        city = new SimpleStringProperty(null);
        state = new SimpleStringProperty(null);

        startDate = new SimpleObjectProperty<>(null);
        endDate = new SimpleObjectProperty<>(null);

        service = NetService.getInstance();
        model = Constructora.getInstance();
    }

    /**
     * Guarda los datos de la vista en el proyecto
     *
     * @throws EmptyFieldException Si deja algun campo vacio
     *
     * @author Matias Zuñiga
     */
    public void actualizar() throws EmptyFieldException {
        actualProyecto.setNombre(name.get());
        actualProyecto.getLocalizacion().setDireccion(address.get());
        actualProyecto.getLocalizacion().setPais(country.get());
        actualProyecto.getLocalizacion().setEstado(state.get());
        actualProyecto.getLocalizacion().setCiudad(city.get());
        actualProyecto.setNombreCliente(client.get());
    }

    /**
     * Carga los datos del proyecto en la vista
     *
     * @author Matias Barrientos
     */
    private void loadData() {
        name.setValue(actualProyecto.getNombre());
        address.setValue(actualProyecto.getLocalizacion().getDireccion());
        country.setValue(actualProyecto.getLocalizacion().getPais());
        state.setValue(actualProyecto.getLocalizacion().getEstado());
        city.setValue(actualProyecto.getLocalizacion().getCiudad());
        client.setValue(actualProyecto.getNombreCliente());
        startDate.setValue(actualProyecto.getFechaInicio());
        endDate.setValue(actualProyecto.getFechaTermino());
    }

    /**
     * Guarda los datos del proyecto en la base de datos
     *
     * @author Matias Barrientos
     */
    public void save() {
        if (oldProyecto.equals(actualProyecto))
            return;

        if (delegate != null)
            delegate.didSaveProyecto(actualProyecto);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Proyecto.class, new Proyecto.ProyectoSerializer())
                .create();

        service.request(ProyectoAPI.UPDATE, gson.toJsonTree(actualProyecto).getAsJsonObject())
                .subscribe(System.out::println, throwable -> {
                    LOGGER.log(Level.SEVERE, "", throwable);
                });

    }

    public void setIdProyecto(String idProyecto) {
        this.idProyecto = idProyecto;
        actualProyecto = model.obtenerProyecto(idProyecto);
        oldProyecto = new Proyecto(actualProyecto);
        loadData();
    }

    public void setDelegate(SaveProyectoDelegate delegate) {
        this.delegate = delegate;
    }

    public String getIdProyecto() {
        return idProyecto;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty addressProperty() {
        return address;
    }

    public StringProperty countryProperty() {
        return country;
    }

    public StringProperty stateProperty() {
        return state;
    }

    public StringProperty cityProperty() {
        return city;
    }

    public StringProperty clientProperty() {
        return client;
    }

    public ObjectProperty<LocalDate> startDateProperty() {
        return startDate;
    }

    public ObjectProperty<LocalDate> endDateProperty() {
        return endDate;
    }
}

