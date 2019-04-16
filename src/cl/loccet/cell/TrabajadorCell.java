package cl.loccet.cell;

import cl.loccet.model.Trabajador;

import java.time.LocalDate;

public class TrabajadorCell {

    private String rut;

    private String nombre;

    private String apellido;

    private LocalDate fechaNacimiento;

    private String nombreEspecialidad;

    private Double cantidadDeHoras;

    private Double sueldoPorHora;

    private String telefono;

    private String correoElectronico;

    public TrabajadorCell(Trabajador t) {
        this.rut = t.getRut();
        this.nombre = t.getNombre();
        this.apellido = t.getApellido();
        this.fechaNacimiento = t.getFechaNacimiento();
        this.nombreEspecialidad = t.getEspecialidad().getNombre();
        this.cantidadDeHoras = t.getEspecialidad().getCantidadDeHoras();
        this.sueldoPorHora = t.getEspecialidad().getSueldoPorHora();
    }

    public String getRut() {
        return rut;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getNombreEspecialidad() {
        return nombreEspecialidad;
    }

    public Double getCantidadDeHoras() {
        return cantidadDeHoras;
    }

    public Double getSueldoPorHora() {
        return sueldoPorHora;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }
}