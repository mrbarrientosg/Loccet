package model;

import com.google.gson.JsonObject;
import model.store.MemorySpecification;
import model.store.memory.MemoryStoreProyecto;
import model.store.memory.MemoryStoreTrabajador;
import model.store.StoreProyecto;
import model.store.StoreTrabajador;

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
        if (storeProyecto.contains(proyecto))
            return;

        storeProyecto.save(proyecto);
    }

    public Proyecto eliminarProyecto(String idProyecto) {
        Proyecto proyecto = storeProyecto.findById(idProyecto);

        if (proyecto == null)
            return null;

        storeProyecto.delete(proyecto);

        proyecto.limpiar();

        return proyecto;
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

    public Trabajador obtenerTrabajador(String rut, String idProyecto) {
        Proyecto proyecto = storeProyecto.findById(idProyecto);

        if (proyecto == null)
            return null;

        return proyecto.obtenerTrabajador(rut);
    }


    public void agregarTrabajador(Trabajador trabajador) {
        if (storeTrabajador.contains(trabajador))
            return;

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

        if (storeTrabajador.contains(trabajador)) {
            proyecto.agregarTrabajador(storeTrabajador.findByRut(trabajador.getRut()));
        } else {
            proyecto.agregarTrabajador(trabajador);
        }

        agregarTrabajador(trabajador);
    }

    public Trabajador eliminarTrabajador(String rut) {
        Trabajador trabajador = storeTrabajador.delete(rut);

        if (trabajador == null)
            return null;

        trabajador.limpiar();

        return trabajador;
    }

    public Trabajador eliminarTrabajador(String idProyecto, String rut) {
        Proyecto p = storeProyecto.findById(idProyecto);

        if (p == null) return null;

        Trabajador trabajador = p.eliminarTrabajador(rut);

        if (trabajador == null)
            return null;

        trabajador.eliminarProyecto(idProyecto);

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


    public void agregarHorario(String rut, String idProyecto, Horario horario) {
        Trabajador trabajador = storeTrabajador.findByRut(rut);

        if (trabajador == null)
            return;

        trabajador.agregarHorario(idProyecto, horario);
    }

    public void eliminarHorario(String rut, Integer idHorario) {
        Trabajador trabajador = storeTrabajador.findByRut(rut);

        if (trabajador == null)
            return;

        trabajador.eliminarHorario(idHorario);
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

    public Iterable<Trabajador> getTrabajadores() {
        return storeTrabajador.findAll();
    }

    public Iterable<Trabajador> getTrabajadores(String idProyecto) {
        Proyecto proyecto = storeProyecto.findById(idProyecto);

        if (proyecto == null)
            return Collections.emptyList();

        return proyecto.getTrabajadores();
    }

    public Iterable<Horario> getHorarios(String rut) {
        Trabajador trabajador = storeTrabajador.findByRut(rut);

        if (trabajador == null)
            return Collections.emptyList();

        return trabajador.obtenerListaHorario();
    }

    public Iterable<Proyecto> getProyectos(String rut) {
        Trabajador trabajador = storeTrabajador.findByRut(rut);

        if (trabajador == null)
            return Collections.emptyList();

        return trabajador.getProyectos();
    }

    public Iterable<Proyecto> getProyectos() {
        return storeProyecto.findAll();
    }

    @Override
    public BigDecimal calcularCosto() {
        Iterator<Proyecto> iterator = storeProyecto.findAll().iterator();

        BigDecimal costoAproximado = new BigDecimal(0);

        while (iterator.hasNext()) {
            costoAproximado = costoAproximado.add(iterator.next().calcularCosto());
        }

        return costoAproximado;
    }
}

