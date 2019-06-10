package repository;

import model.Horario;
import model.Proyecto;

import java.util.Iterator;

public interface StoreHorario extends Store<Horario> {
    public Horario findById(Integer id);
    public Horario delete(Integer id);
}
