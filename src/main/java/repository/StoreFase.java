package repository;

import model.Fase;

import java.util.Iterator;

public interface StoreFase extends Store<Fase> {
    public Fase findById(int id);
    public Fase delete(int id);
}
