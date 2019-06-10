package repository.memory;

import model.Asistencia;
import repository.AbstractStore;
import repository.Store;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MemoryStoreAsistencia extends AbstractStore<Asistencia> implements Store<Asistencia> {

    public MemoryStoreAsistencia() {
        super();
    }
}
