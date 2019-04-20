package cl.loccet.model;

import java.time.LocalDate;
import java.util.*;

public class Trabajador {

    private String rut;

    private String nombre;

    private String apellido;

    private LocalDate fechaNacimiento;

    private Especialidad especialidad;

    private Localizacion localizacion;

    private String telefono;

    private String correoElectronico;

    private Map<String, ArrayList<Horario>> mapProyectohorario;

    /**
     * Guarda el id de todos los proyecto al cual esta asociado el trabajador
     */
    private Map<String, String> mapProyectos;

    private Map<Integer, ArrayList<Horario>> mapDiaHorario;

    private Trabajador(Builder builder) {
        this.rut = builder.rut;
        this.nombre = builder.nombre;
        this.apellido = builder.apellido;
        this.fechaNacimiento = builder.fechaNacimiento;
        this.especialidad = builder.especialidad;
        this.localizacion = builder.localizacion;
        this.telefono = builder.telefono;
        this.correoElectronico = builder.correoElectronico;

        mapProyectohorario = new HashMap<>();
        mapProyectos = new HashMap<>();
        mapDiaHorario = new HashMap<>();
    }

    public boolean asociarProyecto(String idProyecto) {
        if (mapProyectos.containsKey(idProyecto)) return false;
        return mapProyectos.put(idProyecto, idProyecto) == null;
    }

    public void agregarHorario(Horario horario) {
        if (!mapDiaHorario.containsKey(horario.getDia()))
            mapDiaHorario.put(horario.getDia(), new ArrayList<>());

        mapDiaHorario.get(horario.getDia()).add(horario);

        if (!mapProyectohorario.containsKey(horario.getIdProyecto()))
            mapProyectohorario.put(horario.getIdProyecto(), new ArrayList<>());

        mapProyectohorario.get(horario.getIdProyecto()).add(horario);
    }

    public Horario eliminarHorario(String id) {
        Horario h = null;

        for (ArrayList<Horario> horarios: mapProyectohorario.values()) {
            h = eliminarHorario(horarios, id);
            if (h == null) {
                return null;
            }
        }

        mapDiaHorario.get(h.getDia()).remove(h);

        return h;
    }

    private Horario eliminarHorario(ArrayList<Horario> horarios, String id) {
        for (Horario horario: horarios) {
            if (horario.getId().equals(id)) {
                horarios.remove(horario);
                return horario;
            }
        }

        return null;
    }

    public List<Horario> obtenerListaHorario(String idProyecto) {
        if (!mapProyectohorario.containsKey(idProyecto)) return null;
        return Collections.unmodifiableList(mapProyectohorario.get(idProyecto));
    }

    public List<Horario> obtenerListaHorario() {
        List<Horario> aux = new ArrayList<>();
        mapDiaHorario.values().forEach(aux::addAll);
        return Collections.unmodifiableList(aux);
    }

    public static class Builder {

        private String rut;

        private String nombre;

        private String apellido;

        private Especialidad especialidad;

        private LocalDate fechaNacimiento;

        private Localizacion localizacion;

        private String telefono;

        private String correoElectronico;

        public Builder rut(String rut) {
            this.rut = rut;
            return this;
        }

        public Builder nombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder apellido(String apellido) {
            this.apellido = apellido;
            return this;
        }

        public Builder especialidad(Especialidad especialidad) {
            this.especialidad = especialidad;
            return this;
        }

        public Builder fechaNacimiento(LocalDate fechaNacimiento) {
            this.fechaNacimiento = fechaNacimiento;
            return this;
        }

        public Builder localizacion(Localizacion localizacion) {
            this.localizacion = localizacion;
            return this;
        }

        public Builder telefono(String telefono) {
            this.telefono = telefono;
            return this;
        }

        public Builder correoElectronico(String correoElectronico) {
            this.correoElectronico = correoElectronico;
            return this;
        }

        public Trabajador build() {
            return new Trabajador(this);
        }
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

}
