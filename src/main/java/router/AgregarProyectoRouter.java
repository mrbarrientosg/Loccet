package router;

import base.Injectable;
import controller.CrearProyectoController;
import delegate.SaveProyectoDelegate;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.StageStyle;
import view.CrearProyectoView;

/**
 * @author Matias Zuñiga
 * Se encarga de pasar entre diferentes vistas de AgregarTrabajador
 */
public final class AgregarProyectoRouter {

    /**
     * Funcion que crea la vista para agregar el proyecto.
     * @return Vista del proyecto.
     *
     * @author Matias Zúñiga
     */
    public static CrearProyectoView create(SaveProyectoDelegate delegate) {
        CrearProyectoView view = Injectable.find(CrearProyectoView.class);
        AgregarProyectoRouter router = new AgregarProyectoRouter();
        CrearProyectoController controller = Injectable.find(CrearProyectoController.class);

        view.setController(controller);
        view.setRouter(router);

        controller.setView(view);
        controller.setDelegate(delegate);

        return view;
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
