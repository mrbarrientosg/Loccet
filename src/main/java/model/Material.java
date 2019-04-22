package model;
import java.util.Date;

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

    private double retiro;

    private Date fechaRetiro;

    private Date fechaIngreso;

    private String uds;

    private double precio;

    public Material(String nombre, String descripcion, double cantidad,String uds,double precio){
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.nombre = nombre;
        this.uds = uds;
        id = generarId();
        fechaRetiro = null;
        this.precio = precio;
        fechaIngreso = new Date();
    }

    public Material(String nombre, String descripcion, double cantidad,String uds,String id,double precio){
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.nombre = nombre;
        this.uds = uds;
        this.id = id;
        this.precio = precio;
        fechaRetiro = null;
        fechaIngreso = new Date();
    }

    public String getId(){return  id;}

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public String getUds() {
        return uds;
    }

    public Date getFechaRetiro() {
        return fechaRetiro;
    }

    public double getRetiro() {
        return retiro;
    }

    public double getCantidad() {
        return cantidad;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setRetiro(double retiro) {
        this.retiro = retiro;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public void setFechaRetiro(Date fechaRetiro) {
        this.fechaRetiro = fechaRetiro;
    }

    public double precioTotal() {
        return cantidad * precio;
    }

    public void setId(String id) {
        this.id = id;
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