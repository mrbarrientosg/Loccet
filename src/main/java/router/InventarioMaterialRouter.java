package router;


import base.Injectable;
import controller.InventarioMaterialController;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.InventarioMaterial;
import model.Proyecto;
import view.InventarioMaterialView;

/**
 *
 * @author Sebastian Fuenzalida.
 */

public final class InventarioMaterialRouter {

    public static InventarioMaterialView create(InventarioMaterial model, Proyecto proyecto) {
        InventarioMaterialView inventarioView = Injectable.find(InventarioMaterialView.class);
        InventarioMaterialRouter inventarioRouter = new InventarioMaterialRouter();
        InventarioMaterialController inventarioController = new InventarioMaterialController(inventarioView, model, inventarioRouter);

        inventarioView.setController(inventarioController);
        inventarioController.setProyecto(proyecto);

        return inventarioView;
    }

    public Alert showWarning(String header, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING, message, ButtonType.OK);
        alert.setTitle("Warning");
        alert.setHeaderText(header);
        return alert;
    }

}