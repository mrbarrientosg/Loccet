package router;

import base.Injectable;
import controller.DetalleTrabajadorController;
import delegate.SaveTrabajadorDelegate;
import model.Trabajador;
import view.DetalleTrabajadorView;
import view.ListaHorarioView;

public class DetalleTrabajadorRouter {

    public static DetalleTrabajadorView create(String rut, SaveTrabajadorDelegate delegate) {
        DetalleTrabajadorView view = Injectable.find(DetalleTrabajadorView.class);
        DetalleTrabajadorController controller = Injectable.find(DetalleTrabajadorController.class);

        ListaHorarioView listaHorarioView = ListaHorarioRouter.create(rut);

        view.setController(controller);
        view.setListaHorarioView(listaHorarioView);

        controller.setView(view);
        controller.setRutTrabajador(rut);
        controller.setDelegate(delegate);

        return view;
    }
}
