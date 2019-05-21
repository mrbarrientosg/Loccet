package router;

import base.Injectable;
import controller.LoginController;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;
import model.Constructora;
import view.HomeView;
import view.LoginView;
import view.TableroView;

public final class LoginRouter {

    public static LoginView create() {
        LoginView view = Injectable.find(LoginView.class);
        LoginRouter router = new LoginRouter();
        LoginController controller = new LoginController();

        controller.setView(view);

        view.setController(controller);
        view.setRouter(router);

        return view;
    }

    public void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.setTitle("Error");
        alert.showAndWait();
    }

    public HomeView showHome(Constructora model) {
        HomeView home = HomeRouter.create(model);
        home.window().withResizable(true).show();
        return home;
    }

    public TableroView showTablero() {
        TableroView tableroView = TableroRouter.create();
        return tableroView;
    }
}
