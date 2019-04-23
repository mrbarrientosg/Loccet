package model;

public class Localizacion {

    private String direccion;

    private String codigoPostal;

    private String pais;

    private String estado;

    private String ciudad;

    private Localizacion(Builder builder) {
        this.direccion = builder.direccion;
        this.codigoPostal = builder.codigoPostal;
        this.pais = builder.pais;
        this.estado = builder.estado;
        this.ciudad = builder.ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public static class Builder {

        private String direccion;

        private String codigoPostal;

        private String pais;

        private String estado;

        private String ciudad;

        public Builder direccion(String direccion) {
            this.direccion = direccion;
            return this;
        }

        public Builder codigoPostal(String codigoPostal) {
            this.codigoPostal = codigoPostal;
            return this;
        }

        public Builder pais(String pais) {
            this.pais = pais;
            return this;
        }

        public Builder estado(String estado) {
            this.estado = estado;
            return this;
        }

        public Builder ciudad(String ciudad) {
            this.ciudad = ciudad;
            return this;
        }

        public Localizacion build() {
            return new Localizacion(this);
        }
    }
}
