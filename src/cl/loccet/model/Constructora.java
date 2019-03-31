package cl.loccet.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Constructora {

    private String rut;
    private String nombre;
    private ArrayList<Proyecto> listaProyecto;
    //TODO:permisos
    private HashMap<Integer, Proyecto> mapProyecto;

    public Constructora(String rut, String nombre) {
        this.rut = rut;
        this.nombre = nombre;
        listaProyecto = new ArrayList<>();
        mapProyecto = new HashMap<>();
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

    public void agregarProyecto(Proyecto proyecto){
        listaProyecto.add(proyecto);
        mapProyecto.put(proyecto.getId(),proyecto);
    }

    public boolean agregarTrabajador(int id, Proyecto trabajador){
        if(mapProyecto.get(id) == null) return false;
        mapProyecto.get(id).agregarTrabajador(trabajador);
        return true;
    }
}

