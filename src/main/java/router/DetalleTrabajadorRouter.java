package router;

import base.Injectable;
import controller.DetalleTrabajadorController;
import model.Trabajador;
import view.DetalleTrabajadorView;
import view.ListaHorarioView;

public class DetalleTrabajadorRouter {

    public static DetalleTrabajadorView create(Trabajador model) {
        DetalleTrabajadorView view = Injectable.find(DetalleTrabajadorView.class);
        DetalleTrabajadorController controller = Injectable.find(DetalleTrabajadorController.class);

        ListaHorarioView listaHorarioView = ListaHorarioRouter.create(model);

        view.setController(controller);
        view.setListaHorarioView(listaHorarioView);

        controller.setView(view);
        controller.setModel(model);


        return view;
    }
}
