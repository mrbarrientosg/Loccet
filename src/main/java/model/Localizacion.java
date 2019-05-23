package model;

import com.google.gson.JsonObject;

public class Localizacion {

    // MARK: - Atributos

    private String direccion;

    private String codigoPostal;

    private String pais;

    private String estado;

    private String ciudad;


    // MARK: - Getter

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
}
