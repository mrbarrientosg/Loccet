package router;

import base.Injectable;
import controller.DetalleMaterialController;
import view.DetalleMaterialView;

/**
 * @author Sebastian Fuenzalida.
 */
public class DetalleMaterialRouter {
    /**
     *
     * @return vista detalle material
     */
    public static DetalleMaterialView create() {
        DetalleMaterialView view = Injectable.find(DetalleMaterialView.class);
        DetalleMaterialRouter router = new DetalleMaterialRouter();
        DetalleMaterialController controller = Injectable.find(DetalleMaterialController.class);
        view.setController(controller);
        view.setRouter(router);

        controller.setView(view);
        return view;
    }
}