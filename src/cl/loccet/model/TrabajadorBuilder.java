package cl.loccet.model;

import java.time.LocalDate;

public class TrabajadorBuilder {

    private String rut;

    private String nombre;

    private String apellido;

    private Especialidad especialidad;

    private LocalDate fechaNacimiento;

    public static TrabajadorBuilder create() {
        return new TrabajadorBuilder();
    }

    public TrabajadorBuilder withRut(String rut) {
        this.rut = rut;
        return this;
    }

    public TrabajadorBuilder withNomre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public TrabajadorBuilder withApellido(String apellido) {
        this.apellido = apellido;
        return this;
    }

    public TrabajadorBuilder withEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
        return this;
    }

    public TrabajadorBuilder withFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
        return this;
    }

    public Trabajador build() {
        return new Trabajador(rut, nombre, apellido, fechaNacimiento);
    }
}
