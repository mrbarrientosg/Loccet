package view;

import base.View;
import cell.ProyectoCell;
import cell.TrabajadorCell;
import controller.RRHHController;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import io.reactivex.schedulers.Schedulers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.Proyecto;
import router.RRHHRouter;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class RRHHView extends View {

    private RRHHController controller;

    private RRHHRouter router;

    @FXML
    private TextField searchField;

    @FXML
    private ComboBox<ProyectoCell> proyectList;

    @FXML
    private Button createTrabajador;

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
    private TableColumn<TrabajadorCell, String> emailColumn;

    @FXML
    private TableColumn<TrabajadorCell, String> telephoneConlumn;

    @Override
    public void viewDidLoad() {
        rutColumn.setCellValueFactory(new PropertyValueFactory<>("rut"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        specialityColumn.setCellValueFactory(new PropertyValueFactory<>("nombreEspecialidad"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("correoElectronico"));
        telephoneConlumn.setCellValueFactory(new PropertyValueFactory<>("telefono"));

        Callback<ListView<ProyectoCell>, ListCell<ProyectoCell>> factory = lv -> new ListCell<ProyectoCell>() {
            @Override
            protected void updateItem(ProyectoCell item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setText(null);
                } else {
                    setText(item.getId());
                }
            }
        };

        proyectList.setCellFactory(factory);
        proyectList.setButtonCell(factory.call(null));
    }

    @Override
    public void viewDidShow() {
        Observable<String> textInputs = JavaFxObservable.valuesOf(searchField.textProperty());

        textInputs
                .debounce(300, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .flatMap(value -> {
                    ProyectoCell cell = proyectList.getSelectionModel().getSelectedItem();
                    if (cell != null && cell.equals("Todos"))
                        return controller.searchEmployee(value)
                            .onErrorReturnItem(FXCollections.emptyObservableList())
                            .toObservable();

                    if (cell != null && !cell.equals("Todos"))
                        return controller.searchEmployeeProject(cell.getId(), value)
                                .onErrorReturnItem(FXCollections.emptyObservableList())
                                .toObservable();

                    return Observable.just(FXCollections.emptyObservableList());
                })
                .map(objects -> (ObservableList<TrabajadorCell>) objects)
                .subscribeOn(Schedulers.io())
                .observeOn(JavaFxScheduler.platform())
                .subscribe(list -> {
                    tableView.setItems(list);
                });

        ProyectoCell all = new ProyectoCell("Todos");
        proyectList.getItems().add(0, all);
        proyectList.getItems().addAll(controller.getProyectos());

        Observable<ProyectoCell> selected = JavaFxObservable.valuesOf(proyectList.getSelectionModel().selectedItemProperty());

        selected
                .filter(Objects::nonNull)
                .map(value -> {
                    if (value.getId().equals("Todos"))
                        return controller.searchEmployee("");

                    return controller.getEmployeesProject(value.getId());
                })
                .map(o -> (ObservableList<TrabajadorCell>) o)
                .subscribeOn(Schedulers.computation())
                .observeOn(JavaFxScheduler.platform())
                .subscribe(list -> {
                   tableView.setItems(list);
                });

        proyectList.getSelectionModel().selectFirst();
    }

    @Override
    public void viewDidClose() {
        proyectList.getItems().clear();
        tableView.getItems().clear();
    }

    public void setController(RRHHController controller) {
        this.controller = controller;
    }

    public void setRouter(RRHHRouter router) {
        this.router = router;
    }
}
