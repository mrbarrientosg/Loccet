package state;

import model.Trabajador;

/**
 * Delegate cuando se edita un Trabajador
 */
public interface EditTrabajadorDelegate {
    public void didEdit(Trabajador old, Trabajador newT);
}
