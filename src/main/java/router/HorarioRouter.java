package router;

import base.Injectable;
import controller.HorarioController;
import delegate.AddHorarioDelegate;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.StageStyle;
import model.Proyecto;
import model.Trabajador;
import view.HorarioView;

public final class HorarioRouter {

    public static HorarioView create(Trabajador trabajador, AddHorarioDelegate delegate) {
        HorarioView view = Injectable.find(HorarioView.class);
        HorarioRouter router = new HorarioRouter();
        HorarioController controller = Injectable.find(HorarioController.class);

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
