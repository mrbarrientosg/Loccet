package cl.loccet.model;

import java.time.LocalDate;

public class TrabajadorBuilder {

    private final String rut;

    private final String nombre;

    private final String apellido;

    private String especialidad;

    private LocalDate fechaNacimiento;

    public TrabajadorBuilder(String rut, String nombre, String apellido) {
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public static TrabajadorBuilder create(String rut, String nombre, String apellido) {
        return new TrabajadorBuilder(rut, nombre, apellido);
    }

    public TrabajadorBuilder withEspecialidad(String especialidad) {
        this.especialidad = especialidad;
        return this;
    }

    public TrabajadorBuilder withFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
        return this;
    }

    public Trabajador build() {
        return new Trabajador(rut, nombre, apellido, especialidad, fechaNacimiento);
    }
}
