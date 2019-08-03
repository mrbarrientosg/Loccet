package model.store.memory;

import model.Material;
import model.store.AbstractStore;
import model.store.StoreMaterial;

import java.util.*;

public class MemoryStoreMaterial extends AbstractStore<Material> implements StoreMaterial {

    private Map<String, Material> materialMap;

    public MemoryStoreMaterial() {
        super();
        materialMap = new HashMap<>();
    }

    @Override
    public Material save(Material value) {
        super.save(value);
        return materialMap.put(value.getId(), value);
    }

    @Override
    public Material delete(Material value) {
        super.delete(value);
        return materialMap.remove(value.getId());
    }

    @Override
    public Material findById(String id) {
        return materialMap.get(id);
    }

    @Override
    public Material delete(String id) {
        return delete(findById(id));
    }

    @Override
    public void clear() {
        super.clear();
        materialMap.clear();
        materialMap = null;
    }
}
