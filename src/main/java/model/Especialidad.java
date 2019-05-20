package model;

import com.google.gson.JsonObject;

import java.math.BigDecimal;

public class Especialidad {

    private final String nombre;

    private final BigDecimal sueldoPorHora;

    public Especialidad(String nombre, BigDecimal sueldoPorHora) {
        this.nombre = nombre;
        this.sueldoPorHora = sueldoPorHora;
    }

    public String getNombre() {
        return nombre;
    }

    public BigDecimal getSueldoPorHora() {
        return sueldoPorHora;
    }

}
