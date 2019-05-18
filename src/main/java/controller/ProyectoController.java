package controller;

import base.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import model.Constructora;
import model.Proyecto;

import java.util.stream.Collectors;

public class ProyectoController extends Controller {

    private Constructora model;
    private ObservableList<Proyecto> listProyectos;
    private FilteredList<Constructora> filteredMateriales;

    public SortedList<Constructora> sortedList() {
        return new SortedList<>(filteredMateriales);
    }
    private void cargarDatos() {
//        listProyectos = FXCollections.observableList(model.getListaProyecto().stream().map(Constructora::new).collect(Collectors.toList()));
  //      filteredMateriales = new FilteredList<>(listProyectos, e -> true);
    }
}
