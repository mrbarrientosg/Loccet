package repository.memory;

import model.Trabajador;
import repository.MemorySpecification;
import repository.RepositoryTrabajador;
import repository.Specification;

import java.util.*;

public class MemoryRepositoryTrabajador implements RepositoryTrabajador {

    private List<Trabajador> listaTrabajadores;

    private Map<String, Trabajador> mapTrabajadores;

    public MemoryRepositoryTrabajador() {
        listaTrabajadores = new ArrayList<>();
        mapTrabajadores = new HashMap<>();
    }

    @Override
    public void add(Trabajador value) {
        // Validar trabajador
        if (contains(value) || value == null) return;
        mapTrabajadores.put(value.getRut(), value);
        listaTrabajadores.add(value);
    }

    @Override
    public boolean contains(Trabajador value) {
        if (value == null) return false;
        return mapTrabajadores.containsValue(value);
    }

    @Override
    public Trabajador remove(Trabajador value) {
        if (!contains(value) || value == null) return null;
        listaTrabajadores.remove(mapTrabajadores.get(value.getRut()));
        return mapTrabajadores.remove(value.getRut());
    }

    @Override
    public Trabajador update(Trabajador value) {
        if (!contains(value) || value == null) return null;
        int idx = listaTrabajadores.indexOf(mapTrabajadores.get(value.getRut()));
        listaTrabajadores.set(idx, value);
        return mapTrabajadores.put(value.getRut(), value);
    }

    @Override
    public Trabajador get(String rut) {
        if (!mapTrabajadores.containsKey(rut)) return null;
        return mapTrabajadores.get(rut);
    }

    @Override
    public Iterator<Trabajador> get() {
        return listaTrabajadores.iterator();
    }

    @Override
    public Iterator<Trabajador> get(Specification specification) {
        final MemorySpecification<Trabajador> memorySpecification = (MemorySpecification<Trabajador>) specification;

        final List<Trabajador> trabajadors = new ArrayList<>();

        for (Trabajador trabajador: listaTrabajadores) {
            if (memorySpecification.test(trabajador))
                trabajadors.add(trabajador);
        }

        return trabajadors.iterator();
    }
}
