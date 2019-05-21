package cell;
import model.InventarioMaterial;
import model.Proyecto;
import model.Trabajador;
import java.time.LocalDate;
import java.util.List;


public final class ProyectoCell {

    // MARK: - Variables

    private String id;

    private String nombreProyecto;

    private String jefeProyecto;

    private String mailCliente;

    private String cliente;

    private String telefonoCliente;

    private String direccion;

    private String pais;

    private String ciudad;

    private String estado;

    private LocalDate fechaInicio;

    private LocalDate fechaTermino;

    private double estimacion;

    private List<Trabajador> listaTrabajadores;

    private InventarioMaterial inventarioMaterial;

    // MARK: - Constructor

    public ProyectoCell(Proyecto m) {
            id = m.getId();
            nombreProyecto = m.getNombreProyecto();
            fechaInicio = m.getFechaInicio();
            fechaTermino = m.getFechaTermino();
            cliente = m.getCliente();
            estimacion = m.getEstimacion();
            inventarioMaterial = m.getInventarioMaterial();
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

    public String getJefeProyecto() {
        return jefeProyecto;
    }

    public String getCliente(){
        return cliente;
    }

    public String getMailCliente() {
        return mailCliente;
    }

    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getPais() {
        return pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public String getId() {
        return id;
    }

    public double getEstimacion() {
        return estimacion;
    }

    public InventarioMaterial getInventarioMaterial() {
        return inventarioMaterial;
    }
}

