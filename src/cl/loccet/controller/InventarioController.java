package cl.loccet.controller;
import cl.loccet.model.Inventario;
import cl.loccet.router.InventarioRouter;
import cl.loccet.view.InventarioView;


public class InventarioController {

    private InventarioView view;

    private InventarioRouter router;

    private Inventario model;


    public InventarioController(InventarioView view,Inventario model,InventarioRouter router) {
        this.view = view;
        this.model = model;
        this.router = router;
    }
}
