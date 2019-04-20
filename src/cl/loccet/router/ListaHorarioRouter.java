package cl.loccet.router;

import cl.loccet.base.Injectable;
import cl.loccet.controller.ListaHorarioController;
import cl.loccet.model.Trabajador;
import cl.loccet.state.AddHorarioDelegate;
import cl.loccet.view.ListaHorarioView;

public class ListaHorarioRouter {

    public static ListaHorarioView create(Trabajador model) {
        ListaHorarioView view = Injectable.find(ListaHorarioView.class);
        //ListaHorarioRouter router = new ListaHorarioRouter();
        ListaHorarioController controller = Injectable.find(ListaHorarioController.class);

        controller.setView(view);
        controller.setTrabajador(model);
        controller.setAdd(false);

        view.setController(controller);

        return view;
    }
}
