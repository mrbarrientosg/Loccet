package model.store.memory;

import model.Tarea;
import model.store.AbstractStore;
import model.store.Store;

/**
 * Store para las tareas
 */
public class MemoryStoreTarea extends AbstractStore<Tarea> implements Store<Tarea> {

    public MemoryStoreTarea() {
        super();
    }
}
