package router;

import base.Injectable;
import controller.DetalleProyectoController;
import delegate.SaveProyectoDelegate;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Proyecto;
import view.DetalleProyectoView;
import view.InventarioMaterialView;
import view.ListaTrabajadorView;

public class DetalleProyectoRouter {

    public static DetalleProyectoView create(Proyecto proyecto, SaveProyectoDelegate delegate) {
        DetalleProyectoView detalleProyectoView = Injectable.find(DetalleProyectoView.class);
        DetalleProyectoRouter router = new DetalleProyectoRouter();
        DetalleProyectoController detalleProyectoController = Injectable.find(DetalleProyectoController.class);

        ListaTrabajadorView listaTrabajadorView = ListaTrabajadorRouter.create(proyecto);

        InventarioMaterialView inventarioMaterialView = InventarioMaterialRouter.create(proyecto.getInventarioMaterial(), proyecto);

        detalleProyectoView.setListaTrabajadorView(listaTrabajadorView);
        detalleProyectoView.setInventarioMaterialView(inventarioMaterialView);

        detalleProyectoView.setRouter(router);
        detalleProyectoView.setController(detalleProyectoController);

        detalleProyectoController.setView(detalleProyectoView);
        detalleProyectoController.setModel(proyecto);
        detalleProyectoController.setDelegate(delegate);

        return detalleProyectoView;
    }


    public void showSaveAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Los datos se han guardado correctamente", ButtonType.OK);
        alert.showAndWait();
    }
}
