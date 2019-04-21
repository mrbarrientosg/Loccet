package router;


import base.Injectable;
import controller.ListaHorarioController;
import model.Trabajador;
import view.ListaHorarioView;

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
