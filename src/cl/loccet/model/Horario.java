package cl.loccet.model;

import java.util.Date;

public class Horario {

    private Date fechaInicio;

    private Date fechaTermino;

    public Horario(Date fechaInicio, Date fechaTermino) {
        this.fechaInicio = fechaInicio;
        this.fechaTermino = fechaTermino;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(Date fechaTermino) {
        this.fechaTermino = fechaTermino;
    }
}
