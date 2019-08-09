package model.store;

import model.Trabajador;

/**
 * Interface que contiene metodos para un Trabajdor
 */
public interface StoreTrabajador extends Store<Trabajador> {
    public Trabajador findByRut(String rut);
    public Trabajador delete(String rut);
}
