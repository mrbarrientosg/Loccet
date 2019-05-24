package cell;
import model.InventarioMaterial;
import model.Proyecto;
import model.Trabajador;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


public final class ProyectoCell {

    // MARK: - Variables

    private String id;

    private String nombreProyecto;

    private String cliente;

    private LocalDate fechaInicio;

    private LocalDate fechaTermino;

    private BigDecimal estimacion;

    // MARK: - Constructor

    public ProyectoCell(Proyecto m) {
        id = m.getId();
        nombreProyecto = m.getNombre();
        fechaInicio = m.getFechaInicio();
        fechaTermino = m.getFechaTermino();
        cliente = m.getNombreCliente();
        estimacion = m.getEstimacion();
    }

    public ProyectoCell(String id) {
        this.id = id;
    }

    // MARK: - Getter

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaTermino() {
        return fechaTermino;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public String getId() {
        return id;
    }

    public String getCliente() {
        return cliente;
    }

    public BigDecimal getEstimacion() {
        return estimacion;
    }
}

