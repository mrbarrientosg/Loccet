package controller;

import base.Controller;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Rectangle;
import router.HomeRouter;
import router.LoginRouter;
import util.FakeData;
import view.HomeView;
import view.LoginView;

public final class LoginController extends Controller {

    private final LoginView view;

    private final LoginRouter router;

    private StringProperty rutProperty;

    private StringProperty passwordProperty;

    public LoginController(LoginView view, LoginRouter router) {
        this.view = view;
        this.router = router;
    }

    /**
     * Inicia sesion
     */
    public void loginUser() {
        // TODO: Validar el RUT y la contraseña
        if (rutProperty.isEmpty().get() || passwordProperty.isEmpty().get()) {
            router.showError("Complete todos los campos").showAndWait();
            return;
        } else if (!rutProperty.get().equals("123") || !passwordProperty.get().equals("123")) {
            router.showError("Usuario o Contraseña incorrecta").showAndWait();
            return;
        }

        System.out.println(rutProperty.get());
        System.out.println(passwordProperty.get());

        //view.close();

        // TODO: implementar el controlador para poder gestionar la constructora

        HomeView homeView = HomeRouter.create(FakeData.createFakeData());
        view.replaceWith(homeView.getClass(), true, true);
        homeView.getCurrentStage().setResizable(true);

        //router.showHome(FakeData.createFakeData());
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
