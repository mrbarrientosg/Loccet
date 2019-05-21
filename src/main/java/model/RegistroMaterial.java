package model;

import com.google.gson.JsonObject;

import java.time.Instant;
import java.time.LocalDateTime;

public class RegistroMaterial {

    private Material material;

    private double cantidad;

    private Instant fecha;

    public RegistroMaterial(JsonObject json) {
        cantidad = json.get("cantidad").getAsDouble();
        fecha = Instant.ofEpochSecond(json.get("fecha").getAsLong());
    }
    public RegistroMaterial(int cantidad){
        this.cantidad = cantidad;
    }

    public Double getCantidad(){
        return cantidad;
    }
    public Instant getFecha() {
        return fecha;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}
