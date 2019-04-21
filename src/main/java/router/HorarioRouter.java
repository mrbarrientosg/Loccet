package router;

import base.Injectable;
import controller.HorarioController;
import model.Proyecto;
import model.Trabajador;
import view.HorarioView;

public class HorarioRouter {

    public static HorarioView create(Proyecto proyecto, Trabajador trabajador) {
        HorarioView view = Injectable.find(HorarioView.class);
        //HorarioRouter router = new HorarioRouter();
        HorarioController controller = new HorarioController(view, proyecto, trabajador);

        view.setController(controller);

        return view;
    }
}
