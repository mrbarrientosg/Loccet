package cl.loccet.controller;

import cl.loccet.base.Controller;
import cl.loccet.model.Constructora;
import cl.loccet.model.Proyecto;
import cl.loccet.router.AgregarProyectoRouter;
import cl.loccet.view.AgregarProyectoView;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * @author Matias Zuñiga
 * Se encarga de mostrar la información ingresada por el usuario en la vista AgregarProyecto
 */

public class AgregarProyectoController extends Controller {

    private AgregarProyectoView view;
    private AgregarProyectoRouter router;
    private Constructora model;

    public AgregarProyectoController(AgregarProyectoView view, Constructora model, AgregarProyectoRouter router){
        this.view = view;
        this.model = model;
        this.router = router;
    }

    /**
     * @return un string basandose en el nombre del proyecto
     * @author Matías Zúñiga
     */
    public final String generarId() {
        String result = java.util.UUID.randomUUID().toString();
        //result = result.replaceAll("-", "");
        //result = result.replaceAll("[A-Za-z]","");
        //result = result.substring(0, 32);
        return result;
    }

    public void presionarAceptar(TextField nombreP,TextField jefeP, TextField montoC,TextField cliente,TextField telefonoC,TextField mailC,TextField direccion,TextField ciudad,TextField estado,TextField pais,DatePicker fechaF,DatePicker fechaT){
        LOGGER.info("Nombre proyecto: " + nombreP.getText());
        LOGGER.info("Jefe proyecto: " + jefeP.getText());
        LOGGER.info("Cliente: " + cliente.getText());
        LOGGER.info("Presupuesto: " + montoC.getText());
        LOGGER.info("Mail Cliente: " + mailC.getText());
        LOGGER.info("Telefono Cliente: " + telefonoC.getText());
        LOGGER.info("Direccion: " + direccion.getText());
        LOGGER.info("Pais: " + pais.getText());
        LOGGER.info("Estado: " + estado.getText());
        LOGGER.info("Ciudad: " + ciudad.getText());
        System.out.println("Todos los campos estan llenos");
        Proyecto proyecto = new Proyecto.Builder(generarId(), nombreP.getText(),jefeP.getText(),Double.parseDouble(montoC.getText()),cliente.getText())
                .datosCliente(mailC.getText(),telefonoC.getText())
                .datosUbicacion(direccion.getText(),pais.getText(),ciudad.getText(),estado.getText())
                .fechaProyecto(fechaF.getValue(),fechaT.getValue())
                .build();
        model.agregarProyecto(proyecto);
        router.showInformation("Agregado satisfactoriamente", nombreP);
        //TODO: Aqui se cierra la vista
    }

    public Alert showAlert(String mensaje) {
        return router.showAlert(mensaje);
    }

    public Alert showWarning(String mensaje) {
        return router.showWarning(mensaje);
    }
}
