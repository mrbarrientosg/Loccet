package model;

import java.time.LocalTime;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class Horario {

    private Integer dia;

    private Proyecto proyecto;

    private Trabajador trabajador;

    private LocalTime horaIncio;

    private LocalTime horaFin;

    private Horario(Builder builder) {
        this.dia = builder.dia;
        this.horaIncio = builder.fechaInicio;
        this.horaFin = builder.fechaTermino;
    }

    public Integer getDia() {
        return dia;
    }

    public LocalTime getFechaInicio() {
        return horaFin;
    }

    public void setFechaInicio(LocalTime fechaInicio) {
        this.horaIncio = fechaInicio;
    }

    public LocalTime getFechaTermino() {
        return horaFin;
    }

    public void setFechaTermino(LocalTime fechaTermino) {
        this.horaIncio = fechaTermino;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(dia.toString());
        builder.append(" - ");
        builder.append(horaIncio.toString());
        builder.append(" - ");
        builder.append(horaFin.toString());
        return builder.toString();
    }

    public static class Builder {

        private final Integer dia;

        private final String idProyecto;

        private final String nombreProyecto;

        private LocalTime fechaInicio;

        private LocalTime fechaTermino;

        public Builder(Integer dia, String idProyecto, String nombreProyecto) {
            this.dia = dia;
            this.idProyecto = idProyecto;
            this.nombreProyecto = nombreProyecto;
        }

        public Builder fechaInicio(LocalTime fechaInicio) {
            this.fechaInicio = fechaInicio;
            return this;
        }

        public Builder fechaTermino(LocalTime fechaTermino) {
            this.fechaTermino = fechaTermino;
            return this;
        }

        public Horario build() {
            return new Horario(this);
        }
    }

}
