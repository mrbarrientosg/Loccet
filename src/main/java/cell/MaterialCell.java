package cell;

import model.Material;

/**
 * Clase que ayuda a desplegar un material de
 * forma mas facil para la vista
 *
 * @author Sebastian Fuenzalida
 */
public final class MaterialCell {

    private String id;

    private String nombre;

    private String descripcion;

    private String cantidad;

    private String uds;

    private String precio;

    public MaterialCell(Material m) {
        id = m.getId();
        nombre = m.getNombre();
        descripcion = m.getDescripcion();
        cantidad = String.valueOf(m.getCantidad());
        uds = m.getUds();
        precio = m.getPrecio().toString();
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getCantidad() {
        return cantidad;
    }

    public String getUds() {
        return uds;
    }

    public String getPrecio() {
        return precio;
    }
}
