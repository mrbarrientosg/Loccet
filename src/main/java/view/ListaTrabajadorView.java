package view;

import base.View;
import cell.TrabajadorCell;
import controller.ListaTrabajadorController;
import delegate.SaveTrabajadorDelegate;
import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import io.reactivex.schedulers.Schedulers;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;
import model.Trabajador;
import router.BuscarTrabajadorRouter;
import router.DetalleTrabajadorRouter;
import util.AsyncTask;

import java.util.ListIterator;
import java.util.Optional;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public final class ListaTrabajadorView extends View implements SaveTrabajadorDelegate {

    private ListaTrabajadorController controller;

    @FXML
    private Button deleteButton;

    @FXML
    private Button detailButton;

    @FXML
    private TextField searchTextField;

    @FXML
    private TableView<TrabajadorCell> tableView;

    @FXML
    private TableColumn<TrabajadorCell, String> rutColumn;

    @FXML
    private TableColumn<TrabajadorCell, String> nameColumn;

    @FXML
    private TableColumn<TrabajadorCell, String> lastNameColumn;

    @FXML
    private TableColumn<TrabajadorCell, String> specialityColumn;

    @FXML
    private TableColumn<TrabajadorCell, String> typeColumn;

    @FXML
    private TableColumn<TrabajadorCell, String> horasColumn;

    @Override
    public void viewDidLoad() {
        rutColumn.setCellValueFactory(new PropertyValueFactory<>("rut"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        specialityColumn.setCellValueFactory(new PropertyValueFactory<>("nombreEspecialidad"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("tipoTrabajador"));
        horasColumn.setCellValueFactory(new PropertyValueFactory<>("horasPorDia"));

        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                deleteButton.setDisable(false);
                detailButton.setDisable(false);
            } else {
                deleteButton.setDisable(true);
                detailButton.setDisable(true);
            }
        });

    }

    @Override
    public void viewDidShow() {
        Observable<String> textInputs = JavaFxObservable.valuesOf(searchTextField.textProperty());

        textInputs
                .debounce(300, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .flatMap(value -> controller.searchEmployee(value)
                        .onErrorReturnItem(FXCollections.emptyObservableList())
                        .toObservable())
                .subscribeOn(Schedulers.io())
                .observeOn(JavaFxScheduler.platform())
                .subscribe(tableView::setItems);
    }

    @Override
    public void viewDidClose() {
        tableView.getItems().clear();
    }

    @FXML
    private void addEmployeeAction(ActionEvent event) {
        BuscarTrabajadorView view = BuscarTrabajadorRouter.create(controller);
        view.modal().withOwner(null).withStyle(StageStyle.TRANSPARENT)
                .show().getScene().setFill(Color.TRANSPARENT);
    }

    @FXML
    private void verDetalleTrabajador(ActionEvent event) {
        TrabajadorCell cell = tableView.getSelectionModel().getSelectedItem();

        DetalleTrabajadorView view = DetalleTrabajadorRouter.create(cell.getRut(), this);
        view.modal().withOwner(null).withStyle(StageStyle.TRANSPARENT)
                .show().getScene().setFill(Color.TRANSPARENT);
    }

    @FXML
    private void deleteAction(ActionEvent event) {
        TrabajadorCell cell = tableView.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText("Esta accion eliminara un trabajador");
        alert.setContentText("¿Desea continuar?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK){
            controller.eliminarTrabajador(cell.getRut());
            tableView.getItems().remove(cell);
        }

    }

    public void addEmployee(TrabajadorCell cell) {
        tableView.getItems().add(cell);
        searchTextField.setText("");
    }

    public void setController(ListaTrabajadorController controller) {
        this.controller = controller;
    }

    @Override
    public void didSaveTrabajador(Trabajador trabajador) {
        AsyncTask.supplyAsync(() -> {
            ListIterator<TrabajadorCell> iterator = tableView.getItems().listIterator();

            while (iterator.hasNext()) {
                TrabajadorCell cell = iterator.next();
                if (cell.getRut().equals(trabajador.getRut())) {
                    Platform.runLater(() -> {
                        iterator.set(new TrabajadorCell(trabajador));
                    });
                    return true;
                }
            }
            return false;
        });
    }

}
