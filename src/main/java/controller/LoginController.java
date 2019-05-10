package controller;

import base.Controller;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import router.LoginRouter;
import util.FakeData;
import view.LoginView;

public final class LoginController extends Controller {

    private final LoginView view;

    private final LoginRouter router;

    public LoginController(LoginView view, LoginRouter router) {
        this.view = view;
        this.router = router;

        view.setLoginButtonAction(this::loginUser);
        view.setExitButtonAction(this::exit);
        view.setMinimizeButtonAction(this::minimize);
    }

    /**
     * Inicia sesion
     */
    private void loginUser(ActionEvent actionEvent) {
        // TODO: Falta implementar el modelo para que haga el proceso de login con el API
        if (view.getUsername().isEmpty() || view.getPassword().isEmpty()) {
            router.showError("Complete todos los campos").showAndWait();
            return;
        } else if (!view.getUsername().equals("123") || !view.getPassword().equals("123")) {
            router.showError("Usuario o Contraseña incorrecta").showAndWait();
            return;
        }

        view.close();

        // TODO: El modelo deberia retornar los datos de la constructora

        router.showHome(FakeData.createFakeData());
    }

    private void minimize(ActionEvent actionEvent) {
        getPrimaryStage().setIconified(true);
    }

    private void exit(ActionEvent actionEvent) {
        Platform.exit();
        System.exit(0);
    }
}
