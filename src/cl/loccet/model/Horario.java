package cl.loccet.model;

import java.time.LocalTime;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class Horario {

    private String id;

    private Integer dia;

    private String idProyecto;

    private String nombreProyecto;

    private LocalTime fechaInicio;

    private LocalTime fechaTermino;

    private Horario(Builder builder) {
        id = UUID.randomUUID().toString();
        this.dia = builder.dia;
        this.idProyecto = builder.idProyecto;
        this.nombreProyecto = builder.nombreProyecto;
        this.fechaInicio = builder.fechaInicio;
        this.fechaTermino = builder.fechaTermino;
    }

    public String getId() {
        return id;
    }

    public Integer getDia() {
        return dia;
    }

    public String getIdProyecto() {
        return idProyecto;
    }

    public LocalTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalTime getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(LocalTime fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(dia.toString());
        builder.append(" - ");
        builder.append(nombreProyecto);
        builder.append(" - ");
        builder.append(fechaInicio.toString());
        builder.append(" - ");
        builder.append(fechaTermino.toString());
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
            this.fechaInicio = fechaInicio;
            return this;
        }

        public Horario build() {
            return new Horario(this);
        }
    }

}
