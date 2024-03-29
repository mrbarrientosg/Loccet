package model;

import java.math.BigDecimal;

/**
 * Clase que contiene los datos de una especialidad
 */
public class Especialidad {

    // MARK: - Atributos

    private Integer id;

    private String nombre;

    private BigDecimal pagoPorHora;

    // MARK: - Constructor

    public Especialidad(String nombre, BigDecimal pagoPorHora) {
        this.nombre = nombre;
        this.pagoPorHora = pagoPorHora;
    }

    public Especialidad(Especialidad other) {
        this.nombre = other.nombre;
        this.pagoPorHora = other.pagoPorHora;
    }

    // MARK: - Getter

    public String getNombre() {
        return nombre;
    }

    public BigDecimal getPagoPorHora() {
        return pagoPorHora;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;

        if (!(obj instanceof Especialidad)) return false;

        Especialidad e = (Especialidad) obj;

        return e.nombre.equals(nombre) && e.pagoPorHora.equals(pagoPorHora);
    }

    @Override
    public String toString() {
        return nombre;
    }
}
