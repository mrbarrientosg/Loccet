package delegate;

import model.Trabajador;

/**
 * Delegate cuando se busca un trabajador
 */
public interface SearchEmployeeDelegate {
    public void selectedEmployee(Trabajador value);
}
