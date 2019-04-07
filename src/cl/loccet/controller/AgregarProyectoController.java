package cl.loccet.controller;

import cl.loccet.base.Controller;
import cl.loccet.model.Proyecto;
import cl.loccet.router.AgregarProyectoRouter;
import cl.loccet.view.AgregarProyectoView;

public class AgregarProyectoController extends Controller {

    private AgregarProyectoView view;
    private AgregarProyectoRouter router;
    private Proyecto model;

    public AgregarProyectoController(AgregarProyectoView view, Proyecto model, AgregarProyectoRouter router){
        this.view = view;
        this.model = model;
        this.router = router;
    }

}
