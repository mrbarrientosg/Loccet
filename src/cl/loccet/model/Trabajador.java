package cl.loccet.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Trabajador {

    private String rut;

    private String nombre;

    private String apellido;

    private LocalDate fechaNacimiento;

    private Especialidad especialidad;

    private ArrayList<Horario> horarios;

    private Trabajador(Builder builder) {
        this.rut = builder.rut;
        this.nombre = builder.nombre;
        this.apellido = builder.apellido;
        this.fechaNacimiento = builder.fechaNacimiento;
        this.especialidad = builder.especialidad;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public static class Builder {

        private String rut;

        private String nombre;

        private String apellido;

        private Especialidad especialidad;

        private LocalDate fechaNacimiento;

        public Builder rut(String rut) {
            this.rut = rut;
            return this;
        }

        public Builder nombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder apellido(String apellido) {
            this.apellido = apellido;
            return this;
        }

        public Builder especialidad(Especialidad especialidad) {
            this.especialidad = especialidad;
            return this;
        }

        public Builder fechaNaciemiento(LocalDate fechaNacimiento) {
            this.fechaNacimiento = fechaNacimiento;
            return this;
        }

        public Trabajador build() {
            return new Trabajador(this);
        }
    }

}
