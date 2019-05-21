package model;

import com.google.gson.*;
import json.LocalDateTypeConverter;
import repository.RepositoryAsistencia;
import repository.RepositoryFase;
import repository.memory.MemoryRepositoryAsistencia;
import repository.memory.MemoryRepositoryFase;
import repository.memory.MemoryRepositoryTrabajador;
import repository.RepositoryTrabajador;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Proyecto {

    // MARK: - Variables
    private String id;

    private String nombre;

    private Localizacion localizacion;

    private LocalDate fechaInicio;

    private LocalDate fechaTermino;

    private BigDecimal estimacion;

    private String nombreCliente;

    private RepositoryTrabajador repositoryTrabajador;

    private RepositoryAsistencia repositoryAsistencia;

    private RepositoryFase repositoryFase;

    private InventarioMaterial inventarioMaterial;

    // MARK: - Constructor

    private Proyecto(Builder builder){
        this.id = builder.id;
        this.nombre = builder.nombreProyecto;
        this.fechaInicio = builder.fechaInicio;
        this.fechaTermino = builder.fechaTermino;

        repositoryAsistencia = new MemoryRepositoryAsistencia();
        repositoryTrabajador = new MemoryRepositoryTrabajador();
        repositoryFase = new MemoryRepositoryFase();
        inventarioMaterial = new InventarioMaterial();
    }

    public Proyecto() {
        repositoryAsistencia = new MemoryRepositoryAsistencia();
        repositoryTrabajador = new MemoryRepositoryTrabajador();
        repositoryFase = new MemoryRepositoryFase();
        inventarioMaterial = new InventarioMaterial();
    }

    public Proyecto(JsonObject json) {
        id = json.get("id").getAsString();
        nombre = json.get("nombre").getAsString();
        estimacion = json.get("costo_estimado").getAsBigDecimal();
        nombreCliente = json.get("nombre_cliente").getAsString();
        //fechaInicio = LocalDate.parse(json.get("fecha_inicio").getAsString());

        //System.out.println(json.get("fecha_termino").isJsonNull());

        //localizacion = new Localizacion(new JsonParser().parse(json.get("localizacion").getAsString()).getAsJsonObject());


    }
    // MARK: - Getter

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaTermino() {
        return fechaTermino;
    }

    public String getNombre() {
        return nombre;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getEstimacion() {
        return estimacion;
    }

    public InventarioMaterial getInventarioMaterial() {
        return inventarioMaterial;
    }

    public Localizacion getLocalizacion() {
        return localizacion;
    }

    // MARK: - Metodos

    /**
     * Agrega un nuevo trabajador al proyecto
     * @param trabajador trabajador a guardar
     * @return true si pudo agregarlo, en caso contrario false
     *
     * @author Matias Zuñiga
     */
    public void agregarTrabajador(Trabajador trabajador){
        trabajador.asociarProyecto(this);
        repositoryTrabajador.add(trabajador);
    }


    public Trabajador actualizarTrabajador(Trabajador nuevoTrabajador) {
        return repositoryTrabajador.update(nuevoTrabajador);
    }

    public void agregarAsistencia(String rutTrabajador, Asistencia asistencia) {
        asistencia.setProyecto(this);
        asistencia.setTrabajador(repositoryTrabajador.get(rutTrabajador));
        repositoryAsistencia.add(asistencia);
    }

    public void agregarFase(Fase fase) {
        fase.setProyecto(this);
        repositoryFase.add(fase);
    }

    public void agregarTarea(int idFase, Tarea tarea) {
        repositoryFase.get(idFase).agregarTarea(tarea);
    }

    public void agregarMaterial(Material material) {
        inventarioMaterial.agregarMaterial(material);
    }

    public void agregarRegistroMaterial(String idMaterial, RegistroMaterial registroMaterial) {
        inventarioMaterial.agregarRegistroMaterial(idMaterial, registroMaterial);
    }

    public List<Trabajador> getTrabajadores() {
        // Hay cambiarlo por un iterator
        List<Trabajador> list = new ArrayList<>();
        repositoryTrabajador.get().forEachRemaining(list::add);
        return list;
    }


    /**
     * Obtiene al trabajador el cual coincida con el rut.
     * @param rut del trabajador.
     * @return Trabajador encontrado.
     *
     * @author Matias Barrientos
     */
    public Trabajador obtenerTrabajador(String rut) {
        return repositoryTrabajador.get(rut);
    }

    /**
     * Elimina al trabajador que coincida con el rut.
     * @param rut del trabajador.
     * @return El trabajador eliminado
     *
     * @author Matias Barrientos
     */
    public Trabajador eliminarTrabajador(String rut) {
        return repositoryTrabajador.remove(repositoryTrabajador.get(rut));
    }

    /*public void estimacionGasto() {
        double total = inventarioMaterial.gastoTotal();

        NumberFormat formatter = new DecimalFormat("#0.00$");

        System.out.println("Gasto inventario (estimación): " + formatter.format(total));

        Duration diff = Duration.between(fechaInicio.atStartOfDay(), fechaTermino.atStartOfDay());
        long cant = diff.toDays();

        double gastoTrabajadores = listaTrabajadores.stream()
                .map(Trabajador::getEspecialidad)
                .map(Especialidad::sueldoTotal)
                .map(aDouble -> aDouble * cant)
                .reduce(0.0, (left, right) -> left + right);

        System.out.println("Gasto trabajadores (estimación): " + formatter.format(gastoTrabajadores));

        total += gastoTrabajadores;

        System.out.println("Gasto total de la estimación: " + formatter.format(total));

        System.out.println("Gasto propuesto menos estimación: " + formatter.format(estimacion - total));
    }*/

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setLocalizacion(Localizacion localizacion) {
        this.localizacion = localizacion;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
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

    public String getNombreCliente() {
        return nombreCliente;
    }

    public static class Builder {
        private String id;
        private String nombreProyecto;
        private String jefeProyecto;
        private String cliente;
        private String mailCliente;
        private String telefonoCliente;
        private String direccion;
        private String pais;
        private String ciudad;
        private String estado;
        private LocalDate fechaInicio;
        private LocalDate fechaTermino;
        private String fechaTerminoReal;
        private double estimacion;
        private double costoReal;

        public Builder(String nombreProyecto, String jefeProyecto, Double estimacion, String cliente){
            this.id = generarId();
            this.nombreProyecto = nombreProyecto;
            this.jefeProyecto = jefeProyecto;
            this.estimacion = estimacion;
            this.cliente = cliente;
        }

        public Builder datosCliente(String mailCliente, String telefonoCliente) {
            this.mailCliente = mailCliente;
            this.telefonoCliente = telefonoCliente;
            return this;
        }

        public Builder datosUbicacion(String direccion, String pais, String ciudad, String estado) {
            this.direccion = direccion;
            this.pais = pais;
            this.ciudad = ciudad;
            this.estado = estado;
            return this;
        }

        public Builder fechaProyecto(LocalDate fechaInicio, LocalDate fechaTermino) {
            this.fechaInicio = fechaInicio;
            this.fechaTermino = fechaTermino;
            return this;
        }

        public Proyecto build(){
            return new Proyecto(this);
        }

        /**
         * @return un string generando un Id unico para el proyecto.
         * @author Matías Zúñiga
         */
        private final String generarId() {
            String result = java.util.UUID.randomUUID().toString();
            //result = result.replaceAll("-", "");
            //result = result.replaceAll("[A-Za-z]","");
            //result = result.substring(0, 32);
            return result;
        }

    }

    public static class ProyetoDeserializer implements JsonDeserializer<Proyecto> {

        @Override
        public Proyecto deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            Proyecto p = new Proyecto();
            JsonObject json = jsonElement.getAsJsonObject();

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new LocalDateTypeConverter())
                    .create();

            p.setId(json.get("id").getAsString());
            p.setNombre(json.get("nombre").getAsString());
            p.setEstimacion(json.get("costo_estimado").getAsBigDecimal());
            p.setNombreCliente(json.get("nombre_cliente").getAsString());

            p.setFechaInicio(gson.fromJson(json.get("fecha_inicio"), LocalDate.class));
            p.setFechaTermino(gson.fromJson(json.get("fecha_termino"), LocalDate.class));

            p.setLocalizacion(gson.fromJson(json.get("localizacion").getAsString(), Localizacion.class));

            return p;
        }
    }


}
