package cl.loccet.router;

import cl.loccet.base.Injectable;
import cl.loccet.controller.AgregarProyectoController;
import cl.loccet.model.Proyecto;
import cl.loccet.view.AgregarProyectoView;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.StageStyle;

/**
 * @author Matias Zuñiga
 * Se encarga de pasar entre diferentes vistas de AgregarTrabajador
 */

public class AgregarProyectoRouter {

    public static AgregarProyectoView create(Proyecto model){
        AgregarProyectoView agregarProyectoView = Injectable.find(AgregarProyectoView.class);
        AgregarProyectoRouter agregarProyectoRouter = new AgregarProyectoRouter();
        AgregarProyectoController agregarProyectoController = new AgregarProyectoController(agregarProyectoView, model, agregarProyectoRouter);
        agregarProyectoView.setController(agregarProyectoController);

        return agregarProyectoView;
    }
    public Alert showWarning(String mensaje){
        Alert alert = new Alert(Alert.AlertType.WARNING, mensaje, ButtonType.OK, ButtonType.CANCEL);
        alert.initStyle(StageStyle.UTILITY);
        return alert;
    }
    public Alert showAlert(String mensaje){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(mensaje);
        alert.initStyle(StageStyle.UTILITY);
        return alert;
    }


}
