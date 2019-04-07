package cl.loccet.controller;
import cl.loccet.model.InventarioMaterial;
import cl.loccet.router.InventarioMaterialRouter;
import cl.loccet.view.InventarioMaterialView;


/**
 *Clase manejadora de las funciones de la vista inventario.
 *
 * @author Sebastian Fuenzalida.
 */


public class InventarioMaterialController {

    private InventarioMaterialView view;

    private InventarioMaterialRouter router;

    private InventarioMaterial model;


    public InventarioMaterialController(InventarioMaterialView view, InventarioMaterial model, InventarioMaterialRouter router) {
        this.view = view;
        this.model = model;
        this.router = router;
    }
}
