package cl.loccet.model;

import java.util.ArrayList;

public class Trabajador {

    private String rut;

    private String nombre;

    private ArrayList<Horario> horarios;

    public Trabajador(String rut, String nombre) {
        this.rut = rut;
        this.nombre = nombre;
        horarios = new ArrayList<>();
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
