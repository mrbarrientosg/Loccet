package cl.loccet.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Constructora {

    private String rut;
    private String nombre;
    private ArrayList<Proyecto> listaObras;
    //TODO:permisos
    private HashMap<Integer, Proyecto> mapObra;

    public Constructora(String rut, String nombre) {
        this.rut = rut;
        this.nombre = nombre;
        listaObras = new ArrayList<>();
        mapObra = new HashMap<>();
    }
    //Setter

    public void setRut(String rut) {
        this.rut = rut;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //Getter

    public String getRut() {
        return rut;
    }

    public String getNombre() {
        return nombre;
    }

    //Metodos

    public void agregarObra(cl.loccet.model.Proyecto proyecto){
        listaObras.add(proyecto);
        mapObra.put(proyecto.getId(),proyecto);
    }

    public boolean agregarTrabajador(int idObra, Proyecto trabajador){
        if(mapObra.get(idObra) == null) return false;
        // mapObra.get(idObra).agregarTrabajador(trabajador);
        return true;
    }
}

