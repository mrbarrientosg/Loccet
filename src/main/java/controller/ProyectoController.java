package controller;

import base.Controller;
import cell.ProyectoCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import model.Constructora;
import model.Proyecto;

import java.util.stream.Collectors;

public class ProyectoController extends Controller {

    private Constructora model;

    /*public ProyectoController(Constructora model, ObservableList<ProyectoCell> listProyectos, FilteredList<ProyectoCell> filteredProyect) {
        this.model = model;
        this.listProyectos = listProyectos;
        this.filteredProyect = filteredProyect;
        cargarDatos();
    }*/

    public  ObservableList<ProyectoCell> getList(){
        return FXCollections.observableList(model.getListaProyecto().stream().map(ProyectoCell::new).collect(Collectors.toList()));
    }

}

