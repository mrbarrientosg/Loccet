package view;

import base.Injectable;
import base.View;
import cell.ProyectoCell;
import controller.ProyectoController;
import delegate.SaveProyectoDelegate;
import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import io.reactivex.schedulers.Schedulers;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;
import model.Proyecto;
import util.AsyncTask;
import java.util.ListIterator;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class ProyectoView extends View implements SaveProyectoDelegate {

    private ProyectoController controller;

    @FXML
    private TextField searchText;

    @FXML
    private Button detailButton;

    @FXML
    private Button deleteButton;

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
        controller = Injectable.find(ProyectoController.class);

        iDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameProyectColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("fechaInicio"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("fechaTermino"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("estimacion"));
        clientColumn.setCellValueFactory(new PropertyValueFactory<>("cliente"));

        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                detailButton.setDisable(false);
                deleteButton .setDisable(false);
            }else{
                detailButton.setDisable(true);
                deleteButton.setDisable(true);
            }
        });
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

    private ProyectoCell selection() {
        return tableView.getSelectionModel().getSelectedItem();
    }

    @FXML
    public void lookDetails(ActionEvent event){
        ProyectoCell cell = selection();
        Injectable.find(DetalleProyectoView.class).display(cell.getId(), this);
    }


    @FXML
    public void createProyect(ActionEvent event){
        Injectable.find(CrearProyectoView.class).display(this);
    }

    @FXML
    public void deleteProyect(ActionEvent event){
        ProyectoCell cell = selection();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alerta");
        alert.setHeaderText("Esta accion borrara el proyecto");
        alert.setContentText("¿Esta seguro de que desea continuar?");

        Optional<ButtonType> result = alert.showAndWait();

        result.ifPresent(buttonType -> {
            if (buttonType == ButtonType.OK){
                controller.deleteProyect(cell.getId());
                tableView.getItems().remove(cell);
            }
        });
    

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

            searchText.setText("");
        });
    }
}

