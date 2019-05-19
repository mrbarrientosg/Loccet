package repository.memory;

import model.Horario;
import repository.RepositoryHorario;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MemoryRepositoryHorario implements RepositoryHorario {

    private List<Horario> horarioList;

    public MemoryRepositoryHorario() {
        horarioList = new ArrayList<>();
    }

    @Override
    public void add(Horario value) {
        // Validar horario
        if (value == null) return;
        horarioList.add(value);
    }

    @Override
    public Horario remove(Horario value) {
        if (value == null) return null;

        int idx = horarioList.indexOf(value);

        if (idx == -1) return null;

        return horarioList.remove(idx);
    }

    @Override
    public Horario update(Horario value) {
        if (value == null) return null;

        int idx = horarioList.indexOf(value);

        if (idx == -1) return null;

        return horarioList.set(idx, value);
    }

    @Override
    public Iterator<Horario> get() {
        return horarioList.iterator();
    }
}
