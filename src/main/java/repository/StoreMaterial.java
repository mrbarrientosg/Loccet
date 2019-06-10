package repository;

import model.Material;

import java.util.Iterator;

public interface StoreMaterial extends Store<Material> {
    public Material findById(String id);
    public Material delete(String id);
}
