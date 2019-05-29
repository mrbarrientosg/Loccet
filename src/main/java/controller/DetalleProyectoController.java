package controller;

import base.Controller;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Proyecto;
import network.LoccetService;
import network.API.ProyectoAPI;
import view.DetalleProyectoView;

import java.util.logging.Level;

public class DetalleProyectoController extends Controller {

    private DetalleProyectoView view;

    private Proyecto proyecto;

    private Proyecto oldProyecto;

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
    }

    private void loadData() {
        name.setValue(proyecto.getNombre());
        address.setValue(proyecto.getLocalizacion().getDireccion());
        country.setValue(proyecto.getLocalizacion().getPais());
        state.setValue(proyecto.getLocalizacion().getEstado());
        city.setValue(proyecto.getLocalizacion().getCiudad());
        client.setValue(proyecto.getNombreCliente());
    }

    public void save() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Proyecto.class, new Proyecto.ProyectoSerializer())
                .create();

        LoccetService.getInstance()
                .call(ProyectoAPI.UPDATE, gson.toJsonTree(proyecto).getAsJsonObject())
                .subscribe(json -> {
                    System.out.println(json);
                }, throwable -> {
                    LOGGER.log(Level.SEVERE, "", throwable);
                });

    }


    public void setView(DetalleProyectoView view) {
        this.view = view;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
        oldProyecto = new Proyecto(proyecto);
        loadData();
        view.bind();
    }

    public String getIdProyecto() { return proyecto.getId(); }

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

