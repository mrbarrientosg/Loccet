package model;

import java.time.Instant;

public class Asistencia {

    private Instant fechaInicio1;

    private Instant fechaTermino1;

    private Instant fechaInicio2;

    private Instant fechaTermino2;

    private Proyecto proyecto;

    private Trabajador trabajador;

    public Asistencia() {

    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }
}
