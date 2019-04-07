package cl.loccet.state;

import cl.loccet.model.Constructora;
import cl.loccet.model.Trabajador;

public class EditTrabajadorStategy implements SaveStrategy<Trabajador> {

    private Trabajador old;

    private Constructora model;

    public EditTrabajadorStategy(Constructora model, Trabajador old) {
        this.model = model;
        this.old = old;
    }

    public Trabajador getOld() {
        return old;
    }

    @Override
    public Trabajador save(Trabajador newObject) {
        return null;
    }
}
