package repository.memory;

import model.RegistroMaterial;
import repository.RepositoryRegistroMaterial;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MemoryRepositoryRegistroMaterial implements RepositoryRegistroMaterial {

    private List<RegistroMaterial> registroMaterialList;

    public MemoryRepositoryRegistroMaterial() {
        registroMaterialList = new ArrayList<>();
    }

    @Override
    public void add(RegistroMaterial value) {
        // Validar registro
        if (value == null) return;
        registroMaterialList.add(value);
    }

    @Override
    public RegistroMaterial update(RegistroMaterial value) {
        if (value == null) return null;
        int idx = registroMaterialList.indexOf(value);
        return registroMaterialList.set(idx, value);
    }

    @Override
    public Iterator<RegistroMaterial> get() {
        return registroMaterialList.iterator();
    }
}
