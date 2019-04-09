package cl.loccet.controller;

import cl.loccet.base.Controller;
import cl.loccet.model.Proyecto;
import cl.loccet.router.AgregarProyectoRouter;
import cl.loccet.view.AgregarProyectoView;
import java.util.UUID;

/**
 * @author Matias Zuñiga
 * Se encarga de mostrar la información ingresada por el usuario en la vista AgregarProyecto
 */

public class AgregarProyectoController extends Controller {

    private AgregarProyectoView view;
    private AgregarProyectoRouter router;
    private Proyecto model;

    public AgregarProyectoController(AgregarProyectoView view, Proyecto model, AgregarProyectoRouter router){
        this.view = view;
        this.model = model;
        this.router = router;
    }

    public AgregarProyectoView getView() {
        return view;
    }

    public void setView(AgregarProyectoView view) {
        this.view = view;
    }

    public AgregarProyectoRouter getRouter() {
        return router;
    }

    public void setRouter(AgregarProyectoRouter router) {
        this.router = router;
    }

    public Proyecto getModel() {
        return model;
    }

    public void setModel(Proyecto model) {
        this.model = model;
    }

    /**
     * @return un string basandose en el nombre del proyecto
     * @author Matías Zúñiga
     */
    public  final int generarId() {
        String result = java.util.UUID.randomUUID().toString();
        result = result.replaceAll("-", "");
        result = result.replaceAll("[A-Za-z]","");
        result = result.substring(0, 7);
        int id = Integer.parseInt(result);
        return id;
    }


}
