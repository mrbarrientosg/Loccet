package model;

import repository.memory.MemoryRepositoryHorario;
import repository.memory.MemoryRepositoryProyecto;
import repository.RepositoryHorario;
import repository.RepositoryProyecto;

import java.time.LocalDate;
import java.util.*;

public class Trabajador {

    private String rut;

    private String nombre;

    private String apellido;

    private LocalDate fechaNacimiento;

    private Especialidad especialidad;

    private Localizacion localizacion;

    private String telefono;

    private String correoElectronico;

    private int cantidadHoraTrabajada;

    private RepositoryProyecto repositoryProyecto;

    private RepositoryHorario repositoryHorario;

    private Trabajador(Builder builder) {
        this.rut = builder.rut;
        this.nombre = builder.nombre;
        this.apellido = builder.apellido;
        this.fechaNacimiento = builder.fechaNacimiento;
        this.especialidad = builder.especialidad;
        this.localizacion = builder.localizacion;
        this.telefono = builder.telefono;
        this.correoElectronico = builder.correoElectronico;

        repositoryHorario = new MemoryRepositoryHorario();
        repositoryProyecto = new MemoryRepositoryProyecto();
    }

    public void asociarProyecto(Proyecto proyecto) {
        repositoryProyecto.add(proyecto);
    }

    public void agregarHorario(Horario horario) {
        repositoryHorario.add(horario);
    }

    public Horario eliminarHorario(Horario horario) {
        return repositoryHorario.remove(horario);
    }

    public List<Horario> obtenerListaHorario() {
        List<Horario> aux = new ArrayList<>();
        repositoryHorario.get().forEachRemaining(aux::add);
        return Collections.unmodifiableList(aux);
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

}
