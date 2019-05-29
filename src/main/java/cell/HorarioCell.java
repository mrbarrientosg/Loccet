package cell;

import util.Dias;
import model.Horario;

public final class HorarioCell {

    private Integer id;

    private Integer dia;

    private String nombreProyecto;

    private String horaInicio;

    private String horaFin;

    public HorarioCell(Horario h) {
        id = h.getId();
        dia = h.getDia();
        nombreProyecto = h.getProyecto().getNombre();
        horaInicio = h.getHoraInicio().toString();
        horaFin = h.getHoraFin().toString();
    }

    public final Integer getId() { return id; }

    public final Integer getDia() {
        return dia;
    }

    public final String dia() {
        return Dias.getNameDay(dia);
    }

    public final String getNombreProyecto() {
        return nombreProyecto;
    }

    public final String getHoraInicio() {
        return horaInicio;
    }

    public final String getHoraFin() {
        return horaFin;
    }

}
