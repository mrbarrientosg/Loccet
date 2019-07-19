package view;

import base.View;
import cell.ProyectoCell;
import controller.ProyectoController;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Proyecto;
import router.AgregarProyectoRouter;
import router.DetalleProyectoRouter;
import router.ProyectoRouter;
import java.time.LocalDate;
import java.util.Optional;

public class ProyectoView extends View {

    private ProyectoController controller;

    private ProyectoRouter router;

    private ObservableList<ProyectoCell> listProyectos;

    private FilteredList<ProyectoCell> filteredProyect;

    @FXML
    private TextField searchText;

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
        searchText.setOnKeyReleased(event -> {
            searchText.textProperty().addListener((observable, oldValue, newValue) -> {
                didSearch(newValue);
            });
            refreshTable();
        });
    }

    @Override
    public void viewDidShow() {
        inicializarTablaProyecto();
        cargarDatos();
    }

    public void viewDidClose(){

    }

    private void cargarDatos(){
        controller.fetchProyectos(proyectoCells -> {
            listProyectos = proyectoCells;
            filteredProyect = new FilteredList<>(listProyectos, e -> true);
            refreshTable();
        });
    }

    private void inicializarTablaProyecto() {
        iDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameProyectColumn.setCellValueFactory(new PropertyValueFactory<>("nombreProyecto"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("fechaInicio"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("fechaTermino"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("estimacion"));
        clientColumn.setCellValueFactory(new PropertyValueFactory<>("cliente"));
    }

    private SortedList<ProyectoCell> sortedList() {
        return new SortedList<>(filteredProyect);
    }

    private void refreshTable(){
        SortedList sortedList = sortedList();
        tableView.setItems(sortedList);
        sortedList.comparatorProperty().bind(tableView.comparatorProperty());
    }

   //Filtra los proyectos
    private void didSearch(String query) {
        filteredProyect.setPredicate(ProyectoCell ->
                ProyectoCell.getNombre().toLowerCase().contains(query.toLowerCase()) ||
                        ProyectoCell.getId().toLowerCase().contains(query.toLowerCase()) ||
                        ProyectoCell.getCliente().toLowerCase().contains(query.toLowerCase()) ||
                        ProyectoCell.getFechaInicio().toString().contains(query.toLowerCase())
                        //ProyectoCell.getFechaTermino().toString().contains(query.toLowerCase())
        );
    }

    private ProyectoCell selection() {
        int selection = tableView.getSelectionModel().getSelectedIndex();
        if(selection>=0){
            ProyectoCell proyect = tableView.getItems().get(selection);
            return proyect;
        }
        return null;
    }

    @FXML
    public void lookDetails(ActionEvent event){
        ProyectoCell cell = selection();
        if (cell == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("No selecciono ningun proyecto");
            alert.setContentText("Por favor, seleccionar un proyecto");
            alert.showAndWait();
            return;
        };
        Proyecto p = controller.buscarProyecto(cell.getId());
        DetalleProyectoView view = DetalleProyectoRouter.create(p);
        view.modal().withStyle(StageStyle.TRANSPARENT)
                .show().getScene().setFill(Color.TRANSPARENT);
    }


    @FXML
    public void createProyect(ActionEvent event){
        AgregarProyectoView view = AgregarProyectoRouter.create();
        view.modal().withStyle(StageStyle.TRANSPARENT)
                .show().getScene().setFill(Color.TRANSPARENT);
        // TODO: analizar
        //cargarDatos();
    }

    @FXML
    public void deleteProyect(ActionEvent event){
        ProyectoCell cell = selection();
        if (cell == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("No selecciono ningun proyecto");
            alert.setContentText("Por favor seleccione un proyecto");

            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alerta");
        alert.setHeaderText("Esta accion borrara el proyecto");
        alert.setContentText("¿Esta seguro de que desea continuar?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            controller.deleteProyect(cell.getId());
            cargarDatos();
        }

    }

    public void setController(ProyectoController controller) { this.controller = controller; }

    public void setRouter(ProyectoRouter router) {
        this.router = router;
    }
}

