package model;

import com.google.gson.JsonObject;
import repository.MemorySpecification;
import repository.memory.MemoryStoreProyecto;
import repository.memory.MemoryStoreTrabajador;
import repository.StoreProyecto;
import repository.StoreTrabajador;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Constructora implements Costeable {

    // MARK: - Atributos

    private String rut;

    private String nombre;

    private String dns;

    private StoreProyecto storeProyecto;

    private StoreTrabajador storeTrabajador;

    private static Constructora instance;

    // MARK: - Constructores

    private Constructora() {
        storeProyecto = new MemoryStoreProyecto();
        storeTrabajador = new MemoryStoreTrabajador();
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
        return storeProyecto.findById(idProyecto);
    }

    public void agregarProyecto(Proyecto proyecto) {
        storeProyecto.save(proyecto);
    }

    public Proyecto eliminarProyecto(String idProyecto) {
        return storeProyecto.delete(idProyecto);
    }

    public Iterable<Proyecto> buscarProyecto(MemorySpecification<Proyecto> specification) {
        final List<Proyecto> proyectos = new ArrayList<>();

        for (Proyecto proyecto: storeProyecto.findAll()) {
            if (specification.test(proyecto))
                proyectos.add(proyecto);
        }

        return proyectos;
    }

    // MARK: - Metodos Trabajador

    public Trabajador obtenerTrabajador(String rut) {
        return storeTrabajador.findByRut(rut);
    }

    public void agregarTrabajador(Trabajador trabajador) {
        storeTrabajador.save(trabajador);
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
        Proyecto proyecto = storeProyecto.findById(idProyecto);
        if (proyecto == null) return;
        proyecto.agregarTrabajador(trabajador);
        storeTrabajador.save(trabajador);
    }

    public Trabajador eliminarTrabajador(String rut) {
        if (storeTrabajador.findByRut(rut) == null) return null;

        storeProyecto.findAll().forEach(proyecto -> {
            proyecto.eliminarTrabajador(rut);
        });

        return storeTrabajador.delete(rut);
    }

    public Trabajador eliminarTrabajador(String idProyecto, String rut) {
        Proyecto p = storeProyecto.findById(idProyecto);
        if (p == null) return null;
        return p.eliminarTrabajador(rut);
    }

    /**
     * Buscar los trabajadores especificos en un proyecto
     *
     * @param idProyecto id del proyecto
     * @param specification   consulta de busqueda
     * @return Lista de trabajadores encontrados
     * @author Matias Barrientos
     */
    public Iterable<Trabajador> buscarTrabajador(String idProyecto, MemorySpecification<Trabajador> specification) {
        Proyecto p = storeProyecto.findById(idProyecto);
        if (p == null) return Collections.emptyList();
        return p.buscarTrabajador(specification);
    }

    public Iterable<Trabajador> buscarTrabajador(MemorySpecification<Trabajador> specification) {
        final List<Trabajador> trabajadors = new ArrayList<>();

        for (Trabajador trabajador: storeTrabajador.findAll()) {
            if (specification.test(trabajador))
                trabajadors.add(trabajador);
        }

        return trabajadors;
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
        storeTrabajador.findAll().forEach(list::add);
        return list;
    }

    public List<Proyecto> getListaProyecto() {
        List<Proyecto> list = new ArrayList<>();
        storeProyecto.findAll().forEach(list::add);
        return list;
    }


    @Override
    public BigDecimal calcularCosto() {
        Iterable<Proyecto> iterable = storeProyecto.findAll();
        BigDecimal costoAproximado = new BigDecimal(0);

        iterable.forEach(proyecto -> {
            costoAproximado.add(proyecto.calcularCosto());
        });

        return costoAproximado;
    }
}

