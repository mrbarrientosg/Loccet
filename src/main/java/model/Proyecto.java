package model;

import com.google.gson.*;
import com.google.gson.annotations.Expose;
import exceptions.EmptyFieldException;
import json.LocalDateTypeConverter;
import model.store.*;
import model.store.memory.MemoryStoreAsistencia;
import model.store.memory.MemoryStoreFase;
import model.store.memory.MemoryStoreTrabajador;
import specification.MemorySpecification;
import util.StringUtils;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Proyecto implements Costeable, Cleanable {

    // MARK: - Atributos

    @Expose
    private String id;

    @Expose
    private String nombre;

    @Expose
    private Localizacion localizacion;

    @Expose
    private LocalDate fechaInicio;

    @Expose
    private LocalDate fechaTermino;

    @Expose
    private BigDecimal costoEstimado;

    @Expose
    private String nombreCliente;

    @Expose(serialize = false)
    private StoreTrabajador storeTrabajador;

    @Expose(serialize = false)
    private Store<Asistencia> asistenciaStore;

    @Expose(serialize = false)
    private StoreFase storeFase;

    @Expose(serialize = false)
    private InventarioMaterial inventarioMaterial;

    // MARK: - Constructor

    public Proyecto() {
        id = generarId();
        asistenciaStore = new MemoryStoreAsistencia();
        storeTrabajador = new MemoryStoreTrabajador();
        storeFase = new MemoryStoreFase();
        inventarioMaterial = new InventarioMaterial();
    }

    public Proyecto(Proyecto other) {
        this.id = other.id;
        this.nombre = other.nombre;
        this.localizacion = new Localizacion(other.localizacion);
        this.fechaInicio = other.fechaInicio;
        this.fechaTermino = other.fechaTermino;
        this.costoEstimado = other.costoEstimado;
        this.nombreCliente = other.nombreCliente;
    }

    // MARK: - Metodos Trabajador

    /**
     * Obtiene al trabajador el cual coincida con el rut.
     * @param rut del trabajador.
     * @return Trabajador encontrado.
     *
     * @author Matias Barrientos
     */
    public Trabajador obtenerTrabajador(String rut) {
        return storeTrabajador.findByRut(rut);
    }

    /**
     * Agrega un nuevo trabajador al proyecto
     * @param trabajador trabajador a guardar
     * @return true si pudo agregarlo, en caso contrario false
     *
     * @author Matias Zuñiga
     */
    public void agregarTrabajador(Trabajador trabajador) {
        if (storeTrabajador.contains(trabajador))
            return;

        trabajador.asociarProyecto(this);
        storeTrabajador.save(trabajador);
    }


    /**
     * Elimina al trabajador que coincida con el rut.
     * @param rut del trabajador.
     * @return El trabajador eliminado
     *
     * @author Matias Barrientos
     */
    public Trabajador eliminarTrabajador(String rut) {
        return storeTrabajador.delete(rut);
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

    // MARK: - Metodos Inventario

    public void agregarMaterial(Material material) {
        inventarioMaterial.agregarMaterial(material);
    }

    public Material eliminarMaterial(String idMaterial) {
        return inventarioMaterial.eliminarMaterial(idMaterial);
    }

    public Material obtenerMaterial(String idMaterial) {
        return inventarioMaterial.obtenerMaterial(idMaterial);
    }

    public Iterable<Material> buscarMaterial(MemorySpecification<Material> specification) {
        return inventarioMaterial.buscarMaterial(specification);
    }

    // MARK: - Metodos Asistencia

    public void agregarAsistencia(String rutTrabajador, Asistencia asistencia) {
        asistencia.setProyecto(this);
        asistencia.setTrabajador(storeTrabajador.findByRut(rutTrabajador));
        asistenciaStore.save(asistencia);
    }

    // MARK: - Metodos Fase

    public void agregarFase(Fase fase) {
        fase.setProyecto(this);
        storeFase.save(fase);
    }

    // MARK: - Metodos Tarea

    public void agregarTarea(int idFase, Tarea tarea) {
        storeFase.findById(idFase).agregarTarea(tarea);
    }


    // MARK : - Metodos Registro Material

    public void agregarRegistroMaterial(String idMaterial, RegistroMaterial registroMaterial) {
        inventarioMaterial.agregarRegistroMaterial(idMaterial, registroMaterial);
    }

    // MARK: - Interfaz Costeable

    @Override
    public BigDecimal calcularCosto(){
        Iterator<Trabajador> iterator = storeTrabajador.findAll().iterator();

        BigDecimal costoAproximado = new BigDecimal(0);

        while (iterator.hasNext())
            costoAproximado = costoAproximado.add(iterator.next().calcularSueldo());

        return costoAproximado.add(inventarioMaterial.calcularCosto());
    }

    // MARK: - Getter

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Localizacion getLocalizacion() {
        return localizacion;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaTermino() {
        return fechaTermino;
    }

    public BigDecimal getCostoEstimado() {
        return costoEstimado;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public Integer getIdInventario() {
        return inventarioMaterial.getId();
    }

    // MARK: - Setter

    private void setId(String id) throws EmptyFieldException {
        if (StringUtils.isEmpty(id))
            throw new EmptyFieldException("ID");

        this.id = id;
    }

    public void setNombre(String nombre) throws EmptyFieldException {
        if (StringUtils.isEmpty(nombre))
            throw new EmptyFieldException("Nombre");

        this.nombre = nombre;
    }

    public void setLocalizacion(Localizacion localizacion) {
        this.localizacion = localizacion;
    }

    public void setFechaInicio(LocalDate fechaInicio) throws EmptyFieldException {
        if (fechaInicio == null)
            throw new EmptyFieldException("Fecha inicio");

        this.fechaInicio = fechaInicio;
    }

    public void setFechaTermino(LocalDate fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

    public void setCostoEstimado(BigDecimal costoEstimado) {
        this.costoEstimado = costoEstimado;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public void setIdInventario(Integer id) {
        inventarioMaterial.setId(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;

        if (!(obj instanceof Proyecto)) return false;

        Proyecto p = (Proyecto) obj;

        return p.id.equals(id) &&
                p.nombre.equals(nombre) &&
                p.localizacion.equals(localizacion) &&
                p.nombreCliente.equals(nombreCliente);

    }

    @Override
    public void clean() {
        storeTrabajador.findAll().forEach(trabajador -> trabajador.eliminarProyecto(id));
        storeTrabajador.clean();
        storeFase.clean();
        asistenciaStore.clean();

        storeFase = null;
        storeTrabajador = null;
        asistenciaStore = null;
        localizacion = null;
    }

    // MARK: - JSON

    public static class ProyetoDeserializer implements JsonDeserializer<Proyecto> {

        @Override
        public Proyecto deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            Proyecto p = new Proyecto();
            JsonObject json = jsonElement.getAsJsonObject();

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new LocalDateTypeConverter())
                    .create();

            try {
                p.setId(json.get("id").getAsString());
                p.setNombre(json.get("nombre").getAsString());
                p.setCostoEstimado(json.get("costo_estimado").getAsBigDecimal());
                p.setNombreCliente(json.get("nombre_cliente").getAsString());

                p.setFechaInicio(gson.fromJson(json.get("fecha_inicio"), LocalDate.class));
                p.setFechaTermino(gson.fromJson(json.get("fecha_termino"), LocalDate.class));

                p.setLocalizacion(gson.fromJson(json.get("localizacion").getAsString(), Localizacion.class));

                p.inventarioMaterial.setId(json.get("id_inventario").getAsInt());
            } catch (EmptyFieldException e) {
                // TODO: Ver que se hace en este caso
                e.printStackTrace();
            }


            return p;
        }
    }

    public static class ProyectoSerializer implements JsonSerializer<Proyecto> {

        @Override
        public JsonElement serialize(Proyecto proyecto, Type type, JsonSerializationContext jsonSerializationContext) {
            JsonObject json = new JsonObject();

            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .create();

            json.addProperty("id", proyecto.getId());
            json.addProperty("nombre", proyecto.getNombre());
            json.addProperty("nombre_cliente", proyecto.getNombreCliente());

            json.add("localizacion", gson.toJsonTree(proyecto.getLocalizacion()));

            return json;
        }
    }
    private final String generarId(){
        String result = java.util.UUID.randomUUID().toString();
        result = result.substring(0,20);
        return result;
    }


}
