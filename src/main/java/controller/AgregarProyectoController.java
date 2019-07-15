package controller;

import base.Controller;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import exceptions.EmptyFieldException;
import json.LocalDateTypeConverter;
import model.Constructora;
import model.Localizacion;
import model.Proyecto;
import network.endpoint.ProyectoAPI;
import network.service.Router;
import view.AgregarProyectoView;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.logging.Level;

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
    public void presionarAceptar(String nombreP, String jefeP, BigDecimal montoC, String cliente,
                                 String direccion, String ciudad, String estado, String pais,
                                 LocalDate fechaF, LocalDate fechaT) throws EmptyFieldException {
        System.out.println("Todos los campos estan llenos");
        Proyecto proyecto = new Proyecto();
        Localizacion localizacion = new Localizacion(direccion,pais,estado,ciudad);
        proyecto.setNombre(nombreP);
        proyecto.setNombreCliente(cliente);
        proyecto.setCostoEstimado(montoC);
        proyecto.setFechaInicio(fechaF);
        proyecto.setFechaTermino(fechaF);
        proyecto.setLocalizacion(localizacion);
        model.agregarProyecto(proyecto);
        //TODO: se agrega un proyecto

        Router<ProyectoAPI> service = Router.getInstance();

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeConverter())
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        JsonObject json = gson.toJsonTree(proyecto).getAsJsonObject();
        json.addProperty("dns_constructora", model.getDns());

        System.out.println(json);

        service.request(ProyectoAPI.CREATE.CREATE, json)
                .subscribe(jsonElement -> {
                    JsonObject responseJson = jsonElement.getAsJsonObject();
                    localizacion.setId(responseJson.get("id_localizacion").getAsInt());
                    proyecto.getInventarioMaterial().setId(responseJson.get("id_inventario").getAsInt());
                    System.out.println(jsonElement);
                }, throwable -> {
                    LOGGER.log(Level.SEVERE, "", throwable);
                });
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
