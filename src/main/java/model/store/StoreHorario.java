package model.store;

import model.Horario;

/**
 * Interface que contiene metodos para un Horario
 */
public interface StoreHorario extends Store<Horario> {
    public Horario findById(Integer id);
    public Horario delete(Integer id);
}
