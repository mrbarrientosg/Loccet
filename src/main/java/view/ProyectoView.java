package view;

import base.View;
import cell.MaterialCell;
import controller.ProyectoController;
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

    @FXML
    private TextField searchField;

    @FXML
    private Button detailButton;

    @FXML
    private Button createProyectButton;

    @FXML
    private TableView<?> tableView;

    @FXML
    private TableColumn<Proyecto,String> iDColumn;

    @FXML
    private TableColumn<Proyecto,String> nameProyectColumn;

    @FXML
    private TableColumn<Proyecto,String> clientColumn;

    @FXML
    private TableColumn<Proyecto,Double> amountColumn;

    @FXML
    private TableColumn<Proyecto, LocalDate> startDateColumn;

    @FXML
    private TableColumn<Proyecto,LocalDate> endDateColumn;

    @Override
    public void viewDidLoad() {
        inicializarTablaMateriales();
    }

    public void viewDidClose(){

    }

    private void inicializarTablaMateriales() {
        iDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameProyectColumn.setCellValueFactory(new PropertyValueFactory<>("nombreProyecto"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("fechaTermino"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("fechaInicio"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("estimacion"));
        clientColumn.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        refreshTable();
    }
    private void refreshTable() {
        SortedList sortedList = controller.sortedList();
        tableView.setItems(sortedList);
        sortedList.comparatorProperty().bind(tableView.comparatorProperty());
    }


}
