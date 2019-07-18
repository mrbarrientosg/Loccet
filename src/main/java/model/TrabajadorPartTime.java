package model;

import java.math.BigDecimal;

public class TrabajadorPartTime extends Trabajador {

    private int cantidadHoraTrabajada;

    public TrabajadorPartTime(int cantidadHoraTrabajada) {
        this.cantidadHoraTrabajada = cantidadHoraTrabajada;
    }

    public TrabajadorPartTime(TrabajadorPartTime other) {
        super(other);
        this.cantidadHoraTrabajada = other.cantidadHoraTrabajada;
    }

    public void setCantidadHoraTrabajada(int cantidadHoraTrabajada) {
        this.cantidadHoraTrabajada = cantidadHoraTrabajada;
    }

    public int getCantidadHoraTrabajada() {
        return cantidadHoraTrabajada;
    }

    @Override
    public BigDecimal calcularSueldo() {
        return BigDecimal.valueOf(cantidadHoraTrabajada).multiply(getEspecialidad().getPagoPorHora());
    }
}
