package repository;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractStore<Model> implements Store<Model> {

    private Set<Model> items;

    public AbstractStore() {
        items = new HashSet<>();
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
            throw new NullPointerException();

        return items.contains(value);
    }

    public Iterable<Model> findAll() {
        return items;
    }
}
