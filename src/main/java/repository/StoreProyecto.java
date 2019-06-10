package repository;

import model.Proyecto;

import java.util.Iterator;

public interface StoreProyecto extends Store<Proyecto> {
    public Proyecto findById(String id);
    public Proyecto delete(String id);
}
