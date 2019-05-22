package router;

import base.Injectable;
import controller.BuscarTrabajadorController;
import util.SearchEmployeeDelegate;
import view.BuscarTrabajadorView;

public class BuscarTrabajadorRouter {

    public static BuscarTrabajadorView create(SearchEmployeeDelegate delegate) {
        BuscarTrabajadorView view = Injectable.find(BuscarTrabajadorView.class);
        BuscarTrabajadorController controller = new BuscarTrabajadorController();

        controller.setView(view);
        controller.setDelegate(delegate);

        view.setController(controller);

        return view;
    }
}
