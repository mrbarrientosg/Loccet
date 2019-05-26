package repository.memory;

import model.Horario;
import repository.RepositoryHorario;

import java.util.*;

public class MemoryRepositoryHorario implements RepositoryHorario {

    private List<Horario> horarioList;

    private Map<Integer, Horario> horarioMap;

    public MemoryRepositoryHorario() {
        horarioList = new ArrayList<>();
        horarioMap = new HashMap<>();
    }

    @Override
    public void add(Horario value) {
        // Validar horario
        if (value == null) return;
        horarioList.add(value);
        horarioMap.put(value.getId(), value);
    }

    @Override
    public Horario remove(Horario value) {
        if (value == null || !horarioMap.containsValue(value)) return null;
        horarioList.remove(value);
        return horarioMap.remove(value.getId());
    }

    @Override
    public Horario update(Horario value) {
        if (value == null) return null;
        return horarioMap.put(value.getId(), value);
    }

    @Override
    public Horario get(Integer id) {
        return horarioMap.get(id);
    }

    @Override
    public Iterable<Horario> get() {
        return horarioList;
    }
}
