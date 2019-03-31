package cl.loccet.model;

public class Constructora {
    String rut;
    String nombre;

    public Constructora(String rut, String nombre){
        this.nombre = nombre;
        this.rut = rut;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }
}
