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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import model.Proyecto;
import model.Trabajador;
import router.DetalleProyectoRouter;
import router.DetalleTrabajadorRouter;
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
    private Button detailTrabajador;

    @FXML
    private Button deleteTrabajador;

    @FXML
    private TableView<TrabajadorCell> tableTrabajadores;

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

    @FXML
    private Button createEspecialidad;

    @FXML
    private Button deleteEspecialidad;

    @FXML
    private TableView<?> tableEspecialidades;

    @FXML
    private TableColumn<?, ?> nameEspecialidad;

    @FXML
    private TableColumn<?, ?> sueldoEspecialidad;

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
                    setText(item.getNombre());
                }
            }
        };

        proyectList.setCellFactory(factory);
        proyectList.setButtonCell(factory.call(null));

        deleteTrabajador.setOnAction(this::deleteTrabajadorAction);
        detailTrabajador.setOnAction(this::detailTrabajadorAction);
    }

    @Override
    public void viewDidShow() {
        ProyectoCell all = new ProyectoCell("Todos");
        proyectList.getItems().add(0, all);
        proyectList.getItems().addAll(controller.getProyectos());

        proyectList.getSelectionModel().selectFirst();

        Observable<String> textInputs = JavaFxObservable.valuesOf(searchField.textProperty());

        textInputs
                .debounce(300, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .flatMap(value -> {
                    ProyectoCell cell = proyectList.getSelectionModel().getSelectedItem();
                    if (cell != null && cell.getNombre().equals("Todos"))
                        return controller.searchEmployee(value)
                            .onErrorReturnItem(FXCollections.emptyObservableList())
                            .toObservable();

                    if (cell != null && !cell.getNombre().equals("Todos"))
                        return controller.searchEmployeeProject(cell.getId(), value)
                                .onErrorReturnItem(FXCollections.emptyObservableList())
                                .toObservable();

                    return Observable.just(FXCollections.emptyObservableList());
                })
                .map(objects -> (ObservableList<TrabajadorCell>) objects)
                .subscribeOn(Schedulers.io())
                .observeOn(JavaFxScheduler.platform())
                .subscribe(list -> {
                    tableTrabajadores.setItems(list);
                });


        Observable<ProyectoCell> selected = JavaFxObservable.valuesOf(proyectList.getSelectionModel().selectedItemProperty());

        selected
                .filter(Objects::nonNull)
                .flatMap(value -> {
                    if (value.getNombre().equals("Todos"))
                        return controller.searchEmployee("")
                                .toObservable();

                    return controller.getEmployeesProject(value.getId())
                            .toObservable();
                })
                .subscribeOn(Schedulers.computation())
                .observeOn(JavaFxScheduler.platform())
                .subscribe(list -> {
                    tableTrabajadores.setItems(list);
                });

    }

    @Override
    public void viewDidClose() {
        proyectList.getItems().clear();
        tableTrabajadores.getItems().clear();
    }

    private void deleteTrabajadorAction(ActionEvent event) {
        TrabajadorCell cell = tableTrabajadores.getSelectionModel().getSelectedItem();

        if (cell == null) return;

        controller.deleteTrabajador(cell.getRut());
    }

    private void detailTrabajadorAction(ActionEvent event) {
        TrabajadorCell cell = tableTrabajadores.getSelectionModel().getSelectedItem();
        if (cell == null) return;
        Trabajador t = controller.obtenerTrabajador(cell.getRut());
        DetalleTrabajadorView view = DetalleTrabajadorRouter.create(t);
        view.modal()
                .withStyle(StageStyle.TRANSPARENT)
                .show();
    }

    public void didDeleteTrabajador(String rut) {
        tableTrabajadores.getItems().removeIf(value -> value.getRut().equals(rut));
    }

    public void setController(RRHHController controller) {
        this.controller = controller;
    }

    public void setRouter(RRHHRouter router) {
        this.router = router;
    }
}
