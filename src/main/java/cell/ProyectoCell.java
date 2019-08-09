package cell;

import model.Proyecto;
import util.DateUtils;

import java.text.DecimalFormat;
import java.util.Currency;
import java.util.Locale;

/**
 * Clase que ayuda a desplegar un proyecto de forma
 * mas facil para la vista
 *
 * @author Matias Zuñiga
 */
public final class ProyectoCell {

    // MARK: - Variables

    private String id;

    private String nombre;

    private String cliente;

    private String fechaInicio;

    private String fechaTermino;

    private String estimacion;

    // MARK: - Constructor

    public ProyectoCell(Proyecto m) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(0);
        df.setCurrency(Currency.getInstance(Locale.getDefault()));

        id = m.getId();
        nombre = m.getNombre();
        fechaInicio = DateUtils.formatDate(m.getFechaInicio());
        fechaTermino = m.getFechaTermino() == null ? "-" : DateUtils.formatDate(m.getFechaTermino());
        cliente = m.getNombreCliente();
        estimacion = "$" + df.format(m.getCostoEstimado());
    }

    public ProyectoCell(String nombre) {
        this.nombre = nombre;
    }

    // MARK: - Getter

    public String getFechaInicio() {
        return fechaInicio;
    }

    public String getFechaTermino() {
        return fechaTermino;
    }

    public String getNombre() {
        return nombre;
    }

    public String getId() {
        return id;
    }

    public String getCliente() {
        return cliente;
    }

    public String getEstimacion() {
        return estimacion;
    }



}

