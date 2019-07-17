package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import exceptions.EmptyFieldException;
import model.store.Store;
import model.store.memory.MemoryStoreRegistroMaterial;
import util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Clase modelo usada para el objeto material;
 *
 * @author Sebastian Fuenzalida
 *
 */
public class Material {

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

    public Material(String nombre, String descripcion, double cantidad,String uds,BigDecimal precio) {
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.nombre = nombre;
        this.uds = uds;
        id = generarId();
        this.precio = precio;
        registroMaterialStore = new MemoryStoreRegistroMaterial();
    }

    public Material(String nombre, String descripcion, double cantidad,String uds,String id,BigDecimal precio){
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.nombre = nombre;
        this.uds = uds;
        this.id = id;
        this.precio = precio;
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

    public void setNombre(String nombre) throws EmptyFieldException {
        if (StringUtils.isEmpty(nombre))
            throw new EmptyFieldException("Nombre");

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

    public List<RegistroMaterial> getListaRegistroMaterial() {
        List<RegistroMaterial> list = new ArrayList<>();
        registroMaterialStore.findAll().forEach(list::add);
        return list;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;

        if (!(obj instanceof Material)) return false;

        Material m = (Material) obj;

        return m.id.equals(id) &&
                m.nombre.equals(nombre) &&
                m.descripcion.equals(descripcion) &&
                Double.compare(m.cantidad, cantidad) == 0 &&
                m.uds.equals(uds) &&
                m.precio.equals(precio);
    }
}
