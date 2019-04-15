package cl.loccet.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Clase que contiene un reporte de la contratista
 */
public class RegistroAvance {

    private String id;

    private Proyecto proyecto;

    private Date fecha;

    /**
     * Hay que especificar la clase contratista
     */
    private Object contratista;

    private String descripcion;

    private ArrayList<ItemRegistro> registros;

    private int totalTrabajadores;

    private double totalHorasTrabajadas;

    // id del encargado
    private String nombreDelEncargado;

    public RegistroAvance() {
        registros = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getTotalTrabajadores() {
        return totalTrabajadores;
    }

    public double getTotalHorasTrabajadas() {
        return registros.stream().mapToDouble(ItemRegistro::getHorasTrabajadas).reduce(0, (left, right) -> left + right);
    }

    public String getNombreDelEncargado() {
        return nombreDelEncargado;
    }

    public void agregarRegistro(ItemRegistro itemRegistro) {
        registros.add(itemRegistro);
    }
}
