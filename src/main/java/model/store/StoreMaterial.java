package model.store;

import model.Material;

/**
 * Interface que contiene metodos para un Material
 */
public interface StoreMaterial extends Store<Material> {
    public Material findById(String id);
    public Material delete(String id);
}
