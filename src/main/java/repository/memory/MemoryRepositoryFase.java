package repository.memory;

import model.Fase;
import repository.RepositoryFase;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MemoryRepositoryFase implements RepositoryFase {

    private List<Fase> faseList;

    public MemoryRepositoryFase() {
        faseList = new ArrayList<>();
    }

    @Override
    public void add(Fase value) {
        // Validar fase
        if (value == null) return;
        faseList.add(value);
    }

    @Override
    public Fase remove(Fase value) {
        if (value == null) return null;

        int idx = faseList.indexOf(value);

        if (idx == -1) return null;

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
    public Iterator<Fase> get() {
        return faseList.iterator();
    }
}
