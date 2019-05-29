package controller;

import base.Controller;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.Constructora;
import model.Localizacion;
import model.Proyecto;
import router.AgregarProyectoRouter;
import view.AgregarProyectoView;

import java.math.BigDecimal;

/**
 * @author Matias Zuñiga
 * Se encarga de mostrar la información ingresada por el usuario en la vista AgregarProyecto
 */

public final class AgregarProyectoController extends Controller {

    private AgregarProyectoView view;
    private Constructora model = Constructora.getInstance();



    /**
     * Función que permite ingresar un proyecto a la constructora.
     * @author Matías Zúñiga
     */
    public void presionarAceptar(TextField nombreP,TextField jefeP, TextField montoC,TextField cliente,TextField telefonoC,TextField mailC,TextField direccion,TextField ciudad,TextField estado,TextField pais,DatePicker fechaF,DatePicker fechaT){
        System.out.println("Todos los campos estan llenos");
        Proyecto proyecto = new Proyecto();

        Localizacion localizacion = new Localizacion();
        localizacion.setCiudad(ciudad.getText());
        localizacion.setDireccion(direccion.getText());
        localizacion.setEstado(estado.getText());


        proyecto.setNombre(nombreP.getText());
        proyecto.setNombreCliente(cliente.getText());
        proyecto.setEstimacion(new BigDecimal(montoC.getText()));
        proyecto.setFechaInicio(fechaF.getValue());
        proyecto.setFechaTermino(fechaF.getValue());
        proyecto.setLocalizacion(localizacion);
        model.agregarProyecto(proyecto);
    }

    /**
     * @param mensaje texto expuesto en la alerta.
     * @return una ventana de tipo alerta
     * @author Matías Zúñiga
     */
  /*  public Alert showAlert(String mensaje) {
        return router.showAlert(mensaje);
    }*/
    /**
     * @param mensaje texto que se expondra en la alerta.
     * @return una ventana de tipo Warning.
     * @author Matías Zúñiga
     */
  /*  public Alert showWarning(String mensaje) {
        return router.showWarning(mensaje);
    }*/

    public void setView(AgregarProyectoView view){this.view = view;}
}
