package model.store;

public interface Store<Model> {
    public Model save(Model value);

    public Model delete(Model value);

    public boolean contains(Model value);

    public Iterable<Model> findAll();

    public void clear();
}
