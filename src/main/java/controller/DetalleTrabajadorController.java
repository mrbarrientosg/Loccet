package controller;

import base.Controller;
import base.Injectable;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import delegate.SaveTrabajadorDelegate;
import exceptions.EmptyFieldException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import json.LocalDateTypeConverter;
import model.*;
import network.endpoint.TrabajadorAPI;
import network.service.NetService;
import view.DetalleTrabajadorView;
import view.ListaTrabajadorView;

import java.time.LocalDate;
import java.util.logging.Level;

public final class DetalleTrabajadorController extends Controller {

    private DetalleTrabajadorView view;

    private Constructora model;

    private StringProperty name;

    private StringProperty lastName;

    private StringProperty address;

    private StringProperty country;

    private StringProperty state;

    private StringProperty city;

    private StringProperty telephone;

    private StringProperty email;

    private StringProperty horas;

    private ObjectProperty<LocalDate> birthday;

    private ObjectProperty<Especialidad> speciality;

    private SaveTrabajadorDelegate delegate;

    private Trabajador actualTrabajador;

    private Trabajador oldTrabajador;

    private String rutTrabajador;

    private NetService service;

    public DetalleTrabajadorController() {
        name = new SimpleStringProperty();
        lastName = new SimpleStringProperty();

        address = new SimpleStringProperty();
        country = new SimpleStringProperty();
        state = new SimpleStringProperty();
        city = new SimpleStringProperty();

        telephone = new SimpleStringProperty();
        email = new SimpleStringProperty();

        horas = new SimpleStringProperty();

        birthday = new SimpleObjectProperty<>();
        speciality = new SimpleObjectProperty<>();

        service = NetService.getInstance();
        model = Constructora.getInstance();
    }

    public void guardar() throws EmptyFieldException {
        actualTrabajador.setNombre(name.get());
        actualTrabajador.setApellido(lastName.get());

        actualTrabajador.getLocalizacion().setDireccion(address.get());
        actualTrabajador.getLocalizacion().setPais(country.get());
        actualTrabajador.getLocalizacion().setEstado(state.get());
        actualTrabajador.getLocalizacion().setCiudad(city.get());

        actualTrabajador.setTelefono(telephone.get());
        actualTrabajador.setCorreoElectronico(email.get());
        actualTrabajador.setFechaNacimiento(birthday.get());

        actualTrabajador.setEspecialidad(speciality.get());

        if (actualTrabajador instanceof TrabajadorPartTime)
            ((TrabajadorPartTime) actualTrabajador).setCantidadHoraTrabajada(Integer.parseInt(horas.get()));

        if (delegate != null)
            delegate.didSaveTrabajador(actualTrabajador);
    }

    private void loadData() {
        name.setValue(actualTrabajador.getNombre());
        lastName.setValue(actualTrabajador.getApellido());

        address.setValue(actualTrabajador.getLocalizacion().getDireccion());
        country.setValue(actualTrabajador.getLocalizacion().getPais());
        state.setValue(actualTrabajador.getLocalizacion().getEstado());
        city.setValue(actualTrabajador.getLocalizacion().getCiudad());

        telephone.setValue(actualTrabajador.getTelefono());
        email.setValue(actualTrabajador.getCorreoElectronico());
        birthday.setValue(actualTrabajador.getFechaNacimiento());

        speciality.set(actualTrabajador.getEspecialidad());

        if (actualTrabajador instanceof TrabajadorPartTime)
            horas.set(String.valueOf(((TrabajadorPartTime) actualTrabajador).getCantidadHoraTrabajada()));
    }

    public void save() {
        if (oldTrabajador.equals(actualTrabajador))
            return;

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeConverter())
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        JsonObject json = gson.toJsonTree(actualTrabajador).getAsJsonObject();

        if (actualTrabajador instanceof TrabajadorPartTime) {
            json.addProperty("cantidad_hora_trabajada", ((TrabajadorPartTime) actualTrabajador).getCantidadHoraTrabajada());
        }

        System.out.println(json);

        service.request(TrabajadorAPI.UPDATE, json)
                .subscribe(System.out::println, throwable -> {
                    LOGGER.log(Level.SEVERE, "", throwable);
                });

    }

    public void setRutTrabajador(String rutTrabajador) {
        this.rutTrabajador = rutTrabajador;

        actualTrabajador = model.obtenerTrabajador(rutTrabajador);

        if (actualTrabajador instanceof TrabajadorPartTime) {
            oldTrabajador = new TrabajadorPartTime((TrabajadorPartTime) actualTrabajador);
            view.showPartTimeVbox();
        } else {
            oldTrabajador = new TrabajadorTiempoCompleto((TrabajadorTiempoCompleto) actualTrabajador);
            view.hidePartTimeVbox();
        }

        loadData();
    }

    public void setDelegate(SaveTrabajadorDelegate delegate) {
        this.delegate = delegate;
    }

    public void setView(DetalleTrabajadorView view) {
        this.view = view;
    }

    public String getRutTrabajador() {
        return rutTrabajador;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty lastNameProperty() {
        return lastName;
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

    public StringProperty telephoneProperty() {
        return telephone;
    }

    public StringProperty emailProperty() {
        return email;
    }

    public ObjectProperty<LocalDate> birthdayProperty() {
        return birthday;
    }

    public ObjectProperty<Especialidad> specialityProperty() {
        return speciality;
    }

    public StringProperty horasProperty() {
        return horas;
    }
}
