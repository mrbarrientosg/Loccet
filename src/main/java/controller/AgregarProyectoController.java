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
import java.time.LocalDate;

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
    public void presionarAceptar(String nombreP, String jefeP, BigDecimal montoC, String cliente, String telefonoC,
                                 String direccion, String ciudad, String estado, String pais,
                                 LocalDate fechaF, LocalDate fechaT){
        System.out.println("Todos los campos estan llenos");
        Proyecto proyecto = new Proyecto();
        Localizacion localizacion = new Localizacion(direccion,pais,estado,ciudad);

        proyecto.setNombre(nombreP);
        proyecto.setNombreCliente(cliente);
        proyecto.setEstimacion(montoC);
        proyecto.setFechaInicio(fechaF);
        proyecto.setFechaTermino(fechaF);
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
     * @return una ventana de tipo Warning.
     * @author Matías Zúñiga
     */
  /*  public Alert showWarning(String mensaje) {
        return router.showWarning(mensaje);
    }*/

    public void setView(AgregarProyectoView view){this.view = view;}
}
