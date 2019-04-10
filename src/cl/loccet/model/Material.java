package cl.loccet.model;

import java.time.LocalDate;

public class Material {


    private int id;
    private String descripcion;
    private int cantidad;
    private LocalDate fechaRetiro;


    public Material(int id, String descripcion, int cantidad){
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.id = id;
        fechaRetiro = null;
    }




    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }



    public void setId(int id) {
        this.id = id;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }


}
