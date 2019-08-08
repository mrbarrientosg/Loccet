package model;

import com.google.gson.JsonObject;
import exceptions.NegativeQuantityException;
import specification.MemorySpecification;
import model.store.memory.MemoryStoreProyecto;
import model.store.memory.MemoryStoreTrabajador;
import model.store.StoreProyecto;
import model.store.StoreTrabajador;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Clase que contiene los datos y metodos
 * para la constructora.
 */
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

        proyecto.clean();

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

    public Iterable<Proyecto> getProyectos(String rut) {
        Trabajador trabajador = storeTrabajador.findByRut(rut);

        if (trabajador == null)
            return Collections.emptyList();

        return trabajador.getProyectos();
    }

    public Iterable<Proyecto> getProyectos() {
        return storeProyecto.findAll();
    }

    public Integer getIdInventario(String idProyecto) {
        Proyecto p = storeProyecto.findById(idProyecto);

        if (p == null)
            return null;

        return p.getIdInventario();
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

        trabajador.clean();

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

    public Iterable<Trabajador> buscarTrabajador(MemorySpecification<Trabajador> specification) {
        final List<Trabajador> trabajadors = new ArrayList<>();

        for (Trabajador trabajador: storeTrabajador.findAll()) {
            if (specification.test(trabajador))
                trabajadors.add(trabajador);
        }

        return trabajadors;
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


    // MARK: - Metodos Horario

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

    public Iterable<Horario> getHorarios(String rut) {
        Trabajador trabajador = storeTrabajador.findByRut(rut);

        if (trabajador == null)
            return Collections.emptyList();

        return trabajador.obtenerListaHorario();
    }


    // MARK: - Metodos Material

    public Iterable<Material> buscarMaterial(String idProyecto, MemorySpecification<Material> specification) {
        Proyecto p = storeProyecto.findById(idProyecto);

        if (p == null)
            return Collections.emptyList();

        return p.buscarMaterial(specification);
    }

    public Material obtenerMaterial(String idProyecto, String idMaterial) {
        Proyecto p = storeProyecto.findById(idProyecto);

        if (p == null)
            return null;

        return p.obtenerMaterial(idMaterial);
    }

    public void agregarMaterial(String idProyecto, Material material) {
        Proyecto p = storeProyecto.findById(idProyecto);

        if (p == null)
            return;

        p.agregarMaterial(material);
    }

    public Material eliminarMaterial(String idProyecto, String idMaterial) {
        Proyecto p = storeProyecto.findById(idProyecto);

        if (p == null)
            return null;

        return p.eliminarMaterial(idMaterial);
    }

    public void actualizarCantidadMaterial(String idProyecto, String idMaterial, double cantidad) throws NegativeQuantityException {
        Proyecto proyecto = storeProyecto.findById(idProyecto);

        if (proyecto == null)
            return;

        Material m = proyecto.obtenerMaterial(idMaterial);

        if (m == null)
            return;

        m.setCantidad(m.getCantidad() + cantidad);
    }

    // MARK: - Metodos Registro Material

    public void agregarRegistroMaterial(String idProyecto, String idMaterial, RegistroMaterial rm) {
        Proyecto proyecto = storeProyecto.findById(idProyecto);

        if (proyecto == null)
            return;

        Material m = proyecto.obtenerMaterial(idMaterial);

        if (m == null)
            return;

        m.agregarRegistro(rm);
    }

    public Iterable<RegistroMaterial> getRegistrosMateriales(String idProyecto, String idMaterial) {
        Proyecto proyecto = storeProyecto.findById(idProyecto);

        if (proyecto == null)
            return Collections.emptyList();

        Material m = proyecto.obtenerMaterial(idMaterial);

        if (m == null)
            return Collections.emptyList();

        return m.getRegistrosMateriales();
    }

    // MARK: - Costeable

    @Override
    public BigDecimal calcularCosto() {
        Iterator<Proyecto> iterator = storeProyecto.findAll().iterator();

        BigDecimal costoAproximado = new BigDecimal(0);

        while (iterator.hasNext())
            costoAproximado = costoAproximado.add(iterator.next().calcularCosto());

        return costoAproximado;
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
}

