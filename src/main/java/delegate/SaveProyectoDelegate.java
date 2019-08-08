package delegate;

import model.Proyecto;

/**
 * Delegate cuando se guarda un proyecto
 */
public interface SaveProyectoDelegate {
    public void didSaveProyecto(Proyecto proyecto);
}
