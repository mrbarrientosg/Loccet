package controller;

import base.Controller;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import delegate.SaveTrabajadorDelegate;
import exceptions.EmptyFieldException;
import exceptions.InvalidRutException;
import exceptions.ObjectExistException;
import io.reactivex.Maybe;
import javafx.beans.property.*;
import json.LocalDateTypeConverter;
import model.*;
import network.endpoint.TrabajadorAPI;
import network.service.NetService;
import view.CrearTrabajadorView;

import java.time.LocalDate;
import java.util.logging.Level;

/**
 * Controlador para la vista Trabajador
 *
 * @author Matias Barrientos
 */
public final class CrearTrabajadorController extends Controller {

    private Constructora model;

    private StringProperty rut;

    private StringProperty name;

    private StringProperty lastName;

    private ObjectProperty<Especialidad> speciality;

    private ObjectProperty<LocalDate> birthday;

    private StringProperty address;

    private StringProperty zip;

    private StringProperty country;

    private StringProperty city;

    private StringProperty state;

    private StringProperty telephone;

    private StringProperty email;

    private BooleanProperty partTime;

    private StringProperty hours;

    private SaveTrabajadorDelegate delegate;

    public CrearTrabajadorController() {
        rut = new SimpleStringProperty(null);
        name = new SimpleStringProperty(null);
        lastName = new SimpleStringProperty(null);
        speciality = new SimpleObjectProperty<>(null);
        birthday = new SimpleObjectProperty<>(null);

        address = new SimpleStringProperty(null);
        zip = new SimpleStringProperty(null);
        country = new SimpleStringProperty(null);
        city = new SimpleStringProperty(null);
        state = new SimpleStringProperty(null);

        telephone = new SimpleStringProperty(null);
        email = new SimpleStringProperty(null);

        partTime = new SimpleBooleanProperty(false);
        hours = new SimpleStringProperty(null);

        model = Constructora.getInstance();
    }

    public void guardarTrabajador() throws EmptyFieldException, InvalidRutException, ObjectExistException {
        if (model.obtenerTrabajador(rut.get()) != null)
            throw new ObjectExistException("El trabajador ya esta en la constructora");

        Trabajador trabajador;

        Integer horas = null;

        if (partTime.get()) {
            horas = new Integer(hours.getValue());
            trabajador = new TrabajadorPartTime(horas);
        } else {
            trabajador = new TrabajadorTiempoCompleto();
        }

        trabajador.setRut(rut.get());
        trabajador.setNombre(name.get());
        trabajador.setApellido(lastName.get());
        trabajador.setFechaNacimiento(birthday.get());

        trabajador.setEspecialidad(speciality.get());

        Localizacion localizacion = new Localizacion(address.get(), zip.get(), country.get(), state.get(), city.get());

        trabajador.setLocalizacion(localizacion);

        trabajador.setTelefono(telephone.get());
        trabajador.setCorreoElectronico(email.get());

        model.agregarTrabajador(trabajador);

        if (delegate != null)
            delegate.didSaveTrabajador(trabajador);

        NetService service = NetService.getInstance();

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeConverter())
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        JsonObject json = gson.toJsonTree(trabajador).getAsJsonObject();
        json.addProperty("cantidad_hora_trabajada", horas != null ? horas : 8);
        json.addProperty("tiempo_completo", horas != null ? 0 : 1);

        json.addProperty("id_especialidad", trabajador.getEspecialidad().getId());

        json.addProperty("dns_constructora", model.getDns());

        System.out.println(json);

        service.request(TrabajadorAPI.CREATE, json)
                .flatMap(jsonElement -> {
                    if (jsonElement.getAsJsonObject().has("id_localizacion"))
                        localizacion.setId(jsonElement.getAsJsonObject().get("id_localizacion").getAsInt());
                    else {
                        json.remove("dns_constructora");
                        return service.request(TrabajadorAPI.UPDATE, json);
                    }

                    return Maybe.just(new JsonObject());
                })
                .subscribe(System.out::println, throwable -> {
                    LOGGER.log(Level.SEVERE, "", throwable);
                });
    }

    public void setDelegate(SaveTrabajadorDelegate delegate) {
        this.delegate = delegate;
    }

    public StringProperty rutProperty() {
        return rut;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public ObjectProperty<Especialidad> specialityProperty() {
        return speciality;
    }

    public ObjectProperty<LocalDate> birthdayProperty() {
        return birthday;
    }

    public StringProperty addressProperty() {
        return address;
    }

    public StringProperty zipProperty() {
        return zip;
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

    public StringProperty telephoneProperty() {
        return telephone;
    }

    public StringProperty emailProperty() {
        return email;
    }

    public BooleanProperty partTimeProperty() {
        return partTime;
    }

    public StringProperty hoursProperty() {
        return hours;
    }
}
