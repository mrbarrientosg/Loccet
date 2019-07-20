package view;

import base.View;
import cell.ProyectoCell;
import cell.TrabajadorCell;
import controller.ProyectoController;
import delegate.SaveProyectoDelegate;
import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import io.reactivex.schedulers.Schedulers;
import javafx.application.Platform;
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
import util.AsyncTask;

import java.time.LocalDate;
import java.util.ListIterator;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class ProyectoView extends View implements SaveProyectoDelegate {

    private ProyectoController controller;

    private ProyectoRouter router;

    @FXML
    private TextField searchText;

    @FXML
    private Button detailButton;

    @FXML
    private Button createProyectButton;

    @FXML
    private TableView<ProyectoCell> tableView;

    @FXML
    private TableColumn<ProyectoCell, String> iDColumn;

    @FXML
    private TableColumn<ProyectoCell, String> nameProyectColumn;

    @FXML
    private TableColumn<ProyectoCell, String> clientColumn;

    @FXML
    private TableColumn<ProyectoCell, Double> amountColumn;

    @FXML
    private TableColumn<ProyectoCell, String> startDateColumn;

    @FXML
    private TableColumn<ProyectoCell, String> endDateColumn;

    @Override
    public void viewDidLoad() {
        inicializarTablaProyecto();
    }

    @Override
    public void viewDidShow() {
        Observable<String> textInputs = JavaFxObservable.valuesOf(searchText.textProperty());

        textInputs
                .debounce(300, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .map(controller::searchProyecto)
                .subscribeOn(Schedulers.io())
                .observeOn(JavaFxScheduler.platform())
                .subscribe(tableView::setItems);
    }

    private void inicializarTablaProyecto() {
        iDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameProyectColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("fechaInicio"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("fechaTermino"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("estimacion"));
        clientColumn.setCellValueFactory(new PropertyValueFactory<>("cliente"));
    }

    private ProyectoCell selection() {
        int selection = tableView.getSelectionModel().getSelectedIndex();
        if(selection >= 0){
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
        DetalleProyectoView view = DetalleProyectoRouter.create(p, this);
        view.modal().withStyle(StageStyle.TRANSPARENT)
                .show().getScene().setFill(Color.TRANSPARENT);
    }


    @FXML
    public void createProyect(ActionEvent event){
        AgregarProyectoView view = AgregarProyectoRouter.create(this);
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
        }

    }

    public void setController(ProyectoController controller) { this.controller = controller; }

    public void setRouter(ProyectoRouter router) {
        this.router = router;
    }

    @Override
    public void didSaveProyecto(Proyecto proyecto) {
        AsyncTask.supplyAsync(() -> {
            ListIterator<ProyectoCell> iterator = tableView.getItems().listIterator();

            while (iterator.hasNext()) {
                ProyectoCell cell = iterator.next();
                if (cell.getId().equals(proyecto.getId())) {
                    Platform.runLater(() -> {
                        iterator.set(new ProyectoCell(proyecto));
                    });
                    return true;
                }
            }
            return false;
        }).thenAccept(replace -> {
            if (!replace)
                tableView.getItems().add(new ProyectoCell(proyecto));
        });
    }
}

