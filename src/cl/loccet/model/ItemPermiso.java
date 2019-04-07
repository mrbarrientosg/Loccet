package cl.loccet.model;

import java.util.Date;

public class ItemPermiso {

    private String tipoPermiso;
    private String descripcion;
    private String cliente;
    private boolean aprobado;
    private Date fechaAprobacion;

    public ItemPermiso(String tipoPermiso, String descripcion, String cliente, boolean aprobado) {
        this.tipoPermiso = tipoPermiso;
        this.descripcion = descripcion;
        this.cliente = cliente;
        this.aprobado = aprobado;
    }
    public ItemPermiso(String tipoPermiso, String descripcion, String cliente, boolean aprobado, Date fechaAprobacion) {
        this.tipoPermiso = tipoPermiso;
        this.descripcion = descripcion;
        this.cliente = cliente;
        this.aprobado = aprobado;
        this.fechaAprobacion = fechaAprobacion;
    }

    public String getTipoPermiso() {
        return tipoPermiso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getCliente() {
        return cliente;
    }

    public boolean isAprobado() {
        return aprobado;
    }

    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }
}
