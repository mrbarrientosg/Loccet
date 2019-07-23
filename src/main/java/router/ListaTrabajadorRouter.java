package router;

import base.Injectable;
import controller.ListaTrabajadorController;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Constructora;
import model.Proyecto;
import view.ListaTrabajadorView;

public final class ListaTrabajadorRouter {

    public static ListaTrabajadorView create(String idProyecto) {
        ListaTrabajadorView view = Injectable.find(ListaTrabajadorView.class);
        ListaTrabajadorController controller = Injectable.find(ListaTrabajadorController.class);

        view.setController(controller);

        controller.setIdProyecto(idProyecto);
        controller.setView(view);

        return view;
    }
}
