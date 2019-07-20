package cell;

import model.RegistroMaterial;
import util.DateUtils;

public class RegistroMaterialCell {

    private String fecha;

    private String cantidad;

    private String retirado;

    public RegistroMaterialCell(RegistroMaterial value) {
        this.fecha = DateUtils.formatDate(value.getFecha());
        this.cantidad = String.valueOf(value.getCantidad());
        this.retirado = value.getRetirado() ? "Agregado" : "Retirado";
    }

    public String getFecha() {
        return fecha;
    }

    public String getCantidad() {
        return cantidad;
    }

    public String getRetirado() {
        return retirado;
    }
}
