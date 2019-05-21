package model;

import repository.RepositoryTarea;
import repository.memory.MemoryRepositoryTarea;

import java.time.Instant;

public class Fase {

    private int id;

    private String titulo;

    private String descripcion;

    private Instant fechaInicio;

    private Instant fechaTermino;

    private Double porcentajeRequerido;

    private Proyecto proyecto;

    private RepositoryTarea repositoryTarea;

    public Fase() {
        repositoryTarea = new MemoryRepositoryTarea();
    }

    public void agregarTarea(Tarea tarea) {
        repositoryTarea.add(tarea);
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public int getId() {
        return id;
    }
}
