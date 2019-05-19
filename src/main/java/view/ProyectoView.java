package view;

import base.View;
import cell.MaterialCell;
import cell.ProyectoCell;
import controller.ProyectoController;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Proyecto;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class ProyectoView extends View {

    private ProyectoController controller;

    private ObservableList<ProyectoCell> listProyectos;

    private FilteredList<ProyectoCell> filteredProyect;

    @FXML
    private TextField searchField;

    @FXML
    private Button detailButton;

    @FXML
    private Button createProyectButton;

    @FXML
    private TableView<ProyectoCell> tableView;

    @FXML
    private TableColumn<ProyectoCell,String> iDColumn;

    @FXML
    private TableColumn<ProyectoCell,String> nameProyectColumn;

    @FXML
    private TableColumn<ProyectoCell,String> clientColumn;

    @FXML
    private TableColumn<ProyectoCell,Double> amountColumn;

    @FXML
    private TableColumn<ProyectoCell, LocalDate> startDateColumn;

    @FXML
    private TableColumn<ProyectoCell,LocalDate> endDateColumn;

    @Override
    public void viewDidLoad() {
        inicializarTablaProyecto();
    }

    public void viewDidClose(){

    }

    public void cargarDatos(){
        listProyectos = controller.getList();
        filteredProyect = new FilteredList<>(listProyectos, e -> true);
    }

    private void inicializarTablaProyecto() {
        iDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameProyectColumn.setCellValueFactory(new PropertyValueFactory<>("nombreProyecto"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("fechaTermino"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("fechaInicio"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("estimacion"));
        clientColumn.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        refreshTable();
    }

    public SortedList<ProyectoCell> sortedList() {
        return new SortedList<>(filteredProyect);
    }

    private void refreshTable(){
        SortedList sortedList = sortedList();
        tableView.setItems(sortedList);
        sortedList.comparatorProperty().bind(tableView.comparatorProperty());
    }

   /* //Filtra los proyectos
    public void didSearch(String query) {
        filteredProyect.setPredicate(ProyectoCell ->
                materialCell.getNombreProyecto().toLowerCase().contains(query.toLowerCase()) ||
                        materialCell.getId().toLowerCase().contains(query.toLowerCase()) ||
                        materialCell.getDescripcion().toLowerCase().contains(query.toLowerCase()) ||
                        materialCell.getUds().toLowerCase().contains(query.toLowerCase())
        );
    }*/

    public void setController(ProyectoController controller) { this.controller = controller; }
}

