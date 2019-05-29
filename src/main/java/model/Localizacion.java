package model;

import com.google.gson.JsonObject;

public class Localizacion {

    // MARK: - Atributos

    private Integer id;

    private String direccion;

    private String codigoPostal;

    private String pais;

    private String estado;

    private String ciudad;

    public Localizacion(String direccion, String codigoPostal, String pais, String estado, String ciudad) {
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
        this.pais = pais;
        this.estado = estado;
        this.ciudad = ciudad;
    }

    public Localizacion(String direccion, String pais, String estado, String ciudad) {
        this(direccion, null, pais, estado, ciudad);
    }

    public Localizacion(Localizacion other) {
        this.direccion = other.direccion;
        this.codigoPostal = other.codigoPostal;
        this.pais = other.pais;
        this.estado = other.estado;
        this.ciudad = other.ciudad;
    }

    // MARK: - Getter

    public Integer getId() {
        return id;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public String getPais() {
        return pais;
    }

    public String getEstado() {
        return estado;
    }

    public String getCiudad() {
        return ciudad;
    }

    // MARK: - Setter

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;

        if (!(obj instanceof Localizacion)) return false;

        Localizacion l = (Localizacion) obj;

        Boolean postal;

        if (l.codigoPostal != null && codigoPostal != null)
            postal = l.codigoPostal.equals(codigoPostal);
        else if (l.codigoPostal == null && codigoPostal == null)
            postal = true;
        else
            postal = false;

        return l.direccion.equals(direccion) &&
                postal && l.pais.equals(pais) &&
                l.estado.equals(estado) &&
                l.ciudad.equals(ciudad);
    }
}
