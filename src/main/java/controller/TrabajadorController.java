package controller;

import base.Controller;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Constructora;
import model.Trabajador;
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
}
