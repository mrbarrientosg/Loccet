package cl.loccet.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Proyecto {

    // MARK: - Variables
    private int id;

    private Date fechaInicio;

    private Date fechaTermino;

    private Date fechaTerminoReal;

    private double estimacion;

    private double costoReal;

    private ArrayList<Trabajador> listaTrabajadores;

    private HashMap<String, Trabajador> mapTrabajadores;

    // TODO: Implementar inventario materiales.

    // TODO: Implementar equipo y maquinarias.


    // MARK: - Constructor

    public Proyecto(int id, double estimacion, double costoReal, Date fechaInicio, Date fechaTermino, Date fechaTerminoReal) {
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

    public void setFechaTerminoReal(Date fechaTerminoReal){
        this.fechaTerminoReal = fechaTerminoReal;
    }

    // MARK: - Getter

    public Date getFechaTerminoReal() {
        return fechaTerminoReal;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaTermino() {
        return fechaTermino;
    }

    public int getId() {
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
