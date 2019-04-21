package controller;

import base.Controller;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import router.LoginRouter;
import util.FakeData;
import view.LoginView;

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

        router.showHome(FakeData.createFakeData());
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
