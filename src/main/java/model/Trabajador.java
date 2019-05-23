package model;

import com.google.gson.*;
import json.LocalDateTypeConverter;
import repository.memory.MemoryRepositoryHorario;
import repository.memory.MemoryRepositoryProyecto;
import repository.RepositoryHorario;
import repository.RepositoryProyecto;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.*;

public class Trabajador {

    // MARK: - Atributos

    private String rut;

    private String nombre;

    private String apellido;

    private LocalDate fechaNacimiento;

    private Especialidad especialidad;

    private Localizacion localizacion;

    private String telefono;

    private String correoElectronico;

    private int cantidadHoraTrabajada;

    private RepositoryProyecto repositoryProyecto;

    private RepositoryHorario repositoryHorario;

    // MARK: - Constructor

    public Trabajador() {
        repositoryHorario = new MemoryRepositoryHorario();
        repositoryProyecto = new MemoryRepositoryProyecto();
    }

    // MARK: - Metodos Proyecto

    public void asociarProyecto(Proyecto proyecto) {
        repositoryProyecto.add(proyecto);
    }

    // MARK: - Metodos Horario

    public void agregarHorario(String idProyecto, Horario horario) {
        horario.setProyecto(repositoryProyecto.get(idProyecto));
        horario.setTrabajador(this);
        repositoryHorario.add(horario);
    }

    public List<Horario> obtenerListaHorario() {
        List<Horario> aux = new ArrayList<>();
        repositoryHorario.get().forEachRemaining(aux::add);
        return Collections.unmodifiableList(aux);
    }

    // MARK: - Getter

    public String getRut() {
        return rut;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public Localizacion getLocalizacion() {
        return localizacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public int getCantidadHoraTrabajada() {
        return cantidadHoraTrabajada;
    }

    // MARK: - Setter

    public void setRut(String rut) {
        this.rut = rut;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public void setLocalizacion(Localizacion localizacion) {
        this.localizacion = localizacion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public void setCantidadHoraTrabajada(int cantidadHoraTrabajada) {
        this.cantidadHoraTrabajada = cantidadHoraTrabajada;
    }

    // MARK: - JSON

    public static class TrabajadorDeserializer implements JsonDeserializer<Trabajador> {

        @Override
        public Trabajador deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            Trabajador t = new Trabajador();
            JsonObject json = jsonElement.getAsJsonObject();
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new LocalDateTypeConverter())
                    .create();

            t.setRut(json.get("rut").getAsString());
            t.setNombre(json.get("nombre").getAsString());
            t.setApellido(json.get("apellido").getAsString());

            t.setEspecialidad(gson.fromJson(json.get("especialidad").getAsString(), Especialidad.class));
            t.setLocalizacion(gson.fromJson(json.get("localizacion").getAsString(), Localizacion.class));
            //fechaNacimiento

            t.setTelefono(json.get("telefono").getAsString());
            t.setCorreoElectronico(json.get("correo_electronico").getAsString());
            t.setFechaNacimiento(gson.fromJson(json.get("fecha_nacimiento"), LocalDate.class));

            //cantidadHoraTrabajada = json.get("cantidad_hora_trabajada").getAsInt();

            return t;
        }
    }

}
