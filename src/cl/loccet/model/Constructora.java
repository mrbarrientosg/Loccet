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

    private List<Proyecto> listaProyecto;

    private Map<String, Proyecto> mapProyecto;

    private Map<String, Trabajador> conjuntoTrabajadores;

    public Constructora(String rut, String nombre) {
        this.rut = rut;
        this.nombre = nombre;
        listaProyecto = new ArrayList<>();
        mapProyecto = new HashMap<>();
        conjuntoTrabajadores = new HashMap<>();
    }

    //Metodos

    public boolean agregarProyecto(Proyecto proyecto){
        if (listaProyecto.contains(proyecto) || mapProyecto.containsKey(proyecto.getId()))
            return false;

        listaProyecto.add(proyecto);
        mapProyecto.put(proyecto.getId(), proyecto);

        return true;
    }

    public Proyecto eliminarProyecto(String id) {
        if (!mapProyecto.containsKey(id)) return null;
        listaProyecto.remove(mapProyecto.get(id));
        return mapProyecto.remove(id);
    }

    public Trabajador actualizarTrabajador(Trabajador nuevoTrabajador) {
        for (Proyecto proyecto: listaProyecto) {
            proyecto.actualizarTrabajador(nuevoTrabajador);
        }
        return conjuntoTrabajadores.put(nuevoTrabajador.getRut(), nuevoTrabajador);
    }

    public boolean agregarTrabajador(Trabajador trabajador){
        if (conjuntoTrabajadores.containsKey(trabajador.getRut())) return false;
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
        if (!mapProyecto.containsKey(idProyecto)) return false;
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
    public List<Trabajador> buscarTrabajador(String idProyecto, String busqueda) {
        if (!mapProyecto.containsKey(idProyecto)) return Collections.EMPTY_LIST;
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
    public List<Trabajador> buscarTrabajador(String busqueda) {
        ArrayList<Trabajador> encontrados = new ArrayList<>();

        for (Object ob: conjuntoTrabajadores.values()) {
            Trabajador trabajador = (Trabajador) ob;

            if (StringUtils.containsIgnoreCase(trabajador.getNombre(), busqueda) ||
                    StringUtils.containsIgnoreCase(trabajador.getRut(), busqueda))
                encontrados.add(trabajador);
        }

        return encontrados;
    }

    public Trabajador obtenerTrabajador(String rut) {
        return conjuntoTrabajadores.get(rut);
    }

    public Trabajador eliminarTrabajador(String rut) {
        if (!conjuntoTrabajadores.containsKey(rut)) return null;

        for (Proyecto proyecto: listaProyecto) {
            proyecto.eliminarTrabajador(rut);
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

    public List<Proyecto> getListaProyecto() {
        return Collections.unmodifiableList(listaProyecto);
    }
}

