package cl.loccet.router;

import cl.loccet.base.Injectable;
import cl.loccet.controller.AgregarTrabajadorController;
import cl.loccet.model.Constructora;
import cl.loccet.model.Trabajador;
import cl.loccet.view.AgregarTrabajadorView;

public class AgregarTrabajadorRouter {

    public static AgregarTrabajadorView create(Constructora model) {
        AgregarTrabajadorView view = Injectable.find(AgregarTrabajadorView.class);
        AgregarTrabajadorRouter router = new AgregarTrabajadorRouter();
        AgregarTrabajadorController controller = new AgregarTrabajadorController(view, model, router);

        view.setController(controller);

        return view;
    }

}
