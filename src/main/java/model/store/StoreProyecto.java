package model.store;

import model.Proyecto;

/**
 * Interface que contiene metodos para un Proyecto
 */
public interface StoreProyecto extends Store<Proyecto> {
    public Proyecto findById(String id);
    public Proyecto delete(String id);
}
