package repository;

import model.Material;

import java.util.Iterator;

public interface RepositoryMaterial {
    public void add(Material value);
    public boolean contains(Material value);
    public Material remove(Material value);
    public Material update(Material value);
    public Material get(String id);
    public Iterable<Material> get();
}
