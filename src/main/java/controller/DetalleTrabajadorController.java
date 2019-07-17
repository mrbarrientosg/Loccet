package controller;

import base.Controller;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import delegate.EditTrabajadorDelegate;
import exceptions.EmptyFieldException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import json.LocalDateTypeConverter;
import model.Proyecto;
import model.Trabajador;
import model.TrabajadorPartTime;
import model.TrabajadorTiempoCompleto;
import network.endpoint.ProyectoAPI;
import network.endpoint.TrabajadorAPI;
import network.service.Router;
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

    private ObjectProperty<LocalDate> birthday = new SimpleObjectProperty<>();

    private EditTrabajadorDelegate delegate;

    private Trabajador oldTrabajador;

    private Router<TrabajadorAPI> service = Router.getInstance();

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

        if (delegate != null)
            delegate.didEditTrabajador();

        // TODO: Falta la especialidad
        //model.setEspecialidad();

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
    }

    public void save() {
        if (oldTrabajador.equals(model))
            return;

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeConverter())
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        System.out.println(gson.toJsonTree(model).getAsJsonObject());

        service.request(TrabajadorAPI.UPDATE, gson.toJsonTree(model).getAsJsonObject())
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
        } else {
            oldTrabajador = new TrabajadorTiempoCompleto((TrabajadorTiempoCompleto) model);
        }
        loadData();
        view.bind();
    }

    public void setDelegate(EditTrabajadorDelegate delegate) {
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
}
