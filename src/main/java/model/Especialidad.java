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

    public Especialidad(Especialidad other) {
        this.nombre = other.nombre;
        this.sueldoPorHora = other.sueldoPorHora;
    }

    // MARK: - Getter

    public String getNombre() {
        return nombre;
    }

    public BigDecimal getSueldoPorHora() {
        return sueldoPorHora;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;

        if (!(obj instanceof Trabajador)) return false;

        Especialidad e = (Especialidad) obj;

        return e.nombre.equals(nombre) && e.sueldoPorHora.equals(sueldoPorHora);
    }
}
