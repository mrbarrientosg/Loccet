package cl.loccet.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Proyecto {

    private int id;

    private double estimacion;

    private double costoReal;

    private ArrayList<Trabajador> listaTrabajadores;

    private HashMap<String, Trabajador> mapTrabajadores;
    //Implementar inventario.

    public Proyecto(int id,double estimacion,double costoReal) {
        this.id = id;
        listaTrabajadores = new ArrayList<>();
        mapTrabajadores = new HashMap<>();
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
            //Mostrar por pantalla.****
        }
    }


}
