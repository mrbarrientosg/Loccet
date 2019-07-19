package router;

import base.Injectable;
import controller.TrabajadorController;
import delegate.SaveTrabajadorDelegate;
import model.Constructora;
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
    public static TrabajadorView create(Constructora model, SaveTrabajadorDelegate delegate) {
        TrabajadorView view = Injectable.find(TrabajadorView.class);

        TrabajadorController controller = Injectable.find(TrabajadorController.class);

        controller.setView(view);
        controller.setModel(model);
        controller.setDelegate(delegate);

        view.setController(controller);

        return view;
    }

}
