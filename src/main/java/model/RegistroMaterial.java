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

    // MARK: - Constructor

    public RegistroMaterial(double cantidad){
        this.cantidad = cantidad;
    }

    // MARK: - Getter

    public double getCantidad() {
        return cantidad;
    }

    public Instant getFecha() {
        return fecha;
    }

    // MARK: - Setter

    public void setMaterial(Material material) {
        this.material = material;
    }
}
