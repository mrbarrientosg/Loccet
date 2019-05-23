package model;

import java.time.LocalTime;

public class Horario {

    // MARK: - Atributos

    private Integer dia;

    private Proyecto proyecto;

    private Trabajador trabajador;

    private LocalTime horaIncio;

    private LocalTime horaFin;

    // MARK: - Constructor

    public Horario() {

    }

    // MARK: - Getter

    public Integer getDia() {
        return dia;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public LocalTime getHoraIncio() {
        return horaIncio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    // MARK: - Setter

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }
}
