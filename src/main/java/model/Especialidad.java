package model;

public class Especialidad {

    private final String nombre;

    private final Double sueldoPorHora;

    public Especialidad(String nombre, Double sueldoPorHora) {
        this.nombre = nombre;
        this.sueldoPorHora = sueldoPorHora;
    }

    public String getNombre() {
        return nombre;
    }

    public Double getSueldoPorHora() {
        return sueldoPorHora;
    }

}
