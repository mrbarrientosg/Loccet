package controller;

import base.Controller;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Proyecto;
import view.DetalleProyectoView;

public class DetalleProyectoController extends Controller {

    private DetalleProyectoView view;

    private Proyecto proyecto;

    private StringProperty name = new SimpleStringProperty();

    private StringProperty address = new SimpleStringProperty();

    private StringProperty country = new SimpleStringProperty();

    private StringProperty state = new SimpleStringProperty();

    private StringProperty city = new SimpleStringProperty();

    private StringProperty client = new SimpleStringProperty();

    //private StringProperty startDate = new SimpleStringProperty();

    //private StringProperty endDate = new SimpleStringProperty();


    public void actualizar() {
        proyecto.setNombre(name.get());
        proyecto.getLocalizacion().setDireccion(address.get());
        proyecto.getLocalizacion().setPais(country.get());
        proyecto.getLocalizacion().setEstado(state.get());
        proyecto.getLocalizacion().setCiudad(city.get());
        proyecto.setNombreCliente(client.get());

        // falta servicio para actualizar en la bd
    }

    public void setView(DetalleProyectoView view) {
        this.view = view;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty addressProperty() {
        return address;
    }

    public StringProperty countryProperty() {
        return country;
    }

    public StringProperty stateProperty() {
        return state;
    }

    public StringProperty cityProperty() {
        return city;
    }

    public StringProperty clientProperty() {
        return client;
    }
}

