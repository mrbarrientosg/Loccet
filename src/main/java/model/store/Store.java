package model.store;

import model.Cleanable;

public interface Store<Model> extends Cleanable {
    public Model save(Model value);

    public Model delete(Model value);

    public boolean contains(Model value);

    public Iterable<Model> findAll();
}
