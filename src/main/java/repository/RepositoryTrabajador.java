package repository;

import model.Trabajador;

import java.util.Iterator;

public interface RepositoryTrabajador {

    public void add(Trabajador value);

    public boolean contains(Trabajador value);
    public Trabajador remove(Trabajador value);
    public Trabajador update(Trabajador value);
    public Trabajador get(String rut);
    public Iterable<Trabajador> get();
    public Iterable<Trabajador> get(Specification specification);
}
