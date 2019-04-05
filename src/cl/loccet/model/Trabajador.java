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

    public Trabajador(String rut, String nombre, String apellido, LocalDate fechaNacimiento) {
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
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

}
