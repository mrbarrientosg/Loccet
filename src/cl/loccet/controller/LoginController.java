package cl.loccet.controller;

import cl.loccet.base.Controller;
import cl.loccet.model.Constructora;
import cl.loccet.model.Especialidades;
import cl.loccet.model.Trabajador;
import cl.loccet.router.LoginRouter;
import cl.loccet.util.ValidationResult;
import cl.loccet.view.LoginView;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoginController extends Controller {

    private final LoginView view;

    private final LoginRouter router;

    private StringProperty rutProperty;

    private StringProperty passwordProperty;

    public LoginController(LoginView view, LoginRouter router) {
        this.view = view;
        this.router = router;
    }

    public void loginUser() {
        // TODO: Validar el RUT y la contraseña
        System.out.println(rutProperty.get());
        System.out.println(passwordProperty.get());

        view.close();

        // TODO: implementar el controlador para poder gestionar la constructora
        Constructora c = new Constructora("RUT","NOMBRE");
        c.agregarTrabajador(new Trabajador.Builder().rut("19").nombre("Matias").especialidad(Especialidades.getInstance().get("Pintor")).build());

        router.showHome(c);
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
}
