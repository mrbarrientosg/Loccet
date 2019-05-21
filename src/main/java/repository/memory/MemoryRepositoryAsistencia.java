package repository.memory;

import model.Asistencia;
import repository.RepositoryAsistencia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MemoryRepositoryAsistencia implements RepositoryAsistencia {

    private List<Asistencia> asistenciaList;

    public MemoryRepositoryAsistencia() {
        asistenciaList = new ArrayList<>();
    }

    @Override
    public void add(Asistencia value) {
        // Validar asistencia
        if (value == null) return;
        asistenciaList.add(value);
    }

    @Override
    public Asistencia remove(Asistencia value) {
        if (value == null) return null;

        int idx = asistenciaList.indexOf(value);

        if (idx == -1) return null;

        return asistenciaList.remove(idx);
    }

    @Override
    public Asistencia update(Asistencia value) {
        if (value == null) return null;

        int idx = asistenciaList.indexOf(value);

        if (idx == -1) return null;

        return asistenciaList.set(idx, value);
    }

    @Override
    public Iterator<Asistencia> get() {
        return asistenciaList.iterator();
    }
}
