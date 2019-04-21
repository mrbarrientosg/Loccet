package model;

public class Especialidad {

    private final String nombre;

    private final Double cantidadDeHoras;

    private final Double sueldoPorHora;

    public Especialidad(String nombre, Double horasPorTrabajar, Double sueldoPorHora) {
        this.nombre = nombre;
        this.cantidadDeHoras = horasPorTrabajar;
        this.sueldoPorHora = sueldoPorHora;
    }

    public Double calcularPago(Double horasTrabajadas) {
        // TODO: Implementar metodo
        return 0.0;
    }

    public Double calcularPago(Double horasTrabajadas, Double viatico) {
        // TODO: Implementar metodo
        return 0.0;
    }

    public Double calcularPago(Double horasTrabajadas, Double viatico, Double bono) {
        // TODO: Implementar metodo
        return 0.0;
    }

    public String getNombre() {
        return nombre;
    }

    public Double getCantidadDeHoras() {
        return cantidadDeHoras;
    }

    public Double getSueldoPorHora() {
        return sueldoPorHora;
    }

    public double sueldoTotal() {
        return cantidadDeHoras * sueldoPorHora;
    }
}
