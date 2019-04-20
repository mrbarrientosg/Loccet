package cl.loccet.router;

import cl.loccet.base.Injectable;
import cl.loccet.controller.TrabajadorController;
import cl.loccet.model.Constructora;
import cl.loccet.model.Trabajador;
import cl.loccet.state.EditTrabajadorDelegate;
import cl.loccet.view.TrabajadorView;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * Clase router para la vista Trabajador
 *
 * @author Matias Barrientos
 */
public final class TrabajadorRouter {

    /**
     * Crea la vista para editar el trabajador y recibe un delegate para informar
     * la edicion de un trabajador
     * @param model El modelo del controlador
     * @param old Caso en que se quiera editar el trabajador
     * @param delegate Interfaz para indicar que se ha modificado un trabajador
     * @return Vista del trabajador
     *
     * @author Matias Barrientos
     */
    public static TrabajadorView create(Constructora model, Trabajador old, EditTrabajadorDelegate delegate) {
        TrabajadorView view = Injectable.find(TrabajadorView.class);

        TrabajadorRouter router = new TrabajadorRouter();

        TrabajadorController controller = Injectable.find(TrabajadorController.class);

        controller.setView(view);
        controller.setModel(model);
        controller.setRouter(router);
        controller.setDelegate(delegate);

        view.setController(controller);

        controller.setOldTrabajador(old);

        return view;
    }

    /**
     * Crea la vista para editar un trabajador
     * @param model El modelo del controlador
     * @param old Trabajaddor que quiere ser editado
     * @return Vista del trabajador
     *
     * @author Matias Barrientos
     */
    public static TrabajadorView create(Constructora model, Trabajador old) {
        return create(model, old, null);
    }

    /**
     * Funcion que crea la vista para agregar el trabajador
     * @param model El modelo de la vista
     * @return Vista del trabajador
     *
     * @author Matias Barrientos
     */
    public static TrabajadorView create(Constructora model) {
        return create(model, null, null);
    }

    /**
     * Muestra una alerta de error
     * @param message Mensaje que debe mostrar la alerta
     *
     * @author Matias Barrientos
     */
    public void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.setTitle("Error");
        alert.show();
    }
}
