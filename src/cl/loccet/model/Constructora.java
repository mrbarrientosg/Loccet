package cl.loccet.model;

import cl.loccet.util.StringUtils;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.HashMap;

public class Constructora {

    private String rut;

    private String nombre;

    private ArrayList<Proyecto> listaProyecto;

    private HashMap<String, Proyecto> mapProyecto;

    private HashMap<String, Trabajador> conjuntoTrabajadores;

    public Constructora(String rut, String nombre) {
        this.rut = rut;
        this.nombre = nombre;
        listaProyecto = new ArrayList<>();
        mapProyecto = new HashMap<>();
        conjuntoTrabajadores = new HashMap<>();
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

    public List<Trabajador> getConjuntoTrabajadores() {
        return Collections.unmodifiableList(new ArrayList<>(conjuntoTrabajadores.values()));
    }

    //Metodos

    public void agregarProyecto(Proyecto proyecto){
        listaProyecto.add(proyecto);
        mapProyecto.put(proyecto.getId() ,proyecto);
    }

    public Proyecto eliminarProyecto(String id){
        return mapProyecto.remove(id);
    }

    public Trabajador actualizarTrabajador(Trabajador nuevoTrabajador) {
        return conjuntoTrabajadores.put(nuevoTrabajador.getRut(), nuevoTrabajador);
    }


    public boolean agregarTrabajador(Trabajador trabajador){
        if (conjuntoTrabajadores.get(trabajador.getRut()) != null) return false;
        conjuntoTrabajadores.put(trabajador.getRut(), trabajador);
        return true;
    }

    /**
     * Agregar un trabajador a un proyecto
     * @param idProyecto id del proyecto
     * @param trabajador Trabajador a guardar
     * @return false si no se pudo agregar y true lo contrario
     *
     * @author Matias Barrientos
     */
    public boolean agregarTrabajador(String idProyecto, Trabajador trabajador){
        if(mapProyecto.get(idProyecto) == null) return false;
        mapProyecto.get(idProyecto).agregarTrabajador(trabajador);
        conjuntoTrabajadores.put(trabajador.getRut(), trabajador);
        return true;
    }

    /**
     * Buscar los trabajadores especificos en un proyecto
     * @param idProyecto id del proyecto
     * @param busqueda consulta de busqueda
     * @return Lista de trabajadores encontrados
     *
     * @author Matias Barrientos
     */
    public ArrayList<Trabajador> buscarTrabajador(String idProyecto, String busqueda) {
        if(mapProyecto.get(idProyecto) == null) return null;
        Proyecto aux = mapProyecto.get(idProyecto);
        return aux.buscarTrabajador(busqueda.toLowerCase());
    }

    /**
     * Busca a todos los trabajadores en todas las obras
     * @param busqueda Forma de como se quiere buscar
     * @return Lista de trabajadores encontrados
     *
     * @author Matias Barrientos
     */
    public ArrayList<Trabajador> buscarTrabajador(String busqueda) {
        ArrayList<Trabajador> encontrados = new ArrayList<>();

        for (Object ob: conjuntoTrabajadores.values()) {
            Trabajador trabajador = (Trabajador) ob;

            if (StringUtils.containsIgnoreCase(trabajador.getNombre(), busqueda) ||
                    StringUtils.containsIgnoreCase(trabajador.getRut(), busqueda))
                encontrados.add(trabajador);
        }

        return encontrados;
    }

    public Trabajador eliminarTrabajador(String rut) {
        if (!conjuntoTrabajadores.containsKey(rut)) return null;

        for (Proyecto proyecto: listaProyecto) {
            Trabajador t = proyecto.eliminarTrabajador(rut);
            if (t != null) break;
        }

        return conjuntoTrabajadores.remove(rut);
    }

    /*public Trabajador eliminarTrabajador(String idProyecto, String RUT) {
        if(mapProyecto.get(idProyecto) == null) return null;
        return mapProyecto.get(idProyecto).eliminarTrabajador(RUT);
    }*/

    public Proyecto buscarProyecto(String idProyecto) {
        return mapProyecto.get(idProyecto);
    }

    public List<Proyecto> getListaProyecto() {
        return Collections.unmodifiableList(listaProyecto);
    }
}

