package cl.loccet.model;
import cl.loccet.util.StringUtils;

import java.time.LocalDate;
import java.util.*;

/**
 * Clase modelo usada para el modelo material
 * @author Matias Zuñiga
 */

public class Proyecto {

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

    private Map<String, Trabajador> mapTrabajadores;

    private InventarioMaterial inventarioMaterial;


    // TODO: Implementar inventario materiales.

    // TODO: Implementar equipo y maquinarias.


    // MARK: - Constructor

    private Proyecto(Builder builder){
        this.id = builder.id;
        this.nombreProyecto = builder.nombreProyecto;
        this.jefeProyecto = builder.jefeProyecto;
        this.estimacion = builder.estimacion;
        this.cliente = builder.cliente;
        this.telefonoCliente = builder.telefonoCliente;
        this.mailCliente = builder.mailCliente;
        this.direccion = builder.direccion;
        this.pais = builder.pais;
        this.estado = builder.estado;
        this.ciudad = builder.ciudad;
        this.fechaInicio = builder.fechaInicio;
        this.fechaTermino = builder.fechaTermino;

        listaTrabajadores = new ArrayList<>();
        mapTrabajadores = new HashMap<>();

        inventarioMaterial = new InventarioMaterial();
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

    // MARK: - Metodos

    /**
     * Agrega un nuevo trabajador al proyecto
     * @param trabajador trabajador a guardar
     * @return true si pudo agregarlo, en caso contrario false
     *
     * @author Matias Zuñiga
     */
    public boolean agregarTrabajador(Trabajador trabajador){
        if (mapTrabajadores.containsKey(trabajador.getRut())) return false;
        mapTrabajadores.put(trabajador.getRut(), trabajador);
        listaTrabajadores.add(trabajador);
        return true;
    }

    /**
     * Modifica un trabajador previamente ingresado
     * @param nuevoTrabajador Texto de Busqueda
     *
     * @author Matias Barrientos
     */


    public Trabajador actualizarTrabajador(Trabajador nuevoTrabajador) {
        if (!mapTrabajadores.containsKey(nuevoTrabajador.getRut())) return null;
        int idx = listaTrabajadores.indexOf(mapTrabajadores.get(nuevoTrabajador.getRut()));
        listaTrabajadores.set(idx, nuevoTrabajador);
        return mapTrabajadores.put(nuevoTrabajador.getRut(), nuevoTrabajador);
    }

    /**
     * Busca todos los trabajadores que coincidan con la busqueda
     * @param busqueda Texto de Busqueda
     * @return Lista de Trabajadores encontrados
     *
     * @author Matias Barrientos
     */
    public List<Trabajador> buscarTrabajador(String busqueda) {
        ArrayList<Trabajador> encontrados = new ArrayList<>();

        for (Trabajador trabajador: listaTrabajadores) {
            if (StringUtils.containsIgnoreCase(trabajador.getNombre(), busqueda) ||
                    StringUtils.containsIgnoreCase(trabajador.getRut(), busqueda))
                encontrados.add(trabajador);
        }

        return encontrados;
    }
    /**
     * Obtiene al trabajador el cual coincida con el rut.
     * @param rut del trabajador.
     * @return Trabajador encontrado.
     *
     * @author Matias Barrientos
     */

    public Trabajador obtenerTrabajador(String rut) {
        return mapTrabajadores.get(rut);
    }
    /**
     * Elimina al trabajador que coincida con el rut.
     * @param rut del trabajador.
     * @return El trabajador eliminado
     *
     * @author Matias Barrientos
     */

    public Trabajador eliminarTrabajador(String rut) {
        if (!mapTrabajadores.containsKey(rut)) return null;
        listaTrabajadores.remove(mapTrabajadores.get(rut));
        return mapTrabajadores.remove(rut);
    }

    public static class Builder {
        private final String id;
        private String nombreProyecto;
        private String jefeProyecto;
        private String cliente;
        private String mailCliente;
        private String telefonoCliente;
        private String direccion;
        private String pais;
        private String ciudad;
        private String estado;
        private LocalDate fechaInicio;
        private LocalDate fechaTermino;
        private double estimacion;
        private double costoReal;

        public Builder(String id, String nombreProyecto, String jefeProyecto, Double estimacion, String cliente){
            this.id = id;
            this.nombreProyecto = nombreProyecto;
            this.jefeProyecto = jefeProyecto;
            this.estimacion = estimacion;
            this.cliente = cliente;
        }

        public Builder datosCliente(String mailCliente, String telefonoCliente) {
            this.mailCliente = mailCliente;
            this.telefonoCliente = telefonoCliente;
            return this;
        }

        public Builder datosUbicacion(String direccion, String pais, String ciudad, String estado) {
            this.direccion = direccion;
            this.pais = pais;
            this.ciudad = ciudad;
            this.estado = estado;
            return this;
        }

        public Builder fechaProyecto(LocalDate fechaInicio, LocalDate fechaTermino) {
            this.fechaInicio = fechaInicio;
            this.fechaTermino = fechaTermino;
            return this;
        }

        public Proyecto build(){
            return new Proyecto(this);
        }
    }


}
