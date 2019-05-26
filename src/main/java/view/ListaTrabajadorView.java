package view;

import base.Fragment;
import base.View;
import cell.TrabajadorCell;
import controller.ListaTrabajadorController;
import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import io.reactivex.schedulers.Schedulers;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import router.BuscarTrabajadorRouter;

import java.util.concurrent.TimeUnit;

public final class ListaTrabajadorView extends View {

    private ListaTrabajadorController controller;

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
    private TableColumn<TrabajadorCell, String> fechaNaciemientoColumn;

    @FXML
    private TableColumn<TrabajadorCell, String> specialityColumn;

    @FXML
    private TableColumn<TrabajadorCell, String> horasTrabajoColumn;

    @FXML
    private TableColumn<TrabajadorCell, String> sueldoHoraColumn;

    @FXML
    private TableColumn<TrabajadorCell, String> proyectColumn;

    @FXML
    private TableColumn<TrabajadorCell, String> telefonoColumn;

    @FXML
    private TableColumn<TrabajadorCell, String> emailColumn;

    @Override
    public void viewDidLoad() {
        rutColumn.setCellValueFactory(new PropertyValueFactory<>("rut"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        fechaNaciemientoColumn.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        specialityColumn.setCellValueFactory(new PropertyValueFactory<>("nombreEspecialidad"));
        horasTrabajoColumn.setCellValueFactory(new PropertyValueFactory<>("cantidadDeHoras"));
        sueldoHoraColumn.setCellValueFactory(new PropertyValueFactory<>("sueldoPorHora"));
        telefonoColumn.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("correoElectronico"));
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
                .subscribe(list -> {
                    tableView.setItems(list);
                });
    }

    @Override
    public void viewDidClose() {
        tableView.getItems().clear();
    }

    @FXML
    private void addEmployeeAction(ActionEvent event) {
        BuscarTrabajadorView view = BuscarTrabajadorRouter.create(controller);
        view.modal()
                .withOwner(null)
                .show();
    }

    @FXML
    private void verDetalleTrabajador(ActionEvent event) {

    }

    public void addEmployee(TrabajadorCell cell) {
        tableView.getItems().add(cell);
        searchTextField.setText("");
    }

    public void setController(ListaTrabajadorController controller) {
        this.controller = controller;
    }

}
