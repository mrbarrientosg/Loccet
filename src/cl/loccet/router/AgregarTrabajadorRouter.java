package cl.loccet.router;

import cl.loccet.base.Injectable;
import cl.loccet.controller.AgregarTrabajadorController;
import cl.loccet.controller.HomeController;
import cl.loccet.model.Constructora;
import cl.loccet.view.AgregarTrabajadorView;
import cl.loccet.view.HomeView;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class AgregarTrabajadorRouter {

    public static AgregarTrabajadorView create(Constructora model) {
        AgregarTrabajadorView view = Injectable.find(AgregarTrabajadorView.class);
        AgregarTrabajadorRouter router = new AgregarTrabajadorRouter();
        AgregarTrabajadorController controller = new AgregarTrabajadorController(view, model, router);

        view.setController(controller);

        return view;
    }

    public void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.setTitle("Error");
        alert.showAndWait();
    }
}
