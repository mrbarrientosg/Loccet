package router;


import base.Injectable;
import controller.InventarioMaterialController;
import model.InventarioMaterial;
import view.InventarioMaterialView;

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