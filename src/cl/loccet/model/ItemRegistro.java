package cl.loccet.model;

/**
 * Clase que maneja el registro de un reporte
 * que especialidad trabajo, cuantos y las horas
 */
public class ItemRegistro {

    private String especialidad;

    private int cantidadDeTrabajadores;

    private double horasTrabajadas;

    public ItemRegistro(String especialidad, int cantidadDeTrabajadores, double horasTrabajadas) {
        this.especialidad = especialidad;
        this.cantidadDeTrabajadores = cantidadDeTrabajadores;
        this.horasTrabajadas = horasTrabajadas;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public int getCantidadDeTrabajadores() {
        return cantidadDeTrabajadores;
    }

    public double getHorasTrabajadas() {
        return horasTrabajadas;
    }
}
