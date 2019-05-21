package controller;

import base.Controller;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import io.reactivex.schedulers.Schedulers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Constructora;
import model.Trabajador;

import java.util.List;

public class BuscarTrabajadorController extends Controller {

    private Constructora model;

    public BuscarTrabajadorController() {
        model = Constructora.getInstance();
    }

    public ObservableList<Trabajador> loadData() {
        return FXCollections.observableList(model.getConjuntoTrabajadores());
    }

    public Single<ObservableList<String>> searchEmployee(String text) {
        return Observable.fromIterable(model.getConjuntoTrabajadores())
                .filter(trabajador -> trabajador.getRut().equals(text))
                .map(trabajador -> trabajador.getRut())
                .toList()
                .map(FXCollections::observableList);
    }
}
