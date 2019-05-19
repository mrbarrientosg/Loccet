package router;

import base.Injectable;
import controller.ProyectoController;
import view.ProyectoView;

public class ProyectoRouter {

    public static ProyectoView create() {
        ProyectoView pView = Injectable.find(ProyectoView.class);
        ProyectoRouter pRouter = new ProyectoRouter();
        ProyectoController controller = Injectable.find(ProyectoController.class);

        pView.setController(controller);
        pView.setRouter(pRouter);

        controller.setView(pView);

        return pView;
    }

}
