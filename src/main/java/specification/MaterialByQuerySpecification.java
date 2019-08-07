package specification;

import model.Material;

public class MaterialByQuerySpecification implements MemorySpecification<Material> {

    private final String query;

    public MaterialByQuerySpecification(final String query) {
        this.query = query.toLowerCase();
    }

    @Override
    public boolean test(Material value) {
        if (value == null) return false;

        boolean test = false;

        if (value.getId() != null)
            test = value.getId().toLowerCase().contains(query);

        if (value.getNombre() != null)
            test |= value.getNombre().toLowerCase().contains(query);

        if (value.getDescripcion() != null)
            test |= value.getDescripcion().toLowerCase().contains(query);

        if (value.getUds() != null)
            test |= value.getUds().toLowerCase().contains(query);

        return test;
    }
}
