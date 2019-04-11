package cl.loccet.controller;

import cl.loccet.base.Controller;
import cl.loccet.model.Constructora;
import cl.loccet.model.Especialidades;
import cl.loccet.model.Localizacion;
import cl.loccet.model.Trabajador;
import cl.loccet.router.TrabajadorRouter;
import cl.loccet.state.AddTrabajadorStrategy;
import cl.loccet.state.EditTrabajadorDelegate;
import cl.loccet.state.EditTrabajadorStategy;
import cl.loccet.state.SaveStrategy;
import cl.loccet.view.TrabajadorView;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.time.LocalDate;

public class TrabajadorController extends Controller {

    private TrabajadorView view;

    private Constructora model;

    private TrabajadorRouter router;

    private Trabajador.Builder trabajadorBuilder;

    private Localizacion.Builder localizacionBuilder;

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

    private SaveStrategy<Trabajador> saveStrategy;

    private EditTrabajadorDelegate delegate;

    public TrabajadorController() {
        trabajadorBuilder = new Trabajador.Builder();
        localizacionBuilder = new Localizacion.Builder();

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


    public void guardarTrabajador() {
        if (!validarTrabajador()) return;

        localizacionBuilder
                .codigoPostal(zip.get());

        trabajadorBuilder
                .localizacion(localizacionBuilder.build())
                .especialidad(Especialidades.getInstance().get(speciality.get()))
                .fechaNacimiento(birthday.get());

        Trabajador newT = trabajadorBuilder.build();

        Trabajador old = saveStrategy.save(newT);

        if (delegate != null && saveStrategy instanceof EditTrabajadorStategy) {
            delegate.didEdit(old, newT);
        }

        view.closeView();
    }


    private boolean validarTrabajador() {
        return validarInformacionPersonal() && validarLocalizacion() && validarContacto();
    }

    /**
     * Valida solo la informacion personal del trabajador, y si
     * lo son lo agrega al builder.
     * @return false si un dato es incorreto, lo contrario true
     *
     * @author Matias Barrientos
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

    /**
     * Valida la seccion de localizacion
     *
     *  @return true si los campos son validos, en caso contrario false
     *
     * @author Matias Barrientos
     */
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

    /**
     * Valida la seccion de contacto
     *
     *  @return true si los campos son validos, en caso contrario false
     *
     * @author Matias Barrientos
     */
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

    /**
     * Valida que el campo no sea vacio
     *
     *  @return true si el campo es valido, en caso contrario false
     *
     * @author Matias Barrientos
     */
    private boolean validacionIsEmpty(StringProperty property, String errorMessage) {
        if (property.isEmpty().get() || property.isNull().get()) {
            router.showError(errorMessage);
            return false;
        }
        return true;
    }

    public void bindEditProperty() {
        if (!(saveStrategy instanceof EditTrabajadorStategy)) return;

        EditTrabajadorStategy editState = (EditTrabajadorStategy) saveStrategy;

        if (editState == null || editState.getOld() == null) return;

        rut.set(editState.getOld().getRut());
        name.set(editState.getOld().getNombre());
        lastName.set(editState.getOld().getApellido());
        speciality.set(editState.getOld().getEspecialidad().getNombre());
        birthday.setValue(editState.getOld().getFechaNacimiento());

        address.set(editState.getOld().getLocalizacion().getDireccion());
        zip.set(editState.getOld().getLocalizacion().getCodigoPostal());
        country.set(editState.getOld().getLocalizacion().getPais());
        city.set(editState.getOld().getLocalizacion().getCiudad());
        state.set(editState.getOld().getLocalizacion().getEstado());

        telephone.setValue(editState.getOld().getTelefono());
        email.setValue(editState.getOld().getCorreoElectronico());
    }

    public void changeStategy(SaveStrategy<Trabajador> strategy) {
        this.saveStrategy = strategy;
        view.loadView();
    }

    public void setModel(Constructora model) {
        this.model = model;
    }

    public void setView(TrabajadorView view) {
        this.view = view;
    }

    public void setRouter(TrabajadorRouter router) {
        this.router = router;
    }

    public void setDelegate(EditTrabajadorDelegate delegate) {
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
