package cl.loccet.model;

import java.time.LocalDate;

public class Material {


    private String id;
    private String nombre;
    private String descripcion;
    private int cantidad;
    private  int retiro;
    private LocalDate fechaRetiro;
    private LocalDate fechaIngreso;
    private String uds;


    public Material(String nombre, String descripcion, int cantidad,String uds){
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.nombre = nombre;
        this.uds = uds;
        this.id = nombre;
        fechaRetiro = null;
    }
    public Material(){
        descripcion = null;
        fechaRetiro = null;
        cantidad = 0;
        id = null;
    }



    public String getId(){return  id;}
    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }



    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }


}
