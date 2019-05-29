package router;

import base.Injectable;
import controller.ListaHorarioController;
import model.Trabajador;
import view.ListaHorarioView;

public final class ListaHorarioRouter {

    public static ListaHorarioView create(Trabajador model) {
        ListaHorarioView view = Injectable.find(ListaHorarioView.class);
        ListaHorarioController controller = Injectable.find(ListaHorarioController.class);

        view.setController(controller);

        controller.setModel(model);
        controller.setView(view);

        return view;
    }

}
