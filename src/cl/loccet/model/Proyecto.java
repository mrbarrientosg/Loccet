package cl.loccet.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Obra {
    private Integer id;
    private String direccion;
    private ArrayList<cl.loccet.model.Trabajador> listaEmpleados;
    private HashMap<String, cl.loccet.model.Trabajador> mapEmpleados;
    //TODO: Fecha inicio y fin.

    public Obra(int id, String ubicacion){
        this.id = new Integer(id);
        this.direccion = ubicacion;
        listaEmpleados = new ArrayList<>();
        mapEmpleados = new HashMap<>();
    }

    //Setter
    public void setId(int id){
        this.id = id;
    }

    public void setUbicacion(String ubicacion) {
        this.direccion = ubicacion;
    }

    //Getter
    public Integer getId() {
        return id;
    }

    public String getUbicacion() {
        return direccion;
    }

    //Metodos

    public void agregarTrabajador(cl.loccet.model.Trabajador trabajador){
        listaEmpleados.add(trabajador);
        mapEmpleados.put(trabajador.getRut(), trabajador); //TODO: Validar el put
    }
}