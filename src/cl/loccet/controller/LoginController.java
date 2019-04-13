package cl.loccet.controller;

import cl.loccet.base.Controller;
import cl.loccet.model.Constructora;
import cl.loccet.router.LoginRouter;
import cl.loccet.util.ValidationResult;
import cl.loccet.view.LoginView;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LoginController extends Controller {

    private final LoginView view;

    private final LoginRouter router;

    private StringProperty rutProperty;

    private StringProperty passwordProperty;

    private BooleanProperty contratista;

    public LoginController(LoginView view, LoginRouter router) {
        this.view = view;
        this.router = router;
        contratista = new SimpleBooleanProperty();
    }

    public void loginUser() {
        // TODO: Validar el RUT y la contraseña
        System.out.println(rutProperty.get());
        System.out.println(passwordProperty.get());

        view.close();

        // TODO: implementar el controlador para poder gestionar la constructora
        Constructora c = new Constructora("RUT","NOMBRE");

        router.showHome(c);


    }

    public ObservableList<String> fetchProyectos() {
        ObservableList<String> fake = FXCollections.observableArrayList();
        fake.add("Nuevo barrio Santiago");
        fake.add("Nuevo IBC con dormitorios");
        fake.add("Edificio nueva blanca");
        return fake;
    }

    public StringProperty rutProperty() {
        if (rutProperty == null)
            rutProperty = new SimpleStringProperty();
        return rutProperty;
    }

    public StringProperty passwordProperty() {
        if (passwordProperty == null)
            passwordProperty = new SimpleStringProperty();
        return passwordProperty;
    }

    public BooleanProperty contratistaProperty() {
        return contratista;
    }
}
