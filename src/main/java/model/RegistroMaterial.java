package model;

import com.google.gson.JsonObject;

import java.time.Instant;
import java.time.LocalDateTime;

/**
 * @author Sebastian Fuenzalida.
 */
public class RegistroMaterial {

    private Material material;

    private double cantidad;

    private Instant fecha;

    /**
     * @param json archivo
     */
    public RegistroMaterial(JsonObject json) {
        cantidad = json.get("cantidad").getAsDouble();
        fecha = Instant.ofEpochSecond(json.get("fecha").getAsLong());
    }

    /**
     * @param cantidad material
     */
    public RegistroMaterial(double cantidad){
        this.cantidad = cantidad;
    }

    /**
     * @return cantidad de material
     */
    public Double getCantidad(){
        return cantidad;
    }

    /**
     * @return fecha del material.
     */

    public Instant getFecha() {
        return fecha;
    }

    /**
     * @param material material
     */
    public void setMaterial(Material material) {
        this.material = material;
    }
}
