package model;

import java.time.LocalTime;

public class Horario {

    // MARK: - Atributos

    private Integer id;

    private Integer dia;

    private Proyecto proyecto;

    private Trabajador trabajador;

    private LocalTime horaInicio;

    private LocalTime horaFin;

    // MARK: - Constructor

    public Horario() {

    }

    // MARK: - Getter

    public Integer getId() {
        return id;
    }

    public Integer getDia() {
        return dia;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
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
