package specification;

import model.Material;
import model.store.MemorySpecification;

public class MaterialByQuerySpecification implements MemorySpecification<Material> {

    private final String query;

    public MaterialByQuerySpecification(final String query) {
        this.query = query;
    }

    @Override
    public boolean test(Material value) {
        if (value == null) return false;

        boolean test = false;

        if (value.getId() != null)
            test |= value.getId().contains(query);

        if (value.getNombre() != null)
            test |= value.getNombre().contains(query);

        if (value.getDescripcion() != null)
            test |= value.getDescripcion().contains(query);

        if (value.getUds() != null)
            test |= value.getUds().contains(query);

        return test;
    }
}
