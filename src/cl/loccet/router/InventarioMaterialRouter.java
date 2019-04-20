package cl.loccet.router;
import cl.loccet.base.Injectable;
import cl.loccet.controller.InventarioMaterialController;
import cl.loccet.model.InventarioMaterial;
import cl.loccet.view.InventarioMaterialView;


/**
 *
 * @author Sebastian Fuenzalida.
 */

public class InventarioMaterialRouter {

    public static InventarioMaterialView create(InventarioMaterial model) {
        InventarioMaterialView inventarioView = Injectable.find(InventarioMaterialView.class);
        InventarioMaterialRouter inventarioRouter = new InventarioMaterialRouter();
        InventarioMaterialController inventarioController = new InventarioMaterialController(inventarioView, model, inventarioRouter);

        inventarioView.setController(inventarioController);

        return inventarioView;
    }


   /* public InventarioView showHome(Inventario model) {
        HomeView home = HomeRouter.create(model);
        home.window().show();
        return home;
    }*/
}