package controller;

import base.Controller;
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
import model.Especialidad;
import model.Trabajador;
import model.TrabajadorPartTime;
import model.TrabajadorTiempoCompleto;
import network.endpoint.TrabajadorAPI;
import network.service.NetService;
import view.DetalleTrabajadorView;

import java.time.LocalDate;
import java.util.logging.Level;

public class DetalleTrabajadorController extends Controller {

    private Trabajador model;

    private DetalleTrabajadorView view;

    private StringProperty name = new SimpleStringProperty();

    private StringProperty lastName = new SimpleStringProperty();

    private StringProperty address = new SimpleStringProperty();

    private StringProperty country = new SimpleStringProperty();

    private StringProperty state = new SimpleStringProperty();

    private StringProperty city = new SimpleStringProperty();

    private StringProperty telephone = new SimpleStringProperty();

    private StringProperty email = new SimpleStringProperty();

    private StringProperty horas = new SimpleStringProperty();

    private ObjectProperty<LocalDate> birthday = new SimpleObjectProperty<>();

    private ObjectProperty<Especialidad> speciality = new SimpleObjectProperty<>();

    private SaveTrabajadorDelegate delegate;

    private Trabajador oldTrabajador;

    private NetService<TrabajadorAPI> service = NetService.getInstance();

    public void guardar() throws EmptyFieldException {
        model.setNombre(name.get());
        model.setApellido(lastName.get());

        model.getLocalizacion().setDireccion(address.get());
        model.getLocalizacion().setPais(country.get());
        model.getLocalizacion().setEstado(state.get());
        model.getLocalizacion().setCiudad(city.get());

        model.setTelefono(telephone.get());
        model.setCorreoElectronico(email.get());
        model.setFechaNacimiento(birthday.get());

        model.setEspecialidad(speciality.get());

        if (model instanceof TrabajadorPartTime)
            ((TrabajadorPartTime) model).setCantidadHoraTrabajada(Integer.parseInt(horas.get()));

        if (delegate != null)
            delegate.didSaveTrabajador(model);
    }

    private void loadData() {
        name.setValue(model.getNombre());
        lastName.setValue(model.getApellido());

        address.setValue(model.getLocalizacion().getDireccion());
        country.setValue(model.getLocalizacion().getPais());
        state.setValue(model.getLocalizacion().getEstado());
        city.setValue(model.getLocalizacion().getCiudad());

        telephone.setValue(model.getTelefono());
        email.setValue(model.getCorreoElectronico());
        birthday.setValue(model.getFechaNacimiento());

        speciality.set(model.getEspecialidad());

        if (model instanceof TrabajadorPartTime)
            horas.set(String.valueOf(((TrabajadorPartTime) model).getCantidadHoraTrabajada()));
    }

    public void save() {
        if (oldTrabajador.equals(model))
            return;

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeConverter())
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        JsonObject json = gson.toJsonTree(model).getAsJsonObject();

        if (model instanceof TrabajadorPartTime) {
            json.addProperty("cantidad_hora_trabajada", ((TrabajadorPartTime) model).getCantidadHoraTrabajada());
        }

        System.out.println(json);

        service.request(TrabajadorAPI.UPDATE, json)
                .subscribe(System.out::println, throwable -> {
                    LOGGER.log(Level.SEVERE, "", throwable);
                });

    }

    public void setView(DetalleTrabajadorView view) {
        this.view = view;
    }

    public void setModel(Trabajador model) {
        this.model = model;

        if (model instanceof TrabajadorPartTime) {
            oldTrabajador = new TrabajadorPartTime((TrabajadorPartTime) model);
            view.showPartTimeVbox();
        } else {
            oldTrabajador = new TrabajadorTiempoCompleto((TrabajadorTiempoCompleto) model);
            view.hidePartTimeVbox();
        }

        loadData();
        view.bind();
    }

    public void setDelegate(SaveTrabajadorDelegate delegate) {
        this.delegate = delegate;
    }

    public String getRut() {
        return model.getRut();
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
