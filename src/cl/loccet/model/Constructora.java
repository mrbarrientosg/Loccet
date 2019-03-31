package cl.loccet.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Constructora {

    private String rut;

    private String nombre;

    private ArrayList<Proyecto> listaProyecto;
  
    private HashMap<Integer, Proyecto> mapProyecto;

    public Constructora(String rut, String nombre) {
        this.rut = rut;
        this.nombre = nombre;
        listaProyecto = new ArrayList<>();
        mapProyecto = new HashMap<>();
    }
    //Setter

    public void setRut(String rut) {
        this.rut = rut;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //Getter

    public String getRut() {
        return rut;
    }

    public String getNombre() {
        return nombre;
    }

    //Metodos

    public void agregarProyecto(Proyecto proyecto){
        listaProyecto.add(proyecto);
        mapProyecto.put(proyecto.getId(),proyecto);
    }

    public boolean agregarTrabajador(int id, Trabajador trabajador){
        if(mapProyecto.get(id) == null) return false;
        mapProyecto.get(id).agregarTrabajador(trabajador);
        return true;
    }

    public ArrayList<Trabajador> buscarTrabajador(int idProyecto, String busqueda) {
        if(mapProyecto.get(idProyecto) == null) return null;
        Proyecto aux = mapProyecto.get(idProyecto);
        return aux.buscarTrabajador(busqueda.toLowerCase());
    }

    /**
     * Busca a todos los trabajadores en todas las obras
     * @param busqueda Forma de como se quiere buscar
     * @return Lista de trabajadores encontrados
     */
    public ArrayList<Trabajador> buscarTrabajador(String busqueda) {
        ArrayList<Trabajador> encontrados = new ArrayList<>();

        ArrayList<Trabajador> aux;

        for (Proyecto proyecto: listaProyecto) {

            aux = proyecto.buscarTrabajador(busqueda.toLowerCase());

            if (aux.size() != 0)
                encontrados.addAll(aux);
        }

        return encontrados;
    }
}

