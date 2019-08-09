package cell;

import model.Material;

import java.text.DecimalFormat;
import java.util.Currency;
import java.util.Locale;

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
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(0);
        df.setCurrency(Currency.getInstance(Locale.getDefault()));

        id = m.getId();
        nombre = m.getNombre();
        descripcion = m.getDescripcion();
        cantidad = String.valueOf(m.getCantidad());
        uds = m.getUds();
        precio = "$" + df.format(m.getPrecio());
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
