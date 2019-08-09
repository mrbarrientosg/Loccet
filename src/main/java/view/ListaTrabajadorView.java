package view;

import base.Injectable;
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
import model.Trabajador;
import util.Alert;
import util.AsyncTask;
import java.util.ListIterator;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * Vista que despliega los trabajadores
 *
 * @author Matias Barrientos
 */
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
        controller = Injectable.find(ListaTrabajadorController.class);
        controller.setView(this);

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
        Injectable.find(BuscarTrabajadorView.class).display(controller);
    }

    @FXML
    private void verDetalleTrabajador(ActionEvent event) {
        TrabajadorCell cell = tableView.getSelectionModel().getSelectedItem();
        Injectable.find(DetalleTrabajadorView.class).display(cell.getRut(), this);
    }

    @FXML
    private void deleteAction(ActionEvent event) {
        TrabajadorCell cell = tableView.getSelectionModel().getSelectedItem();

        Optional<ButtonType> result = Alert.confirmation()
                .withTitle("Eliminar Trabajador")
                .withDescription("¿Desea continuar?")
                .withButton(ButtonType.OK, ButtonType.CANCEL)
                .build().showAndWait();

        if (result.get() == ButtonType.OK){
            controller.eliminarTrabajador(cell.getRut());
            tableView.getItems().remove(cell);
        }

    }

    public void addEmployee(TrabajadorCell cell) {
        tableView.getItems().add(cell);
        searchTextField.setText("");
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

    public ListaTrabajadorView display(String id) {
        controller.setIdProyecto(id);
        return this;
    }

}
