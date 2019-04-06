package cl.loccet.controller;

import cl.loccet.base.Controller;
import cl.loccet.model.Constructora;
import cl.loccet.model.Especialidades;
import cl.loccet.model.Localizacion;
import cl.loccet.model.Trabajador;
import cl.loccet.router.AgregarTrabajadorRouter;
import cl.loccet.router.HomeRouter;
import cl.loccet.util.ValidationResult;
import cl.loccet.util.Validator;
import cl.loccet.view.AgregarTrabajadorView;
import cl.loccet.view.HomeView;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class AgregarTrabajadorController extends Controller {

    private final AgregarTrabajadorView view;

    private final Constructora model;

    private final AgregarTrabajadorRouter router;

    private Trabajador.Builder trabajadorBuilder = new Trabajador.Builder();

    private Localizacion.Builder localizacionBuilder = new Localizacion.Builder();

    private StringProperty rut = new SimpleStringProperty();

    private StringProperty name = new SimpleStringProperty();

    private StringProperty lastName = new SimpleStringProperty();

    private StringProperty speciality = new SimpleStringProperty();

    private ObjectProperty<LocalDate> birthday = new SimpleObjectProperty<>();

    private StringProperty address = new SimpleStringProperty();

    private StringProperty zip = new SimpleStringProperty();

    private StringProperty country = new SimpleStringProperty();

    private StringProperty city = new SimpleStringProperty();

    private StringProperty state = new SimpleStringProperty();

    private StringProperty telephone = new SimpleStringProperty();

    private StringProperty email = new SimpleStringProperty();

    public AgregarTrabajadorController(AgregarTrabajadorView view, Constructora model, AgregarTrabajadorRouter router) {
        this.view = view;
        this.model = model;
        this.router = router;
    }

    public void guardarTrabajador() {
        if (validarInformacionPersonal() && validarLocalizacion() && validarContacto()) { // Son validos los datos

            trabajadorBuilder
                    .localizacion(localizacionBuilder.build())
                    .especialidad(Especialidades.getInstance().get(speciality.get()))
                    .fechaNacimiento(birthday.get());

            boolean result = true; // resultado del modelo
            //model.agregarTrabajador(t);

            if (result) {
                // Agregado exitosamente
            } else {
                // Error no se pudo agregar (Mostrar alerta)
            }
        }
    }

    /**
     * Valida solo la informacion personal del trabajador, y si
     * lo son lo agrega al builder.
     * @return false si un dato es incorreto, lo contrario true
     */
    private boolean validarInformacionPersonal() {
        // 1 validar RUT
        // TODO: Validar que el rut sea correcto
        if (!validacionIsEmpty(rut, "Debe ingresar el RUT"))
            return false;

        trabajadorBuilder.rut(rut.get());

        // 2 validar nombre
        if (!validacionIsEmpty(name, "Debe ingresar el nombre"))
            return false;

        trabajadorBuilder.nombre(name.get());

        // 3 validar apellido
        if (!validacionIsEmpty(lastName, "Debe ingresar el apellido"))
            return false;

        trabajadorBuilder.apellido(lastName.get());

        return true;
    }

    private boolean validarLocalizacion() {
        // 1 validar la dirrecion
        if (!validacionIsEmpty(address, "Debe ingresar la dirreción"))
            return false;

        localizacionBuilder.direccion(address.get());

        // 2 validar pais
        if (!validacionIsEmpty(country, "Debe ingresar el país"))
            return false;

        localizacionBuilder.pais(country.get());

        // 3 validar ciudad
        if (!validacionIsEmpty(city, "Debe ingresar la ciudad"))
            return false;

        localizacionBuilder.ciudad(city.get());

        // 4 validar el estado
        if (!validacionIsEmpty(state, "Debe ingresar el estado/provincia"))
            return false;

        localizacionBuilder.estado(state.get());

        return true;
    }

    private boolean validarContacto() {
        // 1 validar telefono
        if (!validacionIsEmpty(telephone, "Debe ingresar el telefono"))
            return false;

        trabajadorBuilder.telefono(telephone.get());

        // 2 validar email
        // TODO: Validar que el email sea correcto, que tenga el @
        if (!validacionIsEmpty(email, "Debe ingresar el correo electronico"))
            return false;

        trabajadorBuilder.correoElectronico(email.get());

        return true;
    }

    private boolean validacionIsEmpty(StringProperty property, String errorMessage) {
        if (property.isEmpty().get() || property.isNull().get()) {
            router.showError(errorMessage);
            return false;
        }
        return true;
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
