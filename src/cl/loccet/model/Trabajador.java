package cl.loccet.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Trabajador {

    private String rut;

    private String nombre;

    private String apellido;

    private LocalDate fechaNacimiento;

    private Especialidad especialidad;

    private Localizacion localizacion;

    private String telefono;

    private String correoElectronico;

    private ArrayList<Horario> horarios;

    private Trabajador(Builder builder) {
        this.rut = builder.rut;
        this.nombre = builder.nombre;
        this.apellido = builder.apellido;
        this.fechaNacimiento = builder.fechaNacimiento;
        this.especialidad = builder.especialidad;
        this.localizacion = builder.localizacion;
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

    public Localizacion getLocalizacion() {
        return localizacion;
    }

    public static class Builder {

        private String rut;

        private String nombre;

        private String apellido;

        private Especialidad especialidad;

        private LocalDate fechaNacimiento;

        private Localizacion localizacion;

        private String telefono;

        private String correoElectronico;

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

        public Builder fechaNacimiento(LocalDate fechaNacimiento) {
            this.fechaNacimiento = fechaNacimiento;
            return this;
        }

        public Builder localizacion(Localizacion localizacion) {
            this.localizacion = localizacion;
            return this;
        }

        public Builder telefono(String telefono) {
            this.telefono = telefono;
            return this;
        }

        public Builder correoElectronico(String correoElectronico) {
            this.correoElectronico = correoElectronico;
            return this;
        }

        public Trabajador build() {
            return new Trabajador(this);
        }
    }

}
