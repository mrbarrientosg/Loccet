package cl.loccet.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Proyecto {

    private int id;
    private Date fechaInicioProyecto;

    private Date fechaTerminoProyecto;

    private Date fechaTerminoReal;

    private double estimacion;

    private double costoReal;

    private ArrayList<Trabajador> listaTrabajadores;

    private HashMap<String, Trabajador> mapTrabajadores;
    //Implementar inventario.

    public Proyecto(int id,double estimacion,double costoReal, Date fechaInicioProyecto, Date fechaTerminoProyecto, Date fechaTerminoReal) {
        this.id = id;
        this.fechaTerminoReal = fechaTerminoReal;
        this.fechaInicioProyecto = fechaInicioProyecto;
        this.fechaTerminoProyecto = fechaTerminoProyecto;
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

    public Date getFechaInicioProyecto() {
        return fechaInicioProyecto;
    }

    public Date getFechaTerminoProyecto() {
        return fechaTerminoProyecto;
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
}
