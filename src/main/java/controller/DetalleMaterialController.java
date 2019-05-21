package controller;

import base.Controller;
import model.Material;
import model.RegistroMaterial;
import view.DetalleMaterialView;

/**
 * @author Sebastian Fuenzalida
 */
public class DetalleMaterialController extends Controller {
    //Se declaran las variables

    private DetalleMaterialView view;
    private Material model;


    /**
     * @param descripcion material
     */
    public void modificarDescripcion(String descripcion){
        model.setDescripcion(descripcion);

    }

    /**
     * @param nombre material
     */
    public void  modificarNombre(String nombre){
        model.setNombre(nombre);
    }

    /**
     * @return id
     */
    public String getID(){
        return model.getId();
    }

    /**
     * @return nombre material.
     */
    public String getNombre(){return model.getNombre();}

    /**
     * @return descripcion material
     */
    public String getDescripcion(){return model.getDescripcion();}

    /**
     * @return cantidad material
     */

    public Double getCantidad(){return model.getCantidad();}

    /**
     * @param descripcion  material
     */

    public void ModificarDescripcion(String descripcion){model.setDescripcion(descripcion);}

    /**
     * @param nombre material
     */

    public void ModificarNombre(String nombre){model.setNombre(nombre);}

    public boolean retirarMaterial(double cantidad){
        if (model.getCantidad() > cantidad) return false;
        else{
            model.setCantidad(model.getCantidad()-cantidad);
            RegistroMaterial registroMaterial = new RegistroMaterial(cantidad);
            model.agregarRegistro(registroMaterial);
            return true;
        }
    }
    /**
     * @param view detalleMaterial
     */

    public void setView(DetalleMaterialView view) {
        this.view = view;
    }
    public void setModel(Material model){
        this.model = model;
    }


}
