package repository;

import model.Trabajador;

import java.util.Iterator;

public interface StoreTrabajador extends Store<Trabajador> {
    public Trabajador findByRut(String rut);
    public Trabajador delete(String rut);
}
