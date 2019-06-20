package router;

import base.Injectable;
import controller.TrabajadorController;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Constructora;
import model.Trabajador;
import delegate.EditTrabajadorDelegate;
import view.TrabajadorView;

/**
 * Clase router para la vista Trabajador
 *
 * @author Matias Barrientos
 */
public final class TrabajadorRouter {

    /**
     * Funcion que crea la vista para agregar el trabajador
     * @param model El modelo de la vista
     * @return Vista del trabajador
     *
     * @author Matias Barrientos
     */
    public static TrabajadorView create(Constructora model) {
        TrabajadorView view = Injectable.find(TrabajadorView.class);

        TrabajadorController controller = Injectable.find(TrabajadorController.class);

        controller.setView(view);
        controller.setModel(model);

        view.setController(controller);

        return view;
    }

}
