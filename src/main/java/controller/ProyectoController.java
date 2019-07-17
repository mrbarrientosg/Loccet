package controller;

import base.Controller;
import cell.ProyectoCell;
import com.google.gson.JsonObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import model.Constructora;
import model.Proyecto;
import network.endpoint.ProyectoAPI;
import network.service.Router;
import sun.rmi.runtime.RuntimeUtil;
import view.ProyectoView;

import java.util.logging.Level;
import java.util.stream.Collectors;

public class ProyectoController extends Controller {

    private Constructora model = Constructora.getInstance();

    private ProyectoView view;

    public void setView(ProyectoView view) {
        this.view = view;
    }

    public ObservableList<ProyectoCell> getList(){
        return FXCollections.observableList(model.getListaProyecto().stream().map(ProyectoCell::new).collect(Collectors.toList()));
    }

    public Proyecto buscarProyecto(String id) {
        return model.obtenerProyecto(id);
    }

    public void deleteProyect(String id){
        model.eliminarProyecto(id);

        Router<ProyectoAPI> service = Router.getInstance();

        JsonObject json = new JsonObject();

        json.addProperty("id_proyecto", id);

        service.request(ProyectoAPI.REMOVE, json)
                .subscribe(System.out::println, throwable -> {
                    LOGGER.log(Level.SEVERE, "", throwable);
                });
    }

}

