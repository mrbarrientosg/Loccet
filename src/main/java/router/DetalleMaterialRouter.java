package router;

import base.Injectable;
import controller.DetalleMaterialController;
import model.Material;
import view.DetalleMaterialView;

/**
 * @author Sebastian Fuenzalida.
 */
public class DetalleMaterialRouter {
    /**
     *
     * @return vista detalle material
     */
    public static DetalleMaterialView create(Material model) {
        DetalleMaterialView view = Injectable.find(DetalleMaterialView.class);
        DetalleMaterialRouter router = new DetalleMaterialRouter();
        DetalleMaterialController controller = Injectable.find(DetalleMaterialController.class);
        view.setController(controller);
        view.setRouter(router);
        controller.setView(view);
        controller.setModel(model);

        return view;
    }
}