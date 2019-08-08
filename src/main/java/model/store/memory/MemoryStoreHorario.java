package model.store.memory;

import model.Horario;
import model.store.AbstractStore;
import model.store.StoreHorario;

import java.util.*;

/**
 * Store para los horarios
 */
public class MemoryStoreHorario extends AbstractStore<Horario> implements StoreHorario {

    private Map<Integer, Horario> horarioMap;

    public MemoryStoreHorario() {
        super();
        horarioMap = new HashMap<>();
    }

    @Override
    public Horario save(Horario value) {
        super.save(value);
        return horarioMap.put(value.getId(), value);
    }

    @Override
    public Horario delete(Horario value) {
        super.delete(value);
        return horarioMap.remove(value.getId());
    }

    @Override
    public boolean contains(Horario value) {
        return horarioMap.containsKey(value.getId());
    }

    @Override
    public Horario findById(Integer id) {
        return horarioMap.get(id);
    }

    @Override
    public Horario delete(Integer id) {
        return delete(findById(id));
    }

    @Override
    public void clean() {
        super.clean();
        horarioMap.clear();
        horarioMap = null;
    }
}
