package cl.loccet.router;

import cl.loccet.base.Injectable;
import cl.loccet.controller.ListaTrabajadorController;
import cl.loccet.model.Constructora;
import cl.loccet.view.ListaTrabajadorView;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class ListaTrabajadorRouter {

    public static ListaTrabajadorView create(Constructora model) {
        ListaTrabajadorView view = Injectable.find(ListaTrabajadorView.class);
        ListaTrabajadorRouter router = new ListaTrabajadorRouter();
        ListaTrabajadorController controller = new ListaTrabajadorController(view,  model, router);

        view.setController(controller);

        return view;
    }

    public Alert showWarning(String message) {
        Alert warning = new Alert(Alert.AlertType.WARNING, message, ButtonType.YES, ButtonType.CANCEL);
        warning.setTitle("Alerta");
        return warning;
    }
}
