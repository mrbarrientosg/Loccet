package repository;

import model.Fase;

import java.util.Iterator;

public interface RepositoryFase {
    public void add(Fase value);
    public Fase remove(Fase value);
    public Fase update(Fase value);
    public Fase get(int id);
    public Iterator<Fase> get();
}
