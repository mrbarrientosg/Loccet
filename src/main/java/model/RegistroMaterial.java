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

    private Boolean retirado;



    /**
     * @param cantidad material
     */
    public RegistroMaterial(double cantidad,boolean retirado){
        this.cantidad = cantidad;
        this.retirado = retirado;
        fecha = Instant.now();
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
