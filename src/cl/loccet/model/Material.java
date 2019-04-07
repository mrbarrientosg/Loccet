package cl.loccet.model;

public class Material {

    private String categoria;
    private int id;
    private String descripcion;
    private int cantidad;

    public Material(String categoria, int id, String descripcion, int cantidad) {
        this.categoria = categoria;
        this.id = id;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
