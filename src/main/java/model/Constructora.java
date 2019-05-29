package model;

import com.google.gson.JsonObject;
import repository.Specification;
import repository.memory.MemoryRepositoryProyecto;
import repository.memory.MemoryRepositoryTrabajador;
import repository.RepositoryProyecto;
import repository.RepositoryTrabajador;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Constructora implements Costeable {

    // MARK: - Atributos

    private String rut;

    private String nombre;

    private String dns;

    private RepositoryProyecto repositoryProyecto;

    private RepositoryTrabajador repositoryTrabajador;

    private static Constructora instance;

    // MARK: - Constructores

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

    // MARK: - Metodos Proyecto

    public Proyecto obtenerProyecto(String idProyecto) {
        return repositoryProyecto.get(idProyecto);
    }

    public void agregarProyecto(Proyecto proyecto) {
        repositoryProyecto.add(proyecto);
    }

    public Proyecto eliminarProyecto(String idProyecto) {
        return repositoryProyecto.remove(repositoryProyecto.get(idProyecto));
    }

    public Iterable<Proyecto> buscarProyecto(Specification busqueda) {
        return repositoryProyecto.get(busqueda);
    }

    // MARK: - Metodos Trabajador

    public Trabajador obtenerTrabajador(String rut) {
        return repositoryTrabajador.get(rut);
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

    public Trabajador actualizarTrabajador(Trabajador nuevoTrabajador) {
        return repositoryTrabajador.update(nuevoTrabajador);
    }

    public Trabajador eliminarTrabajador(String rut) {
        if (repositoryTrabajador.get(rut) == null) return null;

        repositoryProyecto.get().forEach(proyecto -> {
            proyecto.eliminarTrabajador(rut);
        });

        return repositoryTrabajador.remove(repositoryTrabajador.get(rut));
    }

    public Trabajador eliminarTrabajador(String idProyecto, String rut) {
        Proyecto p = repositoryProyecto.get(idProyecto);
        if (p == null) return null;
        return p.eliminarTrabajador(rut);
    }

    /**
     * Buscar los trabajadores especificos en un proyecto
     *
     * @param idProyecto id del proyecto
     * @param busqueda   consulta de busqueda
     * @return Lista de trabajadores encontrados
     * @author Matias Barrientos
     */
    public Iterable<Trabajador> buscarTrabajador(String idProyecto, Specification busqueda) {
        Proyecto p = repositoryProyecto.get(idProyecto);
        if (p == null) return Collections.emptyList();
        return p.buscarTrabajador(busqueda);
    }

    public Iterable<Trabajador> buscarTrabajador(Specification busqueda) {
        return repositoryTrabajador.get(busqueda);
    }


    // MARK: - Getter

    public String getRut() {
        return rut;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDns() {
        return dns;
    }

    public List<Trabajador> getConjuntoTrabajadores() {
        // Hay cambiarlo por un iterator
        List<Trabajador> list = new ArrayList<>();
        repositoryTrabajador.get().forEach(list::add);
        return list;
    }

    public List<Proyecto> getListaProyecto() {
        List<Proyecto> list = new ArrayList<>();
        repositoryProyecto.get().forEach(list::add);
        return list;
    }


    @Override
    public BigDecimal calcularCosto() {
        Iterable<Proyecto> iterable = repositoryProyecto.get();
        BigDecimal costoAproximado = new BigDecimal(0);

        iterable.forEach(proyecto -> {
            costoAproximado.add(proyecto.calcularCosto());
        });

        return costoAproximado;
    }
}

