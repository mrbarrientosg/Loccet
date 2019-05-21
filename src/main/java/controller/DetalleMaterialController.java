package controller;

import base.Controller;
import model.Material;
import view.DetalleMaterialView;

public class DetalleMaterialController extends Controller {

    private DetalleMaterialView view;
    private Material model;


    public void modificarDescripcion(String descripcion){
        model.setDescripcion(descripcion);

    }
    public void  modificarNombre(String nombre){
        model.setNombre(nombre);
    }

    public String getID(){
        return model.getId();
    }
    public String getNombre(){return model.getNombre();}

    public String getDescripcion(){return model.getDescripcion();}

    public Double getCantidad(){return model.getCantidad();}

    public void ModificarDescripcion(String descripcion){model.setDescripcion(descripcion);}

    public void ModificarNombre(String nombre){model.setNombre(nombre);}

    public void setView(DetalleMaterialView view) {
        this.view = view;
    }


}
