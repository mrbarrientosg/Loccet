package controller;

import base.Controller;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.Constructora;
import model.Proyecto;
import router.AgregarProyectoRouter;
import view.AgregarProyectoView;

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
     * Función que permite ingresar un proyecto a la constructora.
     * @author Matías Zúñiga
     */

    public void presionarAceptar(TextField nombreP,TextField jefeP, TextField montoC,TextField cliente,TextField telefonoC,TextField mailC,TextField direccion,TextField ciudad,TextField estado,TextField pais,DatePicker fechaF,DatePicker fechaT){
        System.out.println("Todos los campos estan llenos");
        Proyecto proyecto = new Proyecto.Builder(nombreP.getText(),jefeP.getText(),Double.parseDouble(montoC.getText()),cliente.getText())
                .datosCliente(mailC.getText(),telefonoC.getText())
                .datosUbicacion(direccion.getText(),pais.getText(),ciudad.getText(),estado.getText())
                .fechaProyecto(fechaF.getValue(),fechaT.getValue())
                .build();
        if(!model.existeProyecto(proyecto)){
            model.agregarProyecto(proyecto);
            router.showInformation("Agregado satisfactoriamente", nombreP);

        }
        else {
            router.showAlert("Proyecto ingresado previamente").showAndWait();
        }
        //TODO: Aqui se cierra la vista
    }
    /**
     * @param mensaje texto expuesto en la alerta.
     * @return una ventana de tipo alerta
     * @author Matías Zúñiga
     */
    public Alert showAlert(String mensaje) {
        return router.showAlert(mensaje);
    }
    /**
     * @param mensaje texto que se expondra en la alerta.
     * @return una ventana de tipo Warning.
     * @author Matías Zúñiga
     */
    public Alert showWarning(String mensaje) {
        return router.showWarning(mensaje);
    }
}
