package model.store;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase abstracta base para un store que guarda
 * datos.
 * @param <Model> El tipo de dato que se quiere guardar en el store
 */
public abstract class AbstractStore<Model> implements Store<Model> {

    private List<Model> items;

    public AbstractStore() {
        items = new ArrayList<>();
    }

    public Model save(Model value) {
        if (contains(value)) {
            delete(value);
        }

        items.add(value);

        return value;
    }

    public Model delete(Model value) {
        if (!contains(value))
            return null;

        items.remove(value);
        return value;
    }

    public boolean contains(Model value) {
        if (value == null)
            return false;

        return items.contains(value);
    }

    public Iterable<Model> findAll() {
        return items;
    }

    @Override
    public void clean() {
        items.clear();
        items = null;
    }
}
