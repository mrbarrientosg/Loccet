package cl.loccet.model;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Proyecto {

    private int id;
    private Date fechaInicio;

    private Date fechaTermino;

    private Date fechaTerminoReal;

    private double estimacion;

    private double costoReal;

    private ArrayList<Trabajador> listaTrabajadores;

    private HashMap<String, Trabajador> mapTrabajadores;
    //Implementar inventario.

    public Proyecto(int id,double estimacion,double costoReal, Date fechaInicio, Date fechaTermino, Date fechaTerminoReal) {
        this.id = id;
        this.fechaTerminoReal = fechaTerminoReal;
        this.fechaInicio = fechaInicio;
        this.fechaTermino = fechaTermino;
        this.estimacion = estimacion;
        this.costoReal = costoReal;
        listaTrabajadores = new ArrayList<>();
        mapTrabajadores = new HashMap<>();
    }

    public void setFechaTerminoReal(Date fechaTerminoReal){
        this.fechaTerminoReal = fechaTerminoReal;
    }

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

    public boolean agregarTrabajador(Trabajador trabajador){
        if (mapTrabajadores.get(trabajador.getRut()) == null){
            mapTrabajadores.put(trabajador.getRut(),trabajador);
            listaTrabajadores.add(trabajador);
            return true;
        }
        return false;
    }

    public void mostrarTrabajadores(){
        for (int i = 0; i < listaTrabajadores.size(); i++){
            //TODO: Mostrar por pantalla.****
        }
    }

    public ArrayList<Trabajador> buscarTrabajador(String busqueda) {
        ArrayList<Trabajador> encontrados = new ArrayList<>();

        for (Trabajador trabajador: listaTrabajadores) {
            if (trabajador.getNombre().toLowerCase().contains(busqueda))
                encontrados.add(trabajador);
        }

        return encontrados;
    }
}
