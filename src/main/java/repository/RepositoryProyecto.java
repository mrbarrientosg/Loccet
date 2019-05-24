package repository;

import model.Proyecto;

import java.util.Iterator;

public interface RepositoryProyecto {
    public void add(Proyecto value);
    public boolean contains(Proyecto value);
    public Proyecto remove(Proyecto value);
    public Proyecto update(Proyecto value);
    public Proyecto get(String id);
    public Iterator<Proyecto> get();
    public Iterator<Proyecto> get(Specification specification);

}
