package cl.loccet.model;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

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
    private LocalDate fechaTerminoReal;
    private double estimacion;

    private double costoReal;

    private ArrayList<Trabajador> listaTrabajadores;

    private HashMap<String, Trabajador> mapTrabajadores;


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
    }

    public Proyecto(String id, double estimacion, double costoReal, LocalDate fechaInicio, LocalDate fechaTermino, LocalDate fechaTerminoReal) {
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

    public void setFechaTerminoReal(LocalDate fechaTerminoReal){
        this.fechaTerminoReal = fechaTerminoReal;
    }

    public void setCostoReal(double costoReal) {
        this.costoReal = costoReal;
    }

    // MARK: - Getter

    public LocalDate getFechaTerminoReal() {
        return fechaTerminoReal;
    }

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
        private String fechaTerminoReal;
        private double estimacion;
        private double costoReal;

        public Builder(String id, String nombreProyecto, String jefeProyecto, Double estimacion, String cliente){
            if(id == null || nombreProyecto == null){
                throw new IllegalArgumentException("id, nombreProyecto, jefeProyecto, estimacion, Cliente no pueden estar vacias");
            }
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

        public Builder fechaProyecto(LocalDate fechaInicio, LocalDate fechaTermino/*, String fechaTerminoReal*/) {
            this.fechaInicio = fechaInicio;
            this.fechaTermino = fechaTermino;
            // this.fechaTerminoReal = fechaTerminoReal;
            return this;
        }
        public Proyecto build(){
            return new Proyecto(this);
        }
    }


}
