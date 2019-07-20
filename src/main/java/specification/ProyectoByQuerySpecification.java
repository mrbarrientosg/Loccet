package specification;

import model.Proyecto;
import model.store.MemorySpecification;

public class ProyectoByQuerySpecification implements MemorySpecification<Proyecto> {

    private final String query;

    public ProyectoByQuerySpecification(final String query) {
        this.query = query;
    }

    @Override
    public boolean test(Proyecto value) {
        if (value == null) return false;

        boolean test = false;

        if (value.getId() != null)
            test |= value.getId().contains(query);

        if (value.getNombre() != null)
            test |= value.getNombre().contains(query);

        if (value.getNombreCliente() != null)
            test |= value.getNombreCliente().contains(query);

        if (value.getFechaInicio() != null)
            test |= value.getFechaInicio().toString().contains(query);

        return test;
    }
}
