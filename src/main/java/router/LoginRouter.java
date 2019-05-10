package router;

import base.Injectable;
import controller.LoginController;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.StageStyle;
import model.Constructora;
import view.HomeView;
import view.LoginView;
import view.TableroView;

public final class LoginRouter {

    public static LoginView create() {
        LoginView view = Injectable.find(LoginView.class);
        LoginRouter router = new LoginRouter();
        LoginController controller = new LoginController(view, router);

        return view;
    }

    public Alert showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.setTitle("Error");
        return alert;
    }

    public HomeView showHome(Constructora model) {
        HomeView home = HomeRouter.create(model);
        home.window().withResizable(true).show();
        return home;
    }

    public TableroView showTablero(Constructora model) {
        TableroView tableroView = TableroRouter.create(model);
        tableroView.window()
                .withStyle(StageStyle.TRANSPARENT)
                .withResizable(true)
                .show();
        return tableroView;
    }
}
