package model.store.memory;

import model.Trabajador;
import model.store.AbstractStore;
import model.store.StoreTrabajador;

import java.util.*;

public class MemoryStoreTrabajador extends AbstractStore<Trabajador> implements StoreTrabajador {

    private Map<String, Trabajador> mapTrabajadores;

    public MemoryStoreTrabajador() {
        super();
        mapTrabajadores = new HashMap<>();
    }

    @Override
    public Trabajador save(Trabajador value) {
        super.save(value);
        return mapTrabajadores.put(value.getRut(), value);
    }

    @Override
    public Trabajador delete(Trabajador value) {
        super.delete(value);
        return mapTrabajadores.remove(value.getRut());
    }

    @Override
    public boolean contains(Trabajador value) {
        return mapTrabajadores.containsKey(value.getRut());
    }

    @Override
    public Trabajador findByRut(String rut) {
        return mapTrabajadores.get(rut);
    }

    @Override
    public Trabajador delete(String rut) {
        return delete(findByRut(rut));
    }
}
