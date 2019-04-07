package cl.loccet.state;

import cl.loccet.model.Constructora;
import cl.loccet.model.Trabajador;

public class AddTrabajadorStrategy implements SaveStrategy<Trabajador> {

    private Constructora model;

    public AddTrabajadorStrategy(Constructora model) {
        this.model = model;
    }

    @Override
    public Trabajador save(Trabajador object) {
        if (model.agregarTrabajador(object))
            return object;
        return null;
    }
}
