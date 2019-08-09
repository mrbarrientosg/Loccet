package controller;

import base.Controller;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import delegate.SaveProyectoDelegate;
import exceptions.DateRangeException;
import exceptions.EmptyFieldException;
import javafx.beans.property.*;
import json.LocalDateTypeConverter;
import model.Constructora;
import model.Localizacion;
import model.Proyecto;
import network.endpoint.ProyectoAPI;
import network.service.NetService;
import view.CrearProyectoView;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.logging.Level;

/**
 * Se encarga de mostrar la información ingresada por el usuario en la vista CrearProyectoView
 *
 * @see view.CrearProyectoView
 *
 * @author Matias Zuñiga
 */

public final class CrearProyectoController extends Controller {

    private CrearProyectoView view;

    private Constructora model = Constructora.getInstance();

    private StringProperty name;

    private StringProperty cliente;

    private StringProperty monto;

    private StringProperty address;

    private StringProperty country;

    private StringProperty city;

    private StringProperty state;

    private ObjectProperty<LocalDate> fechaInicio;

    private ObjectProperty<LocalDate> fechaTermino;

    private SaveProyectoDelegate delegate;

    public CrearProyectoController() {
        name = new SimpleStringProperty(null);
        cliente = new SimpleStringProperty(null);
        monto = new SimpleStringProperty(null);

        address = new SimpleStringProperty(null);
        country = new SimpleStringProperty(null);
        city = new SimpleStringProperty(null);
        state = new SimpleStringProperty(null);

        fechaInicio = new SimpleObjectProperty<>(null);
        fechaTermino = new SimpleObjectProperty<>(null);
    }

    public void saveProyecto() throws EmptyFieldException, DateRangeException {
        if (fechaTermino.get() != null && fechaInicio.get().isBefore(fechaTermino.get())) {
            throw new DateRangeException("La fecha de inicio del proyecto no puede ser mayor que la de termino.");
        }

        Proyecto proyecto = new Proyecto();

        proyecto.setNombre(name.get());
        proyecto.setNombreCliente(cliente.get());
        proyecto.setCostoEstimado(new BigDecimal(monto.get()));

        Localizacion localizacion = new Localizacion(address.get(), country.get(), state.get(), city.get());

        proyecto.setLocalizacion(localizacion);

        proyecto.setFechaInicio(fechaInicio.get());
        proyecto.setFechaTermino(fechaTermino.get());

        model.agregarProyecto(proyecto);

        if (delegate != null)
            delegate.didSaveProyecto(proyecto);

        saveToDB(proyecto);
    }

    private void saveToDB(Proyecto proyecto) {
        NetService service = NetService.getInstance();

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeConverter())
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        JsonObject json = gson.toJsonTree(proyecto).getAsJsonObject();
        json.addProperty("dns_constructora", model.getDns());

        System.out.println(json);

        service.request(ProyectoAPI.CREATE, json)
                .subscribe(jsonElement -> {
                    JsonObject responseJson = jsonElement.getAsJsonObject();
                    proyecto.getLocalizacion().setId(responseJson.get("id_localizacion").getAsInt());
                    proyecto.setIdInventario(responseJson.get("id_inventario").getAsInt());
                    System.out.println(jsonElement);
                }, throwable -> {
                    LOGGER.log(Level.SEVERE, "", throwable);
                });
    }

    public void setView(CrearProyectoView view) {
        this.view = view;
    }

    public void setDelegate(SaveProyectoDelegate delegate) {
        this.delegate = delegate;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty clienteProperty() {
        return cliente;
    }

    public StringProperty montoProperty() {
        return monto;
    }

    public StringProperty addressProperty() {
        return address;
    }

    public StringProperty countryProperty() {
        return country;
    }

    public StringProperty cityProperty() {
        return city;
    }

    public StringProperty stateProperty() {
        return state;
    }

    public ObjectProperty<LocalDate> fechaInicioProperty() {
        return fechaInicio;
    }

    public ObjectProperty<LocalDate> fechaTerminoProperty() {
        return fechaTermino;
    }
}
