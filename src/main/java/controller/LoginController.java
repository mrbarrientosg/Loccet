package controller;

import base.Controller;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import router.LoginRouter;
import util.FakeData;
import util.NodeUtils;
import view.LoginView;
import view.TableroView;

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

        //view.close();

        // TODO: El modelo deberia retornar los datos de la constructora

        TableroView tableroView = router.showTablero(FakeData.createFakeData());

        //NodeUtils.replaceWith(view.getRoot(), tableroView.getRoot(), true, true, null);

        //view.getCurrentStage().setScene(tableroView.getRoot().getScene());
        //view.getRoot().getScene().setRoot(tableroView.getRoot());

        view.replaceWith(tableroView, true, true);

        router.showTablero(FakeData.createFakeData());
    }

    private void minimize(ActionEvent actionEvent) {
        getPrimaryStage().setIconified(true);
    }

    private void exit(ActionEvent actionEvent) {
        Platform.exit();
        System.exit(0);
    }
}
