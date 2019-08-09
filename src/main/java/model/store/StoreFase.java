package model.store;

import model.Fase;

/**
 * Interface que contiene metodos para una Fase
 */
public interface StoreFase extends Store<Fase> {
    public Fase findById(int id);
    public Fase delete(int id);
}
