package cell;

import model.Material;

import java.util.Date;

public final class MaterialCell {

    private String id;

    private String nombre;

    private String descripcion;

    private double cantidad;

    private double retiro;

    private Date fechaRetiro;

    private Date fechaIngreso;

    private String uds;

    private double precio;

    public MaterialCell(Material m) {
        id = m.getId();
        nombre = m.getNombre();
        descripcion = m.getDescripcion();
        cantidad = m.getCantidad();
        //retiro = m.getRetiro();
        //fechaRetiro = m.getFechaRetiro();
        //fechaIngreso = m.getFechaIngreso();
        uds = m.getUds();
        //precio = m.getPrecio();
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

    public double getCantidad() {
        return cantidad;
    }

    public double getRetiro() {
        return retiro;
    }

    public Date getFechaRetiro() {
        return fechaRetiro;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public String getUds() {
        return uds;
    }

    public double getPrecio() {
        return precio;
    }
}
