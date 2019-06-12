package model.store;

import model.Horario;

public interface StoreHorario extends Store<Horario> {
    public Horario findById(Integer id);
    public Horario delete(Integer id);
}
