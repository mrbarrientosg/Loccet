package repository.memory;

import model.Tarea;
import repository.AbstractStore;
import repository.Store;

public class MemoryStoreTarea extends AbstractStore<Tarea> implements Store<Tarea> {

    public MemoryStoreTarea() {
        super();
    }
}
