package cl.loccet.controller;

import cl.loccet.base.Controller;
import cl.loccet.model.Constructora;
import cl.loccet.model.Trabajador;
import cl.loccet.router.AgregarTrabajadorRouter;
import cl.loccet.router.HomeRouter;
import cl.loccet.view.AgregarTrabajadorView;
import cl.loccet.view.HomeView;

import java.util.ArrayList;

public class AgregarTrabajadorController extends Controller {

    private final AgregarTrabajadorView view;

    private final Constructora model;

    private final AgregarTrabajadorRouter router;

    public AgregarTrabajadorController(AgregarTrabajadorView view, Constructora model, AgregarTrabajadorRouter router) {
        this.view = view;
        this.model = model;
        this.router = router;
    }

    public void guardarTrabajador(Trabajador t) {
        LOGGER.info(t.toString());

        // Validar

        boolean result = model.agregarTrabajador(t);

        if (result) {
            // Agregado exitosamente
        } else {
            // Error no se pudo agregar (Mostrar alerta)
        }
    }

}
