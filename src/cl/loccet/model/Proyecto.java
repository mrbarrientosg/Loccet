package cl.loccet.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Proyecto {

    // MARK: - Variables
    private String id;
    private String nombreProyecto;
    private String jefeProyecto;
    private String mailCliente;
    private String telefonoCliente;
    private String direccion;
    private String pais;
    private String ciudad;
    private String estado;
    private String fechaInicio;
    private String fechaTermino;
    private String fechaTerminoReal;
    private double estimacion;
    private double costoReal;
    private ArrayList<Trabajador> listaTrabajadores;
    private HashMap<String, Trabajador> mapTrabajadores;

    // TODO: Implementar inventario materiales.

    // TODO: Implementar equipo y maquinarias.


    // MARK: - Constructor


    public Proyecto(String id, String nombreProyecto, String jefeProyecto, String mailCliente, String telefonoCliente, String direccion, String pais, String ciudad, String estado, String fechaInicio, String fechaTermino, double estimacion) {
        this.id = id;
        this.nombreProyecto = nombreProyecto;
        this.jefeProyecto = jefeProyecto;
        this.mailCliente = mailCliente;
        this.telefonoCliente = telefonoCliente;
        this.direccion = direccion;
        this.pais = pais;
        this.ciudad = ciudad;
        this.estado = estado;
        this.fechaInicio = fechaInicio;
        this.fechaTermino = fechaTermino;
        this.estimacion = estimacion;
        listaTrabajadores = new ArrayList<>();
        mapTrabajadores = new HashMap<>();
    }

    public Proyecto(String id, double estimacion, double costoReal, String fechaInicio, String fechaTermino, String fechaTerminoReal) {
        this.id = id;
        this.fechaTerminoReal = fechaTerminoReal;
        this.fechaInicio = fechaInicio;
        this.fechaTermino = fechaTermino;
        this.estimacion = estimacion;
        this.costoReal = costoReal;
        listaTrabajadores = new ArrayList<>();
        mapTrabajadores = new HashMap<>();
    }

    // MARK: - Setter

    public void setFechaTerminoReal(String fechaTerminoReal){
        this.fechaTerminoReal = fechaTerminoReal;
    }

    public void setCostoReal(double costoReal) {
        this.costoReal = costoReal;
    }

    // MARK: - Getter

    public String getFechaTerminoReal() {
        return fechaTerminoReal;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public String getFechaTermino() {
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

    public double getCostoReal() {
        return costoReal;
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
        if (mapTrabajadores.get(trabajador.getRut()) == null){
            mapTrabajadores.put(trabajador.getRut(), trabajador);
            listaTrabajadores.add(trabajador);
            return true;
        }
        return false;
    }
    /**
     * Muestra los trabajadores que estan asociado al proyecto
     *
     * @author Sebastian Fuenzalida
     */
    public void mostrarTrabajadores(){
        for (int i = 0; i < listaTrabajadores.size(); i++){
            //TODO: Mostrar por pantalla.****
        }
    }
    /**
     * Busca todos los trabajadores que coincidan con la busqueda
     * @param busqueda Texto de Busqueda
     * @return Lista de Trabajadores encontrados
     *
     * @author Matias Barrientos
     */
    public ArrayList<Trabajador> buscarTrabajador(String busqueda) {
        ArrayList<Trabajador> encontrados = new ArrayList<>();

        for (Trabajador trabajador: listaTrabajadores) {
            if (trabajador.getNombre().toLowerCase().contains(busqueda))
                encontrados.add(trabajador);
        }

        return encontrados;
    }

    public Trabajador eliminarTrabajador(String RUT) {
        if (mapTrabajadores.get(RUT) == null) return null;
        listaTrabajadores.remove(mapTrabajadores.get(RUT));
        return mapTrabajadores.remove(RUT);
    }
}
