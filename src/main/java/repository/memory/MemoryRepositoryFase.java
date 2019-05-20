package repository.memory;

import model.Fase;
import repository.RepositoryFase;

import java.util.*;

public class MemoryRepositoryFase implements RepositoryFase {

    private List<Fase> faseList;
    private Map<Integer, Fase> faseMap;

    public MemoryRepositoryFase() {
        faseList = new ArrayList<>();
        faseMap = new HashMap<>();
    }

    @Override
    public void add(Fase value) {
        // Validar fase
        if (value == null) return;
        faseList.add(value);
        faseMap.put(value.getId(), value);
    }

    @Override
    public Fase remove(Fase value) {
        if (value == null) return null;

        int idx = faseList.indexOf(value);

        if (idx == -1) return null;

        faseMap.remove(value.getId());

        return faseList.remove(idx);
    }

    @Override
    public Fase update(Fase value) {
        if (value == null) return null;

        int idx = faseList.indexOf(value);

        if (idx == -1) return null;

        return faseList.set(idx, value);
    }

    @Override
    public Fase get(int id) {
        return faseMap.get(id);
    }

    @Override
    public Iterator<Fase> get() {
        return faseList.iterator();
    }
}
