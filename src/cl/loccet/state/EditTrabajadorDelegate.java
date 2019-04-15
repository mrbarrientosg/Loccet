package cl.loccet.state;

import cl.loccet.model.Trabajador;

public interface EditTrabajadorDelegate {
    public void didEdit(Trabajador old, Trabajador newT);
}
