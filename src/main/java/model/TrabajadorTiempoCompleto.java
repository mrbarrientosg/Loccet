package model;

import java.math.BigDecimal;

public class TrabajadorTiempoCompleto extends Trabajador {

    public TrabajadorTiempoCompleto() { }

    public TrabajadorTiempoCompleto(TrabajadorTiempoCompleto other) {
        super(other);
    }

    @Override
    public BigDecimal calcularSueldo() {
        return BigDecimal.valueOf(8).multiply(getEspecialidad().getSueldoPorHora());
    }
}
