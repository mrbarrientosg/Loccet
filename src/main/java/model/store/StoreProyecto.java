package model.store;

import model.Proyecto;

public interface StoreProyecto extends Store<Proyecto> {
    public Proyecto findById(String id);
    public Proyecto delete(String id);
}
