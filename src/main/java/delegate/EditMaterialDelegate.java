package delegate;

import model.Material;

/**
 * Delegate cuando se edita un material
 */
public interface EditMaterialDelegate {
    public void didEditMaterial(Material material);
}
