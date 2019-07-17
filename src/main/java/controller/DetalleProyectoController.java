package controller;

import base.Controller;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exceptions.EmptyFieldException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Proyecto;
import network.endpoint.ProyectoAPI;
import network.service.Router;
import view.DetalleProyectoView;

import java.util.logging.Level;

public class DetalleProyectoController extends Controller {

    private DetalleProyectoView view;

    private Proyecto model;

    private Proyecto oldProyecto;

    private StringProperty name = new SimpleStringProperty();

    private StringProperty address = new SimpleStringProperty();

    private StringProperty country = new SimpleStringProperty();

    private StringProperty state = new SimpleStringProperty();

    private StringProperty city = new SimpleStringProperty();

    private StringProperty client = new SimpleStringProperty();

    private Router<ProyectoAPI> service = Router.getInstance();

    //private ObjectProperty<LocalDate> startDate = new SimpleStringProperty();

    //private ObjectProperty<LocalDate> endDate = new SimpleStringProperty();

    public void actualizar() throws EmptyFieldException {
        model.setNombre(name.get());
        model.getLocalizacion().setDireccion(address.get());
        model.getLocalizacion().setPais(country.get());
        model.getLocalizacion().setEstado(state.get());
        model.getLocalizacion().setCiudad(city.get());
        model.setNombreCliente(client.get());
    }

    private void loadData() {
        name.setValue(model.getNombre());
        address.setValue(model.getLocalizacion().getDireccion());
        country.setValue(model.getLocalizacion().getPais());
        state.setValue(model.getLocalizacion().getEstado());
        city.setValue(model.getLocalizacion().getCiudad());
        client.setValue(model.getNombreCliente());
    }

    public void save() {
        if (oldProyecto.equals(model))
            return;

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Proyecto.class, new Proyecto.ProyectoSerializer())
                .create();

        service.request(ProyectoAPI.UPDATE, gson.toJsonTree(model).getAsJsonObject())
                .subscribe(System.out::println, throwable -> {
                    LOGGER.log(Level.SEVERE, "", throwable);
                });

    }

    public void setView(DetalleProyectoView view) {
        this.view = view;
    }

    public void setModel(Proyecto model) {
        this.model = model;
        oldProyecto = new Proyecto(model);
        loadData();
        view.bind();
    }

    public String getIdProyecto() { return model.getId(); }

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

