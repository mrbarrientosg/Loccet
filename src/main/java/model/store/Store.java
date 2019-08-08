package model.store;

import model.Cleanable;

/**
 * Interface que contiene lo basico para un store
 *
 * @param <Model> El tipo de dato que se quiere guardar en el store
 */
public interface Store<Model> extends Cleanable {
    public Model save(Model value);

    public Model delete(Model value);

    public boolean contains(Model value);

    public Iterable<Model> findAll();
}
