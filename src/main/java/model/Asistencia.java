package model;

import java.time.Instant;

public class Asistencia implements Cleanable {

    // MARK: - Atributos

    private Instant fechaInicio1;

    private Instant fechaTermino1;

    private Instant fechaInicio2;

    private Instant fechaTermino2;

    private Proyecto proyecto;

    private Trabajador trabajador;


    // MARK: - Constructor
    public Asistencia() {

    }

    // MARK: - Getter

    public Instant getFechaInicio1() {
        return fechaInicio1;
    }

    public Instant getFechaTermino1() {
        return fechaTermino1;
    }

    public Instant getFechaInicio2() {
        return fechaInicio2;
    }

    public Instant getFechaTermino2() {
        return fechaTermino2;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }

    // MARK: - Setter

    public void setFechaInicio1(Instant fechaInicio1) {
        this.fechaInicio1 = fechaInicio1;
    }

    public void setFechaTermino1(Instant fechaTermino1) {
        this.fechaTermino1 = fechaTermino1;
    }

    public void setFechaInicio2(Instant fechaInicio2) {
        this.fechaInicio2 = fechaInicio2;
    }

    public void setFechaTermino2(Instant fechaTermino2) {
        this.fechaTermino2 = fechaTermino2;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }

    @Override
    public void clean() {
        proyecto = null;
        trabajador = null;
    }
}
