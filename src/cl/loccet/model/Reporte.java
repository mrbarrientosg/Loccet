package cl.loccet.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Clase que contiene un reporte de la contratista
 */
public class Reporte {

    private String id;

    private Proyecto proyecto;

    private Date fecha;

    /**
     * Hay que especificar la clase contratista
     */
    private Object contratista;

    private String descripcion;

    private ArrayList<Registro> registros;

    private int totalTrabajadores;

    private double totalHorasTrabajadas;

    // id del encargado
    private String nombreDelEncargado;
}
