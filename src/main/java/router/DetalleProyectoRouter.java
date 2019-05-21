package router;

import base.Injectable;
import controller.DetalleProyectoController;
import model.Proyecto;
import view.DetalleProyectoView;

public class DetalleProyectoRouter {

    public static DetalleProyectoView create(Proyecto proyecto) {
        DetalleProyectoView detalleProyectoView = Injectable.find(DetalleProyectoView.class);
        DetalleProyectoController detalleProyectoController = Injectable.find(DetalleProyectoController.class);

        detalleProyectoView.setController(detalleProyectoController);
        detalleProyectoController.setView(detalleProyectoView);
        detalleProyectoController.setProyecto(proyecto);

        return detalleProyectoView;
    }
}
