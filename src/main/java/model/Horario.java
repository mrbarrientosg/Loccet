package model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Clase que contiene los datos y metodos de un Horario
 */
public class Horario implements Cleanable {

    // MARK: - Atributos

    private Integer id;

    private Integer dia;

    private Proyecto proyecto;

    private Trabajador trabajador;

    private LocalTime horaInicio;

    private LocalTime horaFin;

    // MARK: - Constructor

    public Horario(Integer dia, LocalTime horaInicio, LocalTime horaFin) {
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    // MARK: - Getter

    public Integer getId() {
        return id;
    }

    public Integer getDia() {
        return dia;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    // MARK: - Setter

    public void setId(Integer id) {
        this.id = id;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }

    @Override
    public void clean() {
        proyecto = null;
        trabajador = null;
    }

    public static class HorarioSerializer implements JsonSerializer<Horario> {

        @Override
        public JsonElement serialize(Horario horario, Type type, JsonSerializationContext jsonSerializationContext) {
            JsonObject json = new JsonObject();

            json.addProperty("hora_inicio", horario.getHoraInicio().toString());
            json.addProperty("hora_fin", horario.getHoraFin().toString());
            json.addProperty("rut_trabajador", horario.getTrabajador().getRut());
            json.addProperty("id_proyecto", horario.getProyecto().getId());
            json.addProperty("dia", horario.getDia());

            return json;
        }
    }

}
