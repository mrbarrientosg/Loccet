package repository;

import model.RegistroMaterial;

import java.util.Iterator;

public interface RepositoryRegistroMaterial {
    public void add(RegistroMaterial value);
    public RegistroMaterial update(RegistroMaterial value);
    public Iterator<RegistroMaterial> get();
}
