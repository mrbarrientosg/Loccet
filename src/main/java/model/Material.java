package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import exceptions.EmptyFieldException;
import exceptions.NegativeQuantityException;
import model.store.Store;
import model.store.memory.MemoryStoreRegistroMaterial;
import util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que contiene los datos de un material
 *
 * @author Sebastian Fuenzalida
 *
 */
public class Material implements Costeable, Cleanable {

    // MARK: - Atributos

    @Expose
    private String id;

    @Expose
    private String nombre;

    @Expose
    private String descripcion;

    @Expose
    private double cantidad;

    @Expose
    @SerializedName("unidad_medida")
    private String uds;

    @Expose
    private BigDecimal precio;

    @Expose(serialize = false)
    private Store<RegistroMaterial> registroMaterialStore;

    // MARK: - Constructores

    public Material(String nombre, String descripcion, double cantidad, String uds ,BigDecimal precio) throws EmptyFieldException, NegativeQuantityException {
        setNombre(nombre);
        setDescripcion(descripcion);
        setCantidad(cantidad);
        setUds(uds);
        setPrecio(precio);
        id = generarId();
        registroMaterialStore = new MemoryStoreRegistroMaterial();
    }

    public Material(String nombre, String descripcion, double cantidad,String uds,String id,BigDecimal precio) throws NegativeQuantityException, EmptyFieldException {
        setNombre(nombre);
        setDescripcion(descripcion);
        setCantidad(cantidad);
        setUds(uds);
        setId(id);
        setPrecio(precio);
        registroMaterialStore = new MemoryStoreRegistroMaterial();
    }

    public Material() {
        registroMaterialStore = new MemoryStoreRegistroMaterial();
    }

    public Material(Material other) {
        this.id = other.id;
        this.nombre = other.nombre;
        this.descripcion = other.descripcion;
        this.cantidad = other.cantidad;
        this.uds = other.uds;
        this.precio = other.precio;
    }

    // MARK: - Metodos Registro Material

    public void agregarRegistro(RegistroMaterial registroMaterial) {
        registroMaterial.setMaterial(this);
        registroMaterialStore.save(registroMaterial);
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

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCantidad(double cantidad) throws NegativeQuantityException {
        if (cantidad < 0)
            throw new NegativeQuantityException();

        this.cantidad = cantidad;
    }

    public void setUds(String uds) {
        this.uds = uds;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Iterable<RegistroMaterial> getRegistrosMateriales() {
        return registroMaterialStore.findAll();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;

        if (!(obj instanceof Material)) return false;

        Material m = (Material) obj;

        return m.id.equals(id) &&
                m.nombre.equals(nombre) &&
                m.descripcion.equals(descripcion) &&
                m.uds.equals(uds) &&
                m.precio.equals(precio);
    }

    // MARK: - Costeable y Cleanable

    @Override
    public BigDecimal calcularCosto() {
        return getPrecio().multiply(BigDecimal.valueOf(getCantidad()));
    }

    @Override
    public void clean() {
        registroMaterialStore.clean();
        registroMaterialStore = null;
    }
}
