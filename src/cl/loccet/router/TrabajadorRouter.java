package cl.loccet.router;

import cl.loccet.base.Injectable;
import cl.loccet.controller.TrabajadorController;
import cl.loccet.model.Constructora;
import cl.loccet.model.Trabajador;
import cl.loccet.state.AddTrabajadorStrategy;
import cl.loccet.state.EditTrabajadorStategy;
import cl.loccet.view.TrabajadorView;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * @author Matias Barrientos
 */
public class TrabajadorRouter {

    /**
     * Funcion que crea la vista para editar el trabajador
     * @param model El modelo de la vista
     * @param old Caso en que se quiera editar el trabajador
     * @return Vista del trabajador
     */
    public static TrabajadorView create(Constructora model, Trabajador old) {
        TrabajadorView view = Injectable.find(TrabajadorView.class);

        TrabajadorRouter router = new TrabajadorRouter();

        EditTrabajadorStategy stategy = new EditTrabajadorStategy(model, old);

        TrabajadorController controller = Injectable.find(TrabajadorController.class);

        controller.setView(view);
        controller.setModel(model);
        controller.setRouter(router);

        view.setController(controller);

        controller.changeStategy(stategy);

        return view;
    }

    /**
     * Funcion que crea la vista para agregar el trabajador
     * @param model El modelo de la vista
     * @return Vista del trabajador
     */
    public static TrabajadorView create(Constructora model) {
        TrabajadorView view = Injectable.find(TrabajadorView.class);

        TrabajadorRouter router = new TrabajadorRouter();

        AddTrabajadorStrategy stategy = new AddTrabajadorStrategy(model);

        TrabajadorController controller = Injectable.find(TrabajadorController.class);

        controller.setView(view);
        controller.setModel(model);
        controller.setRouter(router);

        view.setController(controller);

        controller.changeStategy(stategy);

        return view;
    }

    public void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.setTitle("Error");
        alert.showAndWait();
    }
}
