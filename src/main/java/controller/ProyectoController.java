package controller;

import base.Controller;
import cell.ProyectoCell;
import cell.TrabajadorCell;
import com.google.gson.JsonObject;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Constructora;
import model.Proyecto;
import network.endpoint.ProyectoAPI;
import network.service.NetService;
import specification.ProyectoByQuerySpecification;
import specification.TrabajadorByQuerySpecification;
import util.AsyncTask;
import view.ProyectoView;

import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Controlar de la vista ProyectoView
 *
 * @see view.ProyectoView
 *
 * @author Matias Zuñiga
 * @author Sebastian Fuenzalida
 */
public final class ProyectoController extends Controller {

    private Constructora model = Constructora.getInstance();

    private ProyectoView view;

    public ObservableList<ProyectoCell> searchProyecto(String text) {
        ObservableList<ProyectoCell> cells = FXCollections.observableArrayList();

        StreamSupport.stream(model.buscarProyecto(new ProyectoByQuerySpecification(text)).spliterator(), false)
                .map(ProyectoCell::new)
                .forEach(cells::add);

        return cells;
    }

    public void deleteProyect(String id){
        model.eliminarProyecto(id);

        NetService service = NetService.getInstance();

        JsonObject json = new JsonObject();

        json.addProperty("id_proyecto", id);

        service.request(ProyectoAPI.REMOVE, json)
                .subscribe(System.out::println, throwable -> {
                    LOGGER.log(Level.SEVERE, "", throwable);
                });
    }

    public void setView(ProyectoView view) {
        this.view = view;
    }

}

