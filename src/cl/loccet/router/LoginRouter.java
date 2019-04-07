package cl.loccet.router;

import cl.loccet.base.Injectable;
import cl.loccet.controller.LoginController;
import cl.loccet.model.Constructora;
import cl.loccet.view.HomeView;
import cl.loccet.view.LoginView;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class LoginRouter {

    public static LoginView create() {
        LoginView view = Injectable.find(LoginView.class);
        LoginRouter router = new LoginRouter();
        LoginController controller = new LoginController(view, router);

        view.setController(controller);

        return view;
    }

    public Alert showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.setTitle("Error");
        alert.showAndWait();
        return alert;
    }

    public HomeView showHome(Constructora model) {
        HomeView home = HomeRouter.create(model);
        home.window().withResizable(true).show();
        return home;
    }
}
