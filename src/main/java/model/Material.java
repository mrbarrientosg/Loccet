package model;
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

    private String id;

    private String nombre;

    private String descripcion;

    private double cantidad;

    private String uds;

    private BigDecimal precio;

    private RepositoryRegistroMaterial repositoryRegistroMaterial;

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

    public String getId(){return  id;}

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public String getUds() {
        return uds;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void agregarRegistro(RegistroMaterial registroMaterial) {
        repositoryRegistroMaterial.add(registroMaterial);
    }

    public RegistroMaterial actualizarRegistro(RegistroMaterial registroMaterial) {
        return repositoryRegistroMaterial.update(registroMaterial);
    }

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

}
