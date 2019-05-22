package model;

import com.google.gson.*;
import json.LocalDateTypeConverter;
import repository.memory.MemoryRepositoryHorario;
import repository.memory.MemoryRepositoryProyecto;
import repository.RepositoryHorario;
import repository.RepositoryProyecto;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public abstract class Trabajador {

    private String rut;

    private String nombre;

    private String apellido;

    private LocalDate fechaNacimiento;

    private Especialidad especialidad;

    private Localizacion localizacion;

    private String telefono;

    private String correoElectronico;

    private RepositoryProyecto repositoryProyecto;

    private RepositoryHorario repositoryHorario;

    public Trabajador() {
        repositoryHorario = new MemoryRepositoryHorario();
        repositoryProyecto = new MemoryRepositoryProyecto();
    }

    public void asociarProyecto(Proyecto proyecto) {
        repositoryProyecto.add(proyecto);
    }

    public void agregarHorario(String idProyecto, Horario horario) {
        horario.setProyecto(repositoryProyecto.get(idProyecto));
        horario.setTrabajador(this);
        repositoryHorario.add(horario);
    }

    public Horario eliminarHorario(Horario horario) {
        return repositoryHorario.remove(horario);
    }

    public List<Horario> obtenerListaHorario() {
        List<Horario> aux = new ArrayList<>();
        repositoryHorario.get().forEachRemaining(aux::add);
        return Collections.unmodifiableList(aux);
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Localizacion getLocalizacion() {
        return localizacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public void setLocalizacion(Localizacion localizacion) {
        this.localizacion = localizacion;
    }

    public abstract BigDecimal calcularSueldo();

    public static class TrabajadorDeserializer implements JsonDeserializer<Trabajador> {

        @Override
        public Trabajador deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            Trabajador t;

            JsonObject json = jsonElement.getAsJsonObject();

            if (json.get("tiempo_completo").getAsBoolean()) {
                t = new TrabajadorTiempoCompleto();
            } else {
                t = new TrabajadorPartTime(json.get("cantidad_hora_trabajada").getAsInt());
            }

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new LocalDateTypeConverter())
                    .create();

            t.setRut(json.get("rut").getAsString());
            t.setNombre(json.get("nombre").getAsString());
            t.setApellido(json.get("apellido").getAsString());

            t.setEspecialidad(gson.fromJson(json.get("especialidad").getAsString(), Especialidad.class));
            t.setLocalizacion(gson.fromJson(json.get("localizacion").getAsString(), Localizacion.class));

            t.setTelefono(json.get("telefono").getAsString());
            t.setCorreoElectronico(json.get("correo_electronico").getAsString());
            t.setFechaNacimiento(gson.fromJson(json.get("fecha_nacimiento"), LocalDate.class));

            return t;
        }
    }

}
