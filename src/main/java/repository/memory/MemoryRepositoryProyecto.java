package repository.memory;

import model.Proyecto;
import model.Trabajador;
import repository.MemorySpecification;
import repository.RepositoryProyecto;
import repository.Specification;

import java.util.*;

public class MemoryRepositoryProyecto implements RepositoryProyecto {

    private List<Proyecto> listaProyecto;

    private Map<String, Proyecto> mapProyecto;

    public MemoryRepositoryProyecto() {
        listaProyecto = new ArrayList<>();
        mapProyecto = new HashMap<>();
    }

    @Override
    public void add(Proyecto value) {
        // Validar proyecto
        if (contains(value) || value == null) return;
        mapProyecto.put(value.getId(), value);
        listaProyecto.add(value);
    }

    @Override
    public boolean contains(Proyecto value) {
        if (value == null) return false;
        return mapProyecto.containsValue(value);
    }

    @Override
    public Proyecto remove(Proyecto value) {
        if (!contains(value) || value == null) return null;
        listaProyecto.remove(mapProyecto.get(value.getId()));
        return mapProyecto.remove(value.getId());
    }

    @Override
    public Proyecto update(Proyecto value) {
        if (!contains(value) || value == null) return null;
        int idx = listaProyecto.indexOf(mapProyecto.get(value.getId()));
        listaProyecto.set(idx, value);
        return mapProyecto.put(value.getId(), value);
    }

    @Override
    public Proyecto get(String id) {
        if (!mapProyecto.containsKey(id)) return null;
        return mapProyecto.get(id);
    }

    @Override
    public Iterator<Proyecto> get() {
        return listaProyecto.iterator();
    }

    @Override
    public Iterator<Proyecto> get(Specification specification) {
        final MemorySpecification<Proyecto> memorySpecification = (MemorySpecification<Proyecto>) specification;

        final List<Proyecto> trabajadors = new ArrayList<>();

        for (Proyecto proyecto: listaProyecto) {
            if (memorySpecification.test(proyecto))
                trabajadors.add(proyecto);
        }

        return trabajadors.iterator();
    }
}
