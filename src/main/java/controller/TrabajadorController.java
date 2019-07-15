package controller;

import base.Controller;
import exceptions.EmptyFieldException;
import exceptions.InvalidaRutException;
import javafx.beans.property.*;
import model.*;
import router.TrabajadorRouter;
import delegate.EditTrabajadorDelegate;
import view.TrabajadorView;

import java.time.LocalDate;

/**
 * Controlador para la vista Trabajador
 *
 * @author Matias Barrientos
 */
public final class TrabajadorController extends Controller {

    private TrabajadorView view;

    private Constructora model;

    private StringProperty rut;

    private StringProperty name;

    private StringProperty lastName;

    private StringProperty speciality;

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

    public TrabajadorController() {
        rut = new SimpleStringProperty(null);
        name = new SimpleStringProperty(null);
        lastName = new SimpleStringProperty(null);
        speciality = new SimpleStringProperty(null);
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
    }

    public void guardarTrabajador() throws EmptyFieldException, InvalidaRutException {
        Trabajador trabajador;

        Localizacion localizacion = new Localizacion(address.get(), zip.get(), country.get(), state.get(), city.get());


        if (partTime.get()) {
            Integer horas = new Integer(hours.getValue());
            trabajador = new TrabajadorPartTime(horas);
        } else {
            trabajador = new TrabajadorTiempoCompleto();
        }

        trabajador.setRut(rut.get());
        trabajador.setNombre(name.get());
        trabajador.setApellido(lastName.get());
        trabajador.setFechaNacimiento(birthday.get());

        trabajador.setEspecialidad(Especialidades.getInstance().obtener(speciality.get()));
        trabajador.setLocalizacion(localizacion);

        trabajador.setTelefono(telephone.get());
        trabajador.setCorreoElectronico(email.get());

        model.agregarTrabajador(trabajador);
    }

    public void setModel(Constructora model) {
        this.model = model;
    }

    public void setView(TrabajadorView view) {
        this.view = view;
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

    public StringProperty specialityProperty() {
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
