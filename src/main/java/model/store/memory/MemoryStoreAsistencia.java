package model.store.memory;

import model.Asistencia;
import model.store.AbstractStore;
import model.store.Store;

/**
 * Store para las asistencias
 */
public class MemoryStoreAsistencia extends AbstractStore<Asistencia> implements Store<Asistencia> {

    public MemoryStoreAsistencia() {
        super();
    }
}
