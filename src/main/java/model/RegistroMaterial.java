package model;

import com.google.gson.*;
import json.InstantTypeConverter;
import json.LocalDateTypeConverter;
import util.DateUtils;

import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Clase que contiene los datos de un registro material
 *
 * @author Sebastian Fuenzalida.
 */
public class RegistroMaterial implements Cleanable {

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

    // MARK: - Cleanable

    @Override
    public void clean() {
        material = null;
    }

    public static class RegistroMaterialSerializer implements JsonSerializer<RegistroMaterial> {

        @Override
        public JsonElement serialize(RegistroMaterial registroMaterial, Type type, JsonSerializationContext jsonSerializationContext) {
            JsonObject json = new JsonObject();

            json.addProperty("id_material", registroMaterial.material.getId());
            json.addProperty("cantidad", registroMaterial.cantidad);
            json.addProperty("fecha", DateUtils.formatDate("yyyy-MM-dd HH:mm:ss", registroMaterial.fecha));
            json.addProperty("retirado", registroMaterial.retirado);

            return json;
        }
    }
}
