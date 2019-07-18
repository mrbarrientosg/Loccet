package model;

import com.google.gson.*;
import com.google.gson.annotations.Expose;
import exceptions.EmptyFieldException;
import exceptions.InvalidaRutException;
import json.LocalDateTypeConverter;
import model.store.memory.MemoryStoreHorario;
import model.store.memory.MemoryStoreProyecto;
import model.store.StoreHorario;
import model.store.StoreProyecto;
import util.StringUtils;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class Trabajador {

    // MARK: - Atributos

    @Expose
    private String rut;

    @Expose
    private String nombre;

    @Expose
    private String apellido;

    @Expose
    private LocalDate fechaNacimiento;

    @Expose(serialize = false)
    private Especialidad especialidad;

    @Expose
    private Localizacion localizacion;

    @Expose
    private String telefono;

    @Expose
    private String correoElectronico;

    @Expose(serialize = false)
    private StoreProyecto storeProyecto;

    @Expose(serialize = false)
    private StoreHorario storeHorario;

    // MARK: - Constructor

    public Trabajador() {
        storeHorario = new MemoryStoreHorario();
        storeProyecto = new MemoryStoreProyecto();
    }

    public Trabajador(Trabajador other) {
        this.rut = other.rut;
        this.nombre = other.nombre;
        this.apellido = other.apellido;
        this.fechaNacimiento = other.fechaNacimiento;
        this.localizacion = new Localizacion(other.localizacion);
        this.especialidad = new Especialidad(other.especialidad);
        this.telefono = other.telefono;
        this.correoElectronico = other.correoElectronico;
    }

    // MARK: - Metodos Proyecto

    public void asociarProyecto(Proyecto proyecto) {
        storeProyecto.save(proyecto);
    }

    // MARK: - Metodos Horario

    public void agregarHorario(String idProyecto, Horario horario) {
        horario.setProyecto(storeProyecto.findById(idProyecto));
        horario.setTrabajador(this);
        storeHorario.save(horario);
    }

    public void eliminarHorario(Integer id) {
        storeHorario.delete(id);
    }

    public Iterable<Horario> obtenerListaHorario() {
        return storeHorario.findAll();
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

    public Iterable<Proyecto> getProyectos() {
        return storeProyecto.findAll();
    }
    // MARK: - Setter

    public void setRut(String rut) throws EmptyFieldException, InvalidaRutException {
        if (StringUtils.isEmpty(rut))
            throw new EmptyFieldException("Rut");

        if (!validarRut(rut))
            throw new InvalidaRutException();

        this.rut = rut;
    }

    public void setNombre(String nombre) throws EmptyFieldException {
        if (StringUtils.isEmpty(nombre))
            throw new EmptyFieldException("Nombre");

        this.nombre = nombre;
    }

    public void setApellido(String apellido) throws EmptyFieldException {
        if (StringUtils.isEmpty(apellido))
            throw new EmptyFieldException("Apellido");

        this.apellido = apellido;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) throws EmptyFieldException {
        if (fechaNacimiento == null)
            throw new EmptyFieldException("Fecha de nacimiento");

        this.fechaNacimiento = fechaNacimiento;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public void setLocalizacion(Localizacion localizacion) {
        this.localizacion = localizacion;
    }

    public void setTelefono(String telefono) throws EmptyFieldException {
        if (StringUtils.isEmpty(telefono))
            throw new EmptyFieldException("Telefono");

        this.telefono = telefono;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;

        if (!(obj instanceof Trabajador)) return false;

        Trabajador p = (Trabajador) obj;

        return p.rut.equals(rut) &&
                p.nombre.equals(nombre) &&
                p.apellido.equals(apellido) &&
                p.localizacion.equals(localizacion) &&
                p.fechaNacimiento.isEqual(fechaNacimiento) &&
                p.especialidad.equals(especialidad) &&
                p.correoElectronico.equals(correoElectronico) &&
                p.telefono.equals(telefono);
    }

    // MARK: - JSON

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
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .registerTypeAdapter(LocalDate.class, new LocalDateTypeConverter())
                    .create();

            try {
                t.setRut(json.get("rut").getAsString());
                t.setNombre(json.get("nombre").getAsString());
                t.setApellido(json.get("apellido").getAsString());

                Especialidad especialidad = gson.fromJson(json.get("especialidad").getAsString(), Especialidad.class);

                Especialidades.getInstance().agregar(especialidad);

                t.setEspecialidad(especialidad);
                t.setLocalizacion(gson.fromJson(json.get("localizacion").getAsString(), Localizacion.class));

                t.setTelefono(json.get("telefono").getAsString());

                if (json.get("correo_electronico") != null && !json.get("correo_electronico").isJsonNull()){
                    t.setCorreoElectronico(json.get("correo_electronico").getAsString());
                }

                t.setFechaNacimiento(gson.fromJson(json.get("fecha_nacimiento"), LocalDate.class));
            } catch (EmptyFieldException | InvalidaRutException ex) {
                // TODO: Ver que se hace en este caso.
                ex.printStackTrace();
            }

            return t;
        }
    }

    private boolean validarRut(String rut) {
        boolean validacion = false;

        rut =  rut.toUpperCase();
        rut = rut.replace(".", "");
        rut = rut.replace("-", "");
        int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

        char dv = rut.charAt(rut.length() - 1);

        int m = 0, s = 1;
        for (; rutAux != 0; rutAux /= 10) {
            s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
        }
        if (dv == (char) (s != 0 ? s + 47 : 75)) {
            validacion = true;
        }

        return validacion;
    }

}
