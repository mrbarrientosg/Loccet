package router;

import base.Injectable;
import controller.CrearTrabajadorController;
import delegate.SaveTrabajadorDelegate;
import model.Constructora;
import view.CrearTrabajadorView;

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
    public static CrearTrabajadorView create(Constructora model, SaveTrabajadorDelegate delegate) {
        CrearTrabajadorView view = Injectable.find(CrearTrabajadorView.class);

        CrearTrabajadorController controller = Injectable.find(CrearTrabajadorController.class);

        controller.setView(view);
        controller.setModel(model);
        controller.setDelegate(delegate);

        view.setController(controller);

        return view;
    }

}
