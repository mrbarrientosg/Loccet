package cl.loccet.controller;

import cl.loccet.base.Controller;
import cl.loccet.model.Constructora;
import cl.loccet.model.Trabajador;
import cl.loccet.router.AgregarTrabajadorRouter;
import cl.loccet.router.HomeRouter;
import cl.loccet.util.ValidationResult;
import cl.loccet.util.Validator;
import cl.loccet.view.AgregarTrabajadorView;
import cl.loccet.view.HomeView;

import java.util.ArrayList;
import java.util.stream.Collectors;

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
        ValidationResult validation = Validator.of(t)
                .validate(Trabajador::getRut, text -> !text.isEmpty(), "Debe ingresar el RUT")
                .validate(Trabajador::getNombre, text -> !text.isEmpty(), "Debe ingresar el Nombre")
                .validate(Trabajador::getApellido, text -> !text.isEmpty(), "Debe ingresar el Apellido")
                .result();

        if (!validation.isValid()) {
            String message = validation.getMessages()
                    .stream()
                    .map(StringBuilder::new)
                    .map(stringBuilder -> stringBuilder.insert(0, "- ").toString())
                    .collect(Collectors.joining("\n"));

            router.showError(message);
            
            return;
        }

        boolean result = true;
                //model.agregarTrabajador(t);

        if (result) {
            // Agregado exitosamente
        } else {
            // Error no se pudo agregar (Mostrar alerta)
        }
    }

}
