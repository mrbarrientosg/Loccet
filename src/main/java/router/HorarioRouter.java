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

    public static CrearHorarioView create(Trabajador trabajador, AddHorarioDelegate delegate) {
        CrearHorarioView view = Injectable.find(CrearHorarioView.class);
        HorarioRouter router = new HorarioRouter();
        CrearHorarioController controller = Injectable.find(CrearHorarioController.class);

        view.setController(controller);

        controller.setDelegate(delegate);
        controller.setTrabajador(trabajador);
        controller.setView(view);
        controller.setRouter(router);

        return view;
    }

    public Alert showWarning(String mensaje){
        Alert alert = new Alert(Alert.AlertType.WARNING, mensaje, ButtonType.OK, ButtonType.CANCEL);
        alert.initStyle(StageStyle.UTILITY);
        return alert;
    }
}
