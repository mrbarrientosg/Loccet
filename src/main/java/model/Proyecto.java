package model;

import com.google.gson.*;
import exceptions.EmptyFieldException;
import json.LocalDateTypeConverter;
import model.store.*;
import model.store.memory.MemoryStoreAsistencia;
import model.store.memory.MemoryStoreFase;
import model.store.memory.MemoryStoreTrabajador;
import util.StringUtils;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Proyecto implements Costeable{

    // MARK: - Atributos

    private String id;

    private String nombre;

    private Localizacion localizacion;

    private LocalDate fechaInicio;

    private LocalDate fechaTermino;

    private BigDecimal estimacion;

    private String nombreCliente;

    private StoreTrabajador storeTrabajador;

    private Store<Asistencia> asistenciaStore;

    private StoreFase storeFase;

    private InventarioMaterial inventarioMaterial;

    // MARK: - Constructor

    public Proyecto() {
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
        this.estimacion = other.estimacion;
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
    public void agregarTrabajador(Trabajador trabajador){
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

    public List<Trabajador> getTrabajadores() {
        // Hay cambiarlo por un iterator
        List<Trabajador> list = new ArrayList<>();
        storeTrabajador.findAll().forEach(list::add);
        return list;
    }

    // MARK: - Metodos Inventario

    public void agregarMaterial(Material material) {
        inventarioMaterial.agregarMaterial(material);
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
        Iterable<Trabajador> iterable = storeTrabajador.findAll();

        BigDecimal costoAproximado = new BigDecimal(0);

        iterable.forEach(trabajador ->  {
            costoAproximado.add(trabajador.calcularSueldo());
        });

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

    public BigDecimal getEstimacion() {
        return estimacion;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public InventarioMaterial getInventarioMaterial() {
        return inventarioMaterial;
    }

    // MARK: - Setter

    public void setId(String id) throws EmptyFieldException {
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

    public void setEstimacion(BigDecimal estimacion) {
        this.estimacion = estimacion;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
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
                p.setEstimacion(json.get("costo_estimado").getAsBigDecimal());
                p.setNombreCliente(json.get("nombre_cliente").getAsString());

                p.setFechaInicio(gson.fromJson(json.get("fecha_inicio"), LocalDate.class));
                p.setFechaTermino(gson.fromJson(json.get("fecha_termino"), LocalDate.class));

                p.setLocalizacion(gson.fromJson(json.get("localizacion").getAsString(), Localizacion.class));
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


}
