package model;

import com.google.gson.JsonObject;
import repository.memory.MemoryRepositoryProyecto;
import repository.memory.MemoryRepositoryTrabajador;
import repository.RepositoryProyecto;
import repository.RepositoryTrabajador;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Constructora {

    private String rut;

    private String nombre;

    private String dns;

    private static Constructora instance;

    private RepositoryProyecto repositoryProyecto;

    private RepositoryTrabajador repositoryTrabajador;

    private Constructora() {
        repositoryProyecto = new MemoryRepositoryProyecto();
        repositoryTrabajador = new MemoryRepositoryTrabajador();
    }

    public static synchronized Constructora getInstance() {
        if (instance == null)
            instance = new Constructora();
        return instance;
    }

    public void init(JsonObject json) {
        rut = json.get("rut").getAsString();
        nombre = json.get("nombre").getAsString();
        dns = json.get("dns").getAsString();
    }

    //MARK - Metodos

    public void agregarProyecto(Proyecto proyecto) {
        repositoryProyecto.add(proyecto);
    }

    public Proyecto eliminarProyecto(String id) {
        return repositoryProyecto.remove(repositoryProyecto.get(id));
    }

    public Trabajador actualizarTrabajador(Trabajador nuevoTrabajador) {
        for (Iterator<Proyecto> it = repositoryProyecto.get(); it.hasNext(); ) {
            Proyecto proyecto = it.next();
            proyecto.actualizarTrabajador(nuevoTrabajador);
        }
        return repositoryTrabajador.update(nuevoTrabajador);
    }

    public void agregarTrabajador(Trabajador trabajador) {
        repositoryTrabajador.add(trabajador);
    }

    /**
     * Agregar un trabajador a un proyecto
     *
     * @param idProyecto id del proyecto
     * @param trabajador Trabajador a guardar
     * @return false si no se pudo agregar y true lo contrario
     * @author Matias Barrientos
     */
    public void agregarTrabajador(String idProyecto, Trabajador trabajador) {
        Proyecto proyecto = repositoryProyecto.get(idProyecto);
        if (proyecto == null) return;
        proyecto.agregarTrabajador(trabajador);
        repositoryTrabajador.add(trabajador);
    }

//    /**
//     * Buscar los trabajadores especificos en un proyecto
//     *
//     * @param idProyecto id del proyecto
//     * @param busqueda   consulta de busqueda
//     * @return Lista de trabajadores encontrados
//     * @author Matias Barrientos
//     */
//    public List<Trabajador> buscarTrabajador(String idProyecto, String busqueda) {
//        if (!mapProyecto.containsKey(idProyecto)) return Collections.EMPTY_LIST;
//        Proyecto aux = mapProyecto.get(idProyecto);
//        return aux.buscarTrabajador(busqueda.toLowerCase());
//    }

    /**
     * Busca a todos los trabajadores en todas las obras
     *
     * @return Lista de trabajadores encontrados
     * @author Matias Barrientos
     */
    /*public List<Trabajador> buscarTrabajador(String busqueda) {
        ArrayList<Trabajador> encontrados = new ArrayList<>();

        for (Object ob : conjuntoTrabajadores.values()) {
            Trabajador trabajador = (Trabajador) ob;

            if (StringUtils.containsIgnoreCase(trabajador.getNombre(), busqueda) ||
                    StringUtils.containsIgnoreCase(trabajador.getRut(), busqueda))
                encontrados.add(trabajador);
        }

        return encontrados;
    }*/

    public Trabajador obtenerTrabajador(String rut) {
        return repositoryTrabajador.get(rut);
    }

    public Trabajador eliminarTrabajador(String rut) {
        if (repositoryTrabajador.get(rut) == null) return null;

        for (Iterator<Proyecto> it = repositoryProyecto.get(); it.hasNext(); ) {
            Proyecto proyecto = it.next();
            proyecto.eliminarTrabajador(rut);
        }

        return repositoryTrabajador.remove(repositoryTrabajador.get(rut));
    }

    /*public Trabajador eliminarTrabajador(String idProyecto, String RUT) {
        if(mapProyecto.get(idProyecto) == null) return null;
        return mapProyecto.get(idProyecto).eliminarTrabajador(RUT);
    }*/

    public Proyecto buscarProyecto(String idProyecto) {
        return repositoryProyecto.get(idProyecto);
    }

    /*public void estimacionGasto(String idProyecto) {
        if (!mapProyecto.containsKey(idProyecto)) return;
        mapProyecto.get(idProyecto).estimacionGasto();
    }*/

    /**
     * Revisa si existe un proyecto basandose en el nombre
     *
     * @param proyecto el cual va a ser ingresado a constructora
     * @return boolean dependiendo de si el proyecto fue ingresado con anterioridad
     * @author Matias Zúñiga
     */
    public boolean existeProyecto(Proyecto proyecto){
        return repositoryProyecto.contains(proyecto);
    }

    //Getter

    public String getRut() {
        return rut;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Trabajador> getConjuntoTrabajadores() {
        // Hay cambiarlo por un iterator
        List<Trabajador> list = new ArrayList<>();
        repositoryTrabajador.get().forEachRemaining(list::add);
        return list;
    }

    public List<Proyecto> getListaProyecto() {
        List<Proyecto> list = new ArrayList<>();
        repositoryProyecto.get().forEachRemaining(list::add);
        return list;
    }


}

