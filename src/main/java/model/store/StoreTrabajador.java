package model.store;

import model.Trabajador;

public interface StoreTrabajador extends Store<Trabajador> {
    public Trabajador findByRut(String rut);
    public Trabajador delete(String rut);
}
