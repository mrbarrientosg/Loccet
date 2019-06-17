package model;

import exceptions.EmptyFieldException;
import util.StringUtils;

public class Localizacion {

    // MARK: - Atributos

    private Integer id;

    private String direccion;

    private String codigoPostal;

    private String pais;

    private String estado;

    private String ciudad;

    public Localizacion(String direccion, String codigoPostal, String pais, String estado, String ciudad) throws EmptyFieldException {
        setDireccion(direccion);
        setPais(pais);
        setEstado(estado);
        setCiudad(ciudad);
        this.codigoPostal = codigoPostal;
    }

    public Localizacion(String direccion, String pais, String estado, String ciudad) throws EmptyFieldException {
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

    public void setDireccion(String direccion) throws EmptyFieldException {
        if (StringUtils.isEmpty(direccion))
            throw new EmptyFieldException("Dirrección");

        this.direccion = direccion;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public void setPais(String pais) throws EmptyFieldException {
        if (StringUtils.isEmpty(pais))
            throw new EmptyFieldException("Pais");

        this.pais = pais;
    }

    public void setEstado(String estado) throws EmptyFieldException {
        if (StringUtils.isEmpty(estado))
            throw new EmptyFieldException("Estado");

        this.estado = estado;
    }

    public void setCiudad(String ciudad) throws EmptyFieldException {
        if (StringUtils.isEmpty(ciudad))
            throw new EmptyFieldException("Ciudad");

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
