package specification;

import model.Proyecto;

public final class ProyectoByQuerySpecification implements MemorySpecification<Proyecto> {

    private final String query;

    public ProyectoByQuerySpecification(final String query) {
        this.query = query.toLowerCase();
    }

    @Override
    public boolean test(Proyecto value) {
        if (value == null) return false;

        boolean test = false;

        if (value.getId() != null)
            test = value.getId().toLowerCase().contains(query);

        if (value.getNombre() != null)
            test |= value.getNombre().toLowerCase().contains(query);

        if (value.getNombreCliente() != null)
            test |= value.getNombreCliente().toLowerCase().contains(query);

        if (value.getFechaInicio() != null)
            test |= value.getFechaInicio().toString().contains(query);

        return test;
    }
}
