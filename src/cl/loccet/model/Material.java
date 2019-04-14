package cl.loccet.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;

public class Material {


    private String id;
    private String nombre;
    private String descripcion;
    private int cantidad;
    private  int retiro;
    private Date fechaRetiro;//Se define como string debido a que la funcion para cambiar el formato retorna un String.
    private Date  fechaIngreso;
    private String uds;


    public Material(String nombre, String descripcion, int cantidad,String uds){
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.nombre = nombre;
        this.uds = uds;
        this.id = nombre;
        fechaRetiro = null;
        fechaIngreso = new Date();
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

    public Date getFechaIngreso() {
        return fechaIngreso;
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
