package cl.loccet.controller;

import cl.loccet.base.Controller;
import cl.loccet.model.Constructora;
import cl.loccet.model.ItemRegistro;
import cl.loccet.model.Proyecto;
import cl.loccet.model.RegistroAvance;
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

        Proyecto p = new Proyecto(0, 0.0, 0.0, null, null, null);

        RegistroAvance r = new RegistroAvance();

        r.agregarRegistro(new ItemRegistro("", 0, 10));
        r.agregarRegistro(new ItemRegistro("", 0, 25));
        r.agregarRegistro(new ItemRegistro("", 0, 42));
        r.agregarRegistro(new ItemRegistro("", 0, 67.2));

        p.agregarRegistroAvance(r);

        c.agregarProyecto(p);

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
