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

    private String nombre;

    private String cliente;

    private LocalDate fechaInicio;

    private LocalDate fechaTermino;

    private BigDecimal estimacion;

    // MARK: - Constructor

    public ProyectoCell(Proyecto m) {
        id = m.getId();
        nombre = m.getNombre();
        fechaInicio = m.getFechaInicio();
        fechaTermino = m.getFechaTermino();
        cliente = m.getNombreCliente();
        estimacion = m.getEstimacion();
    }

    public ProyectoCell(String nombre) {
        this.nombre = nombre;
    }

    // MARK: - Getter

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaTermino() {
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

    public BigDecimal getEstimacion() {
        return estimacion;
    }



}

