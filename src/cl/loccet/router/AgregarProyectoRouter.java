package cl.loccet.router;

import cl.loccet.base.Injectable;
import cl.loccet.controller.AgregarProyectoController;
import cl.loccet.model.Proyecto;
import cl.loccet.view.AgregarProyectoView;

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
}
