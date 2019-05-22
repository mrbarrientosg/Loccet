package model;

import java.math.BigDecimal;

public class TrabajadorTiempoCompleto extends Trabajador {

    @Override
    public BigDecimal calcularSueldo() {
        return BigDecimal.valueOf(8).multiply(getEspecialidad().getSueldoPorHora());
    }
}
