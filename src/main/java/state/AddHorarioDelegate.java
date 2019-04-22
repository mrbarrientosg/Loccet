package state;

import model.Horario;

/**
 * Delegate para cuando se agrega un Horario
 */
public interface AddHorarioDelegate {
    public void didAddHorario(Horario horario);
}
