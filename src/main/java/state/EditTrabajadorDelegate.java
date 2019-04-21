package state;

import model.Trabajador;

public interface EditTrabajadorDelegate {
    public void didEdit(Trabajador old, Trabajador newT);
}
