package cl.loccet.model;


import java.util.ArrayList;
import java.util.HashMap;

public class Obra {
    private Integer idObra;
    private String direccion;
    private ArrayList<cl.loccet.model.Trabajador> listaEmpleados;
    private HashMap<String, cl.loccet.model.Trabajador> mapEmpleados;
    //TODO: Fecha inicio y fin.

    public Obra(int idObra, String ubicacion){
        this.idObra = new Integer(idObra);
        this.direccion = ubicacion;
        listaEmpleados = new ArrayList<>();
        mapEmpleados = new HashMap<>();
    }

    //Setter
    public void setIdObra(int idObra){
        this.idObra = idObra;
    }

    public void setUbicacion(String ubicacion) {
        this.direccion = ubicacion;
    }

    //Getter
    public Integer getIdObra() {
        return idObra;
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