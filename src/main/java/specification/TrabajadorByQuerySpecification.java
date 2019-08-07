package specification;

import model.Trabajador;

public class TrabajadorByQuerySpecification implements MemorySpecification<Trabajador> {

    private final String query;

    public TrabajadorByQuerySpecification(final String query) {
        this.query = query.toLowerCase();
    }

    @Override
    public boolean test(Trabajador value) {
        if (value == null) return false;

        boolean test = false;

        if (value.getRut() != null)
            test = value.getRut().toLowerCase().contains(query);

        if (value.getNombre() != null)
            test |= value.getNombre().toLowerCase().contains(query);

        if (value.getApellido() != null)
            test |= value.getApellido().toLowerCase().contains(query);

        return test;
    }
}
