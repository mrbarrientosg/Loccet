package model;
import com.google.gson.JsonObject;
import repository.memory.MemoryRepositoryRegistroMaterial;
import repository.RepositoryRegistroMaterial;

import java.math.BigDecimal;

/**
 *
 * Clase modelo usada para el objeto material;
 *
 * @author Sebastian Fuenzalida
 *
 */

public class Material {

    // MARK: - Atributos

    private String id;

    private String nombre;

    private String descripcion;

    private double cantidad;

    private String uds;

    private BigDecimal precio;

    private RepositoryRegistroMaterial repositoryRegistroMaterial;

    // MARK: - Constructores

    public Material(String nombre, String descripcion, double cantidad,String uds,BigDecimal precio){
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.nombre = nombre;
        this.uds = uds;
        id = generarId();
        this.precio = precio;
        repositoryRegistroMaterial = new MemoryRepositoryRegistroMaterial();
    }

    public Material(String nombre, String descripcion, double cantidad,String uds,String id,BigDecimal precio){
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.nombre = nombre;
        this.uds = uds;
        this.id = id;
        this.precio = precio;
        repositoryRegistroMaterial = new MemoryRepositoryRegistroMaterial();
    }

    public Material() {
        repositoryRegistroMaterial = new MemoryRepositoryRegistroMaterial();
    }

    // MARK: - Metodos Registro Material

    public void agregarRegistro(RegistroMaterial registroMaterial) {
        registroMaterial.setMaterial(this);
        repositoryRegistroMaterial.add(registroMaterial);
    }

    public RegistroMaterial actualizarRegistro(RegistroMaterial registroMaterial) {
        return repositoryRegistroMaterial.update(registroMaterial);
    }

    // MARK: - Metodos Privados

    /**
     * Funcion que genera una id aleatorea.
     *
     * @author Sebastian Fuenzalida , Matias Zuñiga.
     */

    private final String generarId(){
        String result = java.util.UUID.randomUUID().toString();
        result = result.substring(0,6);
        return result;
    }

    // MARK: - Getter

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getCantidad() {
        return cantidad;
    }

    public String getUds() {
        return uds;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    // MARK: - Setter

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public void setUds(String uds) {
        this.uds = uds;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
}
