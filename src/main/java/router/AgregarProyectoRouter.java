package router;

import base.Injectable;
import controller.AgregarProyectoController;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.StageStyle;
import model.Constructora;
import view.AgregarProyectoView;

/**
 * @author Matias Zuñiga
 * Se encarga de pasar entre diferentes vistas de AgregarTrabajador
 */
public class AgregarProyectoRouter {

    /**
     * Funcion que crea la vista para agregar el proyecto.
     * @param model El modelo de la vista.
     * @return Vista del proyecto.
     *
     * @author Matias Zúñiga
     */
    public static AgregarProyectoView create(Constructora model){
        AgregarProyectoView agregarProyectoView = Injectable.find(AgregarProyectoView.class);
        AgregarProyectoRouter agregarProyectoRouter = new AgregarProyectoRouter();
        AgregarProyectoController agregarProyectoController = new AgregarProyectoController(agregarProyectoView, model, agregarProyectoRouter);
        agregarProyectoView.setController(agregarProyectoController);

        return agregarProyectoView;
    }
    /**
     * @param mensaje texto que se expondra en la alerta.
     * @return una ventana de tipo Warning.
     * @author Matías Zúñiga
     */
    public Alert showWarning(String mensaje){
        Alert alert = new Alert(Alert.AlertType.WARNING, mensaje, ButtonType.OK, ButtonType.CANCEL);
        alert.initStyle(StageStyle.UTILITY);
        return alert;
    }
    /**
     * @param mensaje texto que se expondra en la alerta.
     * @return una ventana de tipo alerta.
     * @author Matías Zúñiga
     */
    public Alert showAlert(String mensaje){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(mensaje);
        alert.initStyle(StageStyle.UTILITY);
        return alert;
    }
    /**
     * @param mensaje texto que se expondra en la alerta.
     * @return una ventana de tipo información.
     * @author Matías Zúñiga
     */
    public Alert showInformation(String mensaje, TextField nombreP){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.setHeaderText(null);
        alert.setTitle("proyecto " + nombreP.getText());
        alert.setContentText(mensaje);
        alert.showAndWait();
        return alert;
    }

}
