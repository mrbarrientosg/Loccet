package cl.loccet.model;

public class Constructora {

    private String rut;

    private String nombre;

    public Constructora(String rut, String nombre) {
        this.rut = rut;
        this.nombre = nombre;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRut() {
        return rut;
    }
}
