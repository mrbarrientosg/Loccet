package router;

import base.Injectable;
import controller.ListaHorarioController;
import model.Trabajador;
import view.ListaHorarioView;

public final class ListaHorarioRouter {

    public static ListaHorarioView create(Trabajador model, boolean adding) {
        ListaHorarioView view = Injectable.find(ListaHorarioView.class);
        //ListaHorarioRouter router = new ListaHorarioRouter();
        ListaHorarioController controller = new ListaHorarioController(view, model);

        view.setController(controller);

        return view;
    }

    public static ListaHorarioView create(Trabajador model) {
        return create(model, false);
    }
}
