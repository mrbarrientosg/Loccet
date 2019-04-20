package cl.loccet.router;

import cl.loccet.base.Injectable;
import cl.loccet.controller.HorarioController;
import cl.loccet.controller.LoginController;
import cl.loccet.model.Proyecto;
import cl.loccet.model.Trabajador;
import cl.loccet.state.AddHorarioDelegate;
import cl.loccet.view.HorarioView;
import cl.loccet.view.LoginView;

public class HorarioRouter {

    public static HorarioView create(Proyecto proyecto, Trabajador trabajador) {
        HorarioView view = Injectable.find(HorarioView.class);
        //HorarioRouter router = new HorarioRouter();
        HorarioController controller = new HorarioController(view, proyecto, trabajador);

        view.setController(controller);

        return view;
    }
}
