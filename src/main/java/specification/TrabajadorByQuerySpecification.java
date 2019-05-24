package specification;

import model.Trabajador;
import repository.MemorySpecification;

public class TrabajadorByQuerySpecification implements MemorySpecification<Trabajador> {

    private final String query;

    public TrabajadorByQuerySpecification(final String query) {
        this.query = query;
    }

    @Override
    public boolean test(Trabajador value) {
        if (value == null) return false;

        boolean test = false;

        if (value.getRut() != null)
            test |= value.getRut().contains(query);

        if (value.getNombre() != null)
            test |= value.getNombre().contains(query);

        if (value.getApellido() != null)
            test |= value.getApellido().contains(query);

        return test;
    }
}
