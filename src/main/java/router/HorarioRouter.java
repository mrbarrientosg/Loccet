package router;

import base.Injectable;
import controller.CrearHorarioController;
import delegate.AddHorarioDelegate;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.StageStyle;
import model.Trabajador;
import view.CrearHorarioView;

public final class HorarioRouter {

    public static CrearHorarioView create(String rut, AddHorarioDelegate delegate) {
        CrearHorarioView view = Injectable.find(CrearHorarioView.class);

        CrearHorarioController controller = Injectable.find(CrearHorarioController.class);

        view.setController(controller);

        controller.setDelegate(delegate);
        controller.setRutTrabajador(rut);
        controller.setView(view);

        return view;
    }
}
