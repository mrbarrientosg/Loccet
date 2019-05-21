package controller;

import base.Controller;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Proyecto;
import view.DetalleProyectoView;

import java.time.LocalDate;

public class DetalleProyectoController extends Controller {

    private DetalleProyectoView view;

    private Proyecto proyecto;

    private StringProperty name = new SimpleStringProperty();

    private StringProperty address = new SimpleStringProperty();

    private StringProperty country = new SimpleStringProperty();

    private StringProperty state = new SimpleStringProperty();

    private StringProperty city = new SimpleStringProperty();

    private StringProperty client = new SimpleStringProperty();

    //private ObjectProperty<LocalDate> startDate = new SimpleStringProperty();

    //private ObjectProperty<LocalDate> endDate = new SimpleStringProperty();


    public void actualizar() {
        proyecto.setNombre(name.get());
        proyecto.getLocalizacion().setDireccion(address.get());
        proyecto.getLocalizacion().setPais(country.get());
        proyecto.getLocalizacion().setEstado(state.get());
        proyecto.getLocalizacion().setCiudad(city.get());
        proyecto.setNombreCliente(client.get());


        view.didSave();
        // falta servicio para actualizar en la bd
    }

    private void loadData() {
        name.setValue(proyecto.getNombre());
        address.setValue(proyecto.getLocalizacion().getCodigoPostal());
        country.setValue(proyecto.getLocalizacion().getPais());
        state.setValue(proyecto.getLocalizacion().getEstado());
        city.setValue(proyecto.getLocalizacion().getCiudad());
        client.setValue(proyecto.getNombreCliente());
    }


    public void setView(DetalleProyectoView view) {
        this.view = view;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
        loadData();
        view.bind();
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

