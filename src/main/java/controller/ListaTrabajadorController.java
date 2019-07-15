package controller;

import base.Controller;
import cell.TrabajadorCell;
import com.google.gson.JsonObject;
import exceptions.ItemExisteException;
import io.reactivex.Observable;
import io.reactivex.Single;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Proyecto;
import model.Trabajador;
import delegate.SearchEmployeeDelegate;
import network.endpoint.TrabajadorAPI;
import network.service.Router;
import view.ListaTrabajadorView;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.stream.Collectors;

public final class ListaTrabajadorController extends Controller implements SearchEmployeeDelegate {

    private final ListaTrabajadorView view;

    private final Proyecto model;

    private Router<TrabajadorAPI> service;

    public ListaTrabajadorController(ListaTrabajadorView view, Proyecto model) {
        this.view = view;
        this.model = model;
        service = Router.getInstance();
    }

    public ObservableList<TrabajadorCell> loadData() {
        return FXCollections.observableList(model.getTrabajadores().stream().map(TrabajadorCell::new).collect(Collectors.toList()));
    }

    public Single<ObservableList<TrabajadorCell>> searchEmployee(String text) {
        return Observable.fromIterable(model.getTrabajadores())
                .filter(trabajador -> trabajador.getRut().contains(text))
                .map(TrabajadorCell::new)
                .toList()
                .map(FXCollections::observableList);
    }

    @Override
    public void selectedEmployee(Trabajador value) {
        if (model.obtenerTrabajador(value.getRut()) != null) return;

        try {
            model.agregarTrabajador(value);

            view.addEmployee(new TrabajadorCell(value));

            JsonObject json = new JsonObject();

            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                    .withZone(ZoneId.systemDefault());

            json.addProperty("fecha_incio_trabajador", timeFormatter.format(Instant.now()));
            json.addProperty("rut_trabajador", value.getRut());
            json.addProperty("id_proyecto", model.getId());

            service.request(TrabajadorAPI.ADD_TO_PROJECT, json)
                    .subscribe(System.out::println, throwable -> {
                        LOGGER.log(Level.SEVERE, "", throwable);
                    });
        } catch (ItemExisteException e) {
            // TODO: Falta enviar alerta
            e.printStackTrace();
        }

    }

    public Trabajador obtenerTrabajador(String rut) {
        return model.obtenerTrabajador(rut);
    }
}
