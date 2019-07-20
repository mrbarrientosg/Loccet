package cell;
import model.Proyecto;
import util.DateUtils;

import java.math.BigDecimal;
import java.time.LocalDate;


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
        id = m.getId();
        nombre = m.getNombre();
        fechaInicio = DateUtils.formatDate(m.getFechaInicio());
        fechaTermino = m.getFechaTermino() == null ? "-" : DateUtils.formatDate(m.getFechaInicio());
        cliente = m.getNombreCliente();
        estimacion = m.getCostoEstimado().toString();
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

