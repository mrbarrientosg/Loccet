package repository;

import model.Horario;
import model.Proyecto;

import java.util.Iterator;

public interface RepositoryHorario {
    public void add(Horario value);
    public Horario remove(Horario value);
    public Horario update(Horario value);
    public Horario get(Integer id);
    public Iterable<Horario> get();
}
