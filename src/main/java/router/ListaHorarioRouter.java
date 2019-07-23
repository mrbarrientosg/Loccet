package router;

import base.Injectable;
import controller.ListaHorarioController;
import model.Trabajador;
import view.ListaHorarioView;

public final class ListaHorarioRouter {

    public static ListaHorarioView create(String rut) {
        ListaHorarioView view = Injectable.find(ListaHorarioView.class);
        ListaHorarioController controller = Injectable.find(ListaHorarioController.class);

        view.setController(controller);

        controller.setRutTrabajador(rut);
        controller.setView(view);

        return view;
    }

}
