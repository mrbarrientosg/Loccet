package cl.loccet.router;
import cl.loccet.base.Injectable;
import cl.loccet.controller.InventarioController;
import cl.loccet.model.Inventario;
import cl.loccet.view.InventarioView;


public class InventarioRouter {

    public static InventarioView create(Inventario model) {
        InventarioView inventarioView = Injectable.find(InventarioView.class);
        InventarioRouter inventarioRouter = new InventarioRouter();
        InventarioController inventarioController = new InventarioController(inventarioView, model, inventarioRouter);

        inventarioView.setController(inventarioController);

        return inventarioView;
    }



   /* public InventarioView showHome(Inventario model) {
        HomeView home = HomeRouter.create(model);
        home.window().show();
        return home;
    }*/
}