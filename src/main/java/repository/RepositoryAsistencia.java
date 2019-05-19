package repository;

import model.Asistencia;
import model.Horario;

import java.util.Iterator;

public interface RepositoryAsistencia {
    public void add(Asistencia value);
    public Asistencia remove(Asistencia value);
    public Asistencia update(Asistencia value);
    public Iterator<Asistencia> get();
}
