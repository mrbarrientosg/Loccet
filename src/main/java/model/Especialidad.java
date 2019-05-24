package model;

import com.google.gson.JsonObject;

import java.math.BigDecimal;

public class Especialidad {

    // MARK: - Atributos

    private final String nombre;

    private final BigDecimal sueldoPorHora;

    // MARK: - Constructor

    public Especialidad(String nombre, BigDecimal sueldoPorHora) {
        this.nombre = nombre;
        this.sueldoPorHora = sueldoPorHora;
    }

    // MARK: - Getter

    public String getNombre() {
        return nombre;
    }

    public BigDecimal getSueldoPorHora() {
        return sueldoPorHora;
    }

}
