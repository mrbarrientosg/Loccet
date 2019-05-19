package repository;

import model.Tarea;

import java.util.Iterator;

public interface RepositoryTarea {
    public void add(Tarea value);
    public Tarea remove(Tarea value);
    public Tarea update(Tarea value);
    public Iterator<Tarea> get();
}
