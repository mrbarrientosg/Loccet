package cell;

import model.Dias;
import model.Horario;

import java.time.LocalTime;

public class HorarioCell {

    private String id;

    private Integer dia;

    private String nombreProyecto;

    private LocalTime fechaInicio;

    private LocalTime fechaTermino;

    public HorarioCell(Horario h) {
        id = h.getId();
        dia = h.getDia();
        nombreProyecto = h.getNombreProyecto();
        fechaInicio = h.getFechaInicio();
        fechaTermino = h.getFechaTermino();
    }

    public final String getId() {
        return id;
    }

    public final Integer getDia() {
        return dia;
    }

    public final String dia() {
        return Dias.getNameDay(dia);
    }

    public final String getNombreProyecto() {
        return nombreProyecto;
    }

    public final LocalTime getFechaInicio() {
        return fechaInicio;
    }

    public final LocalTime getFechaTermino() {
        return fechaTermino;
    }
}
