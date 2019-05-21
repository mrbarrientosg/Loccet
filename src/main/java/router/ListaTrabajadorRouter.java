package router;

import base.Injectable;
import controller.ListaTrabajadorController;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Constructora;
import model.Proyecto;
import view.ListaTrabajadorView;

public final class ListaTrabajadorRouter {

    public static ListaTrabajadorView create(Proyecto model) {
        ListaTrabajadorView view = Injectable.find(ListaTrabajadorView.class);
        ListaTrabajadorController controller = new ListaTrabajadorController(view,  model);

        view.setController(controller);

        return view;
    }

    public Alert showWarning(String message) {
        Alert warning = new Alert(Alert.AlertType.WARNING, message, ButtonType.YES, ButtonType.CANCEL);
        warning.setTitle("Alerta");
        return warning;
    }
}
