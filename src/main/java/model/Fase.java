package model;

import repository.RepositoryTarea;
import repository.memory.MemoryRepositoryTarea;

import java.time.Instant;

public class Fase {

    // MARK: - Atributos

    private int id;

    private String titulo;

    private String descripcion;

    private Instant fechaInicio;

    private Instant fechaTermino;

    private Double porcentajeRequerido;

    private Proyecto proyecto;

    private RepositoryTarea repositoryTarea;

    // MARK: - Constructor

    public Fase() {
        repositoryTarea = new MemoryRepositoryTarea();
    }

    // MARK: - Metodos Tarea

    public void agregarTarea(Tarea tarea) {
        repositoryTarea.add(tarea);
    }


    // MARK: - Getter

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Instant getFechaInicio() {
        return fechaInicio;
    }

    public Instant getFechaTermino() {
        return fechaTermino;
    }

    public Double getPorcentajeRequerido() {
        return porcentajeRequerido;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    // MARK: - Setter

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFechaInicio(Instant fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaTermino(Instant fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

    public void setPorcentajeRequerido(Double porcentajeRequerido) {
        this.porcentajeRequerido = porcentajeRequerido;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }
}
