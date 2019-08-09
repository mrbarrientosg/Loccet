package controller;

import base.Controller;
import cell.TrabajadorCell;
import com.google.gson.JsonObject;
import io.reactivex.Observable;
import io.reactivex.Single;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Constructora;
import model.Trabajador;
import delegate.SearchEmployeeDelegate;
import network.endpoint.TrabajadorAPI;
import network.service.NetService;
import util.AsyncTask;
import util.DateUtils;
import view.ListaTrabajadorView;
import java.time.Instant;
import java.util.function.Consumer;
import java.util.logging.Level;

/**
 * Controlar de la vista ListaTrabajador
 *
 * @see view.ListaTrabajadorView
 *
 * @author Matias Barrientos
 */
public final class ListaTrabajadorController extends Controller implements SearchEmployeeDelegate {

    private ListaTrabajadorView view;

    private Constructora model;

    private NetService service;

    private String idProyecto;

    public ListaTrabajadorController() {
        model = Constructora.getInstance();
        service = NetService.getInstance();
    }

    public void fetchTrabajadores(Consumer<ObservableList<TrabajadorCell>> callBack) {
        AsyncTask.supplyAsync(() -> {
            ObservableList<TrabajadorCell> cells = FXCollections.observableArrayList(e -> new javafx.beans.Observable[]{ new SimpleStringProperty(e.getRut())});

            model.getTrabajadores(idProyecto).forEach(trabajador -> cells.add(new TrabajadorCell(trabajador)));

            return cells;
        }).thenAccept(callBack);
    }


    public Single<ObservableList<TrabajadorCell>> searchEmployee(String text) {
        return Observable.fromIterable(model.getTrabajadores(idProyecto))
                .filter(trabajador -> trabajador.getRut().contains(text))
                .map(TrabajadorCell::new)
                .toList()
                .map(FXCollections::observableList);
    }

    @Override
    public void selectedEmployee(Trabajador value) {
        if (model.obtenerTrabajador(value.getRut(), idProyecto) != null) return;

        model.agregarTrabajador(idProyecto, value);

        view.addEmployee(new TrabajadorCell(value));

        JsonObject json = new JsonObject();

        json.addProperty("fecha_incio_trabajador", DateUtils.formatDate("yyyy-MM-dd HH:mm:ss", Instant.now()));
        json.addProperty("rut_trabajador", value.getRut());
        json.addProperty("id_proyecto", idProyecto);

        service.request(TrabajadorAPI.ADD_TO_PROJECT, json)
                .subscribe(System.out::println, throwable -> {
                    LOGGER.log(Level.SEVERE, "", throwable);
                });

    }

    public void eliminarTrabajador(String rut) {
        model.eliminarTrabajador(idProyecto, rut);

        NetService service = NetService.getInstance();

        JsonObject json = new JsonObject();

        json.addProperty("rut_trabajador", rut);
        json.addProperty("id_proyecto", idProyecto);

        System.out.println(json);

        service.request(TrabajadorAPI.REMOVE_FROM_PROJECT, json)
                .subscribe(System.out::println, throwable -> {
                    LOGGER.log(Level.SEVERE, "", throwable);
                });
    }

    public void setIdProyecto(String idProyecto) {
        this.idProyecto = idProyecto;
    }

    public void setView(ListaTrabajadorView view) {
        this.view = view;
    }
}
