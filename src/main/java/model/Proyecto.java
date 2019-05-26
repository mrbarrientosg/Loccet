package model;

import com.google.gson.*;
import json.LocalDateTypeConverter;
import repository.RepositoryAsistencia;
import repository.RepositoryFase;
import repository.Specification;
import repository.memory.MemoryRepositoryAsistencia;
import repository.memory.MemoryRepositoryFase;
import repository.memory.MemoryRepositoryTrabajador;
import repository.RepositoryTrabajador;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Proyecto {

    // MARK: - Atributos

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

    public Proyecto() {
        repositoryAsistencia = new MemoryRepositoryAsistencia();
        repositoryTrabajador = new MemoryRepositoryTrabajador();
        repositoryFase = new MemoryRepositoryFase();
        inventarioMaterial = new InventarioMaterial();
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
        return repositoryTrabajador.get(rut);
    }

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

    public Iterator<Trabajador> buscarTrabajador(Specification busqueda) {
        return repositoryTrabajador.get(busqueda);
    }

    public List<Trabajador> getTrabajadores() {
        // Hay cambiarlo por un iterator
        List<Trabajador> list = new ArrayList<>();
        repositoryTrabajador.get().forEachRemaining(list::add);
        return list;
    }

    // MARK: - Metodos Inventario

    public void agregarMaterial(Material material) {
        inventarioMaterial.agregarMaterial(material);
    }

    // MARK: - Metodos Asistencia

    public void agregarAsistencia(String rutTrabajador, Asistencia asistencia) {
        asistencia.setProyecto(this);
        asistencia.setTrabajador(repositoryTrabajador.get(rutTrabajador));
        repositoryAsistencia.add(asistencia);
    }

    // MARK: - Metodos Fase

    public void agregarFase(Fase fase) {
        fase.setProyecto(this);
        repositoryFase.add(fase);
    }

    // MARK: - Metodos Tarea

    public void agregarTarea(int idFase, Tarea tarea) {
        repositoryFase.get(idFase).agregarTarea(tarea);
    }


    // MARK : - Metodos Registro Material

    public void agregarRegistroMaterial(String idMaterial, RegistroMaterial registroMaterial) {
        inventarioMaterial.agregarRegistroMaterial(idMaterial, registroMaterial);
    }

    public BigDecimal costoTotalAproximado(){
        List listaTrabajadores = getTrabajadores();
        BigDecimal costoAproximado = new BigDecimal(0);
        for (int i = 0; i < listaTrabajadores.size();i++) {
            Trabajador trabajador = (Trabajador) listaTrabajadores.get(i);
                costoAproximado.add(trabajador.calcularSueldo());
        }
        return costoAproximado.add(inventarioMaterial.costoInventario());
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

    // MARK: - JSON

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
