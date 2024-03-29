package model.store.memory;

import model.Proyecto;
import model.store.AbstractStore;
import model.store.StoreProyecto;

import java.util.*;

/**
 * Store para los proyectos
 */
public class MemoryStoreProyecto extends AbstractStore<Proyecto> implements StoreProyecto {

    private Map<String, Proyecto> mapProyecto;

    public MemoryStoreProyecto() {
        super();
        mapProyecto = new HashMap<>();
    }

    @Override
    public Proyecto save(Proyecto value) {
        super.save(value);
        return mapProyecto.put(value.getId(), value);
    }

    @Override
    public Proyecto delete(Proyecto value) {
        super.delete(value);
        return mapProyecto.remove(value.getId());
    }

    @Override
    public boolean contains(Proyecto value) {
        return mapProyecto.containsKey(value.getId());
    }

    @Override
    public Proyecto findById(String id) {
        return mapProyecto.get(id);
    }

    @Override
    public Proyecto delete(String id) {
        return delete(findById(id));
    }

    @Override
    public void clean() {
        super.clean();
        mapProyecto.clear();
        mapProyecto = null;
    }
}
