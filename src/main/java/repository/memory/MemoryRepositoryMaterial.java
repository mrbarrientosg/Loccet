package repository.memory;

import model.Material;
import repository.RepositoryMaterial;

import java.util.*;

public class MemoryRepositoryMaterial implements RepositoryMaterial {

    private List<Material> materialList;

    private Map<String, Material> materialMap;

    public MemoryRepositoryMaterial() {
        materialList = new ArrayList<>();
        materialMap = new HashMap<>();
    }

    @Override
    public void add(Material value) {
        // Validar material
        if (contains(value) || value == null) return;
        materialMap.put(value.getId(), value);
        materialList.add(value);
    }

    @Override
    public boolean contains(Material value) {
        if (value == null) return false;
        return materialMap.containsValue(value);
    }

    @Override
    public Material remove(Material value) {
        if (!contains(value) || value == null) return null;
        materialList.remove(materialMap.get(value.getId()));
        return materialMap.remove(value.getId());
    }

    @Override
    public Material update(Material value) {
        if (!contains(value) || value == null) return null;
        int idx = materialList.indexOf(materialMap.get(value.getId()));
        materialList.set(idx, value);
        return materialMap.put(value.getId(), value);
    }

    @Override
    public Material get(String id) {
        if (!materialMap.containsKey(id)) return null;
        return materialMap.get(id);
    }

    @Override
    public Iterator<Material> get() {
        return materialList.iterator();
    }

}
