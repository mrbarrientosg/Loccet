package model;

import com.google.gson.JsonObject;

import java.time.Instant;
import java.time.LocalDateTime;

/**
 * @author Sebastian Fuenzalida.
 */
public class RegistroMaterial {

    // MARK: - Atributos

    private Material material;

    private double cantidad;

    private Instant fecha;

    private Boolean retirado;

    // MARK: - Constructor

    public RegistroMaterial(double cantidad, Boolean retirado){
        this.cantidad = cantidad;
        this.retirado = retirado;
        fecha = Instant.now();
    }

    // MARK: - Getter

    public double getCantidad() {
        return cantidad;
    }

    public Instant getFecha() {
        return fecha;
    }

    public Boolean getRetirado(){
        return retirado;
    }

    // MARK: - Setter

    public void setMaterial(Material material) {
        this.material = material;
    }
}
