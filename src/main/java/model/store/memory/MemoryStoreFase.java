package model.store.memory;

import model.Fase;
import model.store.AbstractStore;
import model.store.StoreFase;

import java.util.*;

public class MemoryStoreFase extends AbstractStore<Fase> implements StoreFase {

    private Map<Integer, Fase> faseMap;

    public MemoryStoreFase() {
        super();
        faseMap = new HashMap<>();
    }

    @Override
    public Fase save(Fase value) {
        super.save(value);
        return faseMap.put(value.getId(), value);
    }

    @Override
    public Fase delete(Fase value) {
        super.delete(value);
        return faseMap.remove(value.getId());
    }


    @Override
    public Fase findById(int id) {
        return faseMap.get(id);
    }

    @Override
    public Fase delete(int id) {
        return delete(findById(id));
    }

    @Override
    public void clean() {
        super.clean();
        faseMap.clear();
        faseMap = null;
    }
}
