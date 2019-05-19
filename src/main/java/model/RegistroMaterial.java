package model;

import com.google.gson.JsonObject;

import java.time.LocalDateTime;

public class RegistroMaterial {

    private Material material;

    private double cantidad;

    private LocalDateTime fecha;

    public RegistroMaterial(Material material, JsonObject json) {
        this.material = material;
        cantidad = json.get("cantidad").getAsDouble();
        //fecha = LocalDateTime.parse(json.get("fecha").getAsString());
    }
}
