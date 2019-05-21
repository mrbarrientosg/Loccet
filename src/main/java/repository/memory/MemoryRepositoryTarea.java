package repository.memory;

import model.Tarea;
import repository.RepositoryTarea;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MemoryRepositoryTarea implements RepositoryTarea {

    private List<Tarea> tareaList;

    public MemoryRepositoryTarea() {
        tareaList = new ArrayList<>();
    }

    @Override
    public void add(Tarea value) {
        // Validar tarea
        if (value == null) return;
        tareaList.add(value);
    }

    @Override
    public Tarea remove(Tarea value) {
        if (value == null) return null;

        int idx = tareaList.indexOf(value);

        if (idx == -1) return null;

        return tareaList.remove(idx);
    }

    @Override
    public Tarea update(Tarea value) {
        if (value == null) return null;

        int idx = tareaList.indexOf(value);

        if (idx == -1) return null;

        return tareaList.set(idx, value);
    }

    @Override
    public Iterator<Tarea> get() {
        return tareaList.iterator();
    }
}
