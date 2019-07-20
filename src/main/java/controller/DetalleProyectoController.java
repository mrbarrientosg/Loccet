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
import model.Proyecto;
import network.endpoint.ProyectoAPI;
import network.service.NetService;
import view.DetalleProyectoView;

import java.time.LocalDate;
import java.util.logging.Level;

public class DetalleProyectoController extends Controller {

    private DetalleProyectoView view;

    private Proyecto model;

    private Proyecto oldProyecto;

    private StringProperty name;

    private StringProperty address;

    private StringProperty country;

    private StringProperty state;

    private StringProperty city;

    private StringProperty client;

    private ObjectProperty<LocalDate> startDate;

    private ObjectProperty<LocalDate> endDate;

    private NetService<ProyectoAPI> service;

    private SaveProyectoDelegate delegate;

    public DetalleProyectoController() {
        name = new SimpleStringProperty(null);
        client = new SimpleStringProperty(null);
        //monto = new SimpleStringProperty(null);

        address = new SimpleStringProperty(null);
        country = new SimpleStringProperty(null);
        city = new SimpleStringProperty(null);
        state = new SimpleStringProperty(null);

        startDate = new SimpleObjectProperty<>(null);
        endDate = new SimpleObjectProperty<>(null);

        service = NetService.getInstance();
    }

    public void actualizar() throws EmptyFieldException {
        model.setNombre(name.get());
        model.getLocalizacion().setDireccion(address.get());
        model.getLocalizacion().setPais(country.get());
        model.getLocalizacion().setEstado(state.get());
        model.getLocalizacion().setCiudad(city.get());
        model.setNombreCliente(client.get());
    }

    private void loadData() {
        name.setValue(model.getNombre());
        address.setValue(model.getLocalizacion().getDireccion());
        country.setValue(model.getLocalizacion().getPais());
        state.setValue(model.getLocalizacion().getEstado());
        city.setValue(model.getLocalizacion().getCiudad());
        client.setValue(model.getNombreCliente());
        startDate.setValue(model.getFechaInicio());
        endDate.setValue(model.getFechaTermino());
    }

    public void save() {
        if (oldProyecto.equals(model))
            return;

        if (delegate != null)
            delegate.didSaveProyecto(model);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Proyecto.class, new Proyecto.ProyectoSerializer())
                .create();

        service.request(ProyectoAPI.UPDATE, gson.toJsonTree(model).getAsJsonObject())
                .subscribe(System.out::println, throwable -> {
                    LOGGER.log(Level.SEVERE, "", throwable);
                });

    }

    public void setView(DetalleProyectoView view) {
        this.view = view;
    }

    public void setModel(Proyecto model) {
        this.model = model;
        oldProyecto = new Proyecto(model);
        loadData();
        view.bind();
    }

    public void setDelegate(SaveProyectoDelegate delegate) {
        this.delegate = delegate;
    }

    public String getIdProyecto() { return model.getId(); }

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

