package cell;

import model.Trabajador;
import model.TrabajadorPartTime;
import model.TrabajadorTiempoCompleto;
import util.StringUtils;

/**
 * Clase que ayuda a desplegar un trabajador
 * de forma mas facil para la vista
 *
 * @author Matias Barrientos
 */
public final class TrabajadorCell {

    private String rut;

    private String nombre;

    private String apellido;

    private String nombreEspecialidad;

    private String telefono;

    private String correoElectronico;

    private String tipoTrabajador;

    private Integer horasPorDia;

    public TrabajadorCell(Trabajador t) {
        this.rut = t.getRut();
        this.nombre = t.getNombre();
        this.apellido = t.getApellido();
        this.nombreEspecialidad = t.getEspecialidad().getNombre();
        this.telefono = t.getTelefono();
        this.correoElectronico = StringUtils.isEmpty(t.getCorreoElectronico()) ? "-" : t.getCorreoElectronico();
        this.tipoTrabajador = (t instanceof TrabajadorTiempoCompleto) ? "Tiempo Completo" : "Part Time";
        this.horasPorDia = (t instanceof TrabajadorTiempoCompleto) ? 8 : ((TrabajadorPartTime) t).getCantidadHoraTrabajada();
    }

    public String getRut() {
        return rut;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNombreEspecialidad() {
        return nombreEspecialidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public String getTipoTrabajador() {
        return tipoTrabajador;
    }

    public Integer getHorasPorDia() {
        return horasPorDia;
    }
}
