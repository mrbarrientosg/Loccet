package view;

import base.Injectable;
import base.View;
import cell.FilterCell;
import cell.ProyectoCell;
import cell.TrabajadorCell;
import controller.RRHHController;
import delegate.SaveTrabajadorDelegate;
import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import io.reactivex.schedulers.Schedulers;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Pair;
import model.Constructora;
import model.Trabajador;
import org.controlsfx.control.tableview2.FilteredTableColumn;
import org.controlsfx.control.tableview2.FilteredTableView;
import router.DetalleTrabajadorRouter;
import router.RRHHRouter;
import delegate.FilterDelegate;
import router.TrabajadorRouter;
import util.AsyncTask;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

public class RRHHView extends View implements SaveTrabajadorDelegate, FilterDelegate {

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
    private FilteredTableView<TrabajadorCell> tableTrabajadores;

    private FilteredTableColumn<TrabajadorCell, String> rutColumn;

    private FilteredTableColumn<TrabajadorCell, String> nameColumn;

    private FilteredTableColumn<TrabajadorCell, String> lastNameColumn;

    private FilteredTableColumn<TrabajadorCell, String> specialityColumn;

    private FilteredTableColumn<TrabajadorCell, String> emailColumn;

    private FilteredTableColumn<TrabajadorCell, String> telephoneConlumn;

    private FilteredTableColumn<TrabajadorCell, String> typeColumn;

    private FilteredTableColumn<TrabajadorCell, Integer> horasColumn;

    @FXML
    private Button filterButton;

    private ObservableList<Pair<String, Class<?>>> columnList;

    private ObservableList<FilterCell> filterCells;

    private Boolean disposable;

    @Override
    public void viewDidLoad() {
        disposable = false;

        filterCells = FXCollections.observableArrayList();
        columnList = FXCollections.observableArrayList();

        rutColumn = new FilteredTableColumn<>("Rut");
        nameColumn = new FilteredTableColumn<>("Nombre");
        lastNameColumn = new FilteredTableColumn<>("Apellido");
        specialityColumn = new FilteredTableColumn<>("Especialidad");
        emailColumn = new FilteredTableColumn<>("Correo Electronico");
        telephoneConlumn = new FilteredTableColumn<>("Telefono");
        typeColumn = new FilteredTableColumn<>("Tipo");
        horasColumn = new FilteredTableColumn<>("Horas por dia");

        rutColumn.setCellValueFactory(new PropertyValueFactory<>("rut"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        specialityColumn.setCellValueFactory(new PropertyValueFactory<>("nombreEspecialidad"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("correoElectronico"));
        telephoneConlumn.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("tipoTrabajador"));
        horasColumn.setCellValueFactory(new PropertyValueFactory<>("horasPorDia"));

        tableTrabajadores.getColumns().setAll(rutColumn, nameColumn, lastNameColumn, specialityColumn, emailColumn,
                telephoneConlumn, typeColumn, horasColumn);

        tableTrabajadores.getColumns().forEach(trabajadorCellTableColumn -> {
            if (trabajadorCellTableColumn.getText().equals("Horas por dia")) {
                columnList.add(new Pair<>(trabajadorCellTableColumn.getText(), Integer.class));
            } else {
                columnList.add(new Pair<>(trabajadorCellTableColumn.getText(), String.class));
            }
        });

        tableTrabajadores.fixedCellSizeProperty().setValue(40);

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
        filterButton.setOnAction(this::showFilterAction);
        createTrabajador.setOnAction(this::showAddTrabajadorAction);

        tableTrabajadores.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                detailTrabajador.setDisable(false);
                deleteTrabajador .setDisable(false);
            }else{
                detailTrabajador.setDisable(true);
                deleteTrabajador.setDisable(true);
            }
        });
    }

    @Override
    public void viewDidShow() {
        ProyectoCell all = new ProyectoCell("Todos");
        proyectList.getItems().add(0, all);
        controller.fetchProyectos(proyectList.getItems()::addAll);

        if (!disposable) {
            Observable<String> textInputs = JavaFxObservable.valuesOf(searchField.textProperty());

            textInputs
                    .debounce(300, TimeUnit.MILLISECONDS)
                    .distinctUntilChanged()
                    .map(value -> {
                        ProyectoCell cell = proyectList.getSelectionModel().getSelectedItem();
                        if (cell != null && cell.getNombre().equals("Todos"))
                            return controller.searchEmployee(value);

                        return controller.searchEmployeeProject(cell.getId(), value);
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(JavaFxScheduler.platform())
                    .subscribe(list -> {
                        tableTrabajadores.setItems(list);
                    });

            Observable<ProyectoCell> selected = JavaFxObservable.valuesOf(proyectList.getSelectionModel().selectedItemProperty());

            selected
                    .filter(Objects::nonNull)
                    .map(value -> {
                        if (value.getNombre().equals("Todos"))
                            return controller.searchEmployee("");

                        return controller.getEmployeesProject(value.getId());
                    })
                    .subscribeOn(Schedulers.computation())
                    .observeOn(JavaFxScheduler.platform())
                    .subscribe(list -> {
                        tableTrabajadores.setItems(list);
                        filters(getConditions());
                    });

            disposable = true;
        }

        proyectList.getSelectionModel().selectFirst();

    }

    @Override
    public void viewDidClose() {
        proyectList.getItems().clear();
    }

    private void showAddTrabajadorAction(ActionEvent event) {
        TrabajadorView view = TrabajadorRouter.create(Constructora.getInstance(), this);
        view.modal().withStyle(StageStyle.TRANSPARENT)
                .show().getScene().setFill(Color.TRANSPARENT);
    }

    private void showFilterAction(ActionEvent event) {
        FilterView view = Injectable.find(FilterView.class);
        view.setFilterCells(filterCells);
        view.setColumnList(columnList);
        view.setDelegate(this);
        view.modal().withResizable(true).withStyle(StageStyle.TRANSPARENT)
                .show().getScene().setFill(Color.TRANSPARENT);
    }

    private void deleteTrabajadorAction(ActionEvent event) {
        TrabajadorCell cell = tableTrabajadores.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText("Esta accion eliminara un trabajador");
        alert.setContentText("¿Desea continuar?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK){
            controller.deleteTrabajador(cell.getRut());
        }

    }

    private void detailTrabajadorAction(ActionEvent event) {
        TrabajadorCell cell = tableTrabajadores.getSelectionModel().getSelectedItem();
        Trabajador t = controller.obtenerTrabajador(cell.getRut());
        DetalleTrabajadorView view = DetalleTrabajadorRouter.create(t, this);
        view.modal().withStyle(StageStyle.TRANSPARENT)
                .show().getScene().setFill(Color.TRANSPARENT);
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

    @Override
    public void didSaveTrabajador(Trabajador trabajador) {
        AsyncTask.supplyAsync(() -> {
            ListIterator<TrabajadorCell> iterator = tableTrabajadores.getItems().listIterator();

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
        }).thenAccept(replace -> {
            ProyectoCell cell = proyectList.getSelectionModel().getSelectedItem();

            if (!replace && cell.getNombre().equals("Todos"))
                tableTrabajadores.getItems().add(new TrabajadorCell(trabajador));

            searchField.setText("");
        });

    }

    @Override
    public void filters(Map<String, Predicate> coditions) {
        tableTrabajadores.getColumns().forEach(column -> {
            FilteredTableColumn filteredTableColumn = (FilteredTableColumn) column;
            filteredTableColumn.setPredicate(coditions.get(column.getText()));
        });
    }

    private Map<String, Predicate> getConditions() {
        Map<String, Predicate> map = new HashMap<>();

        filterCells.forEach(cell -> {
            if (map.containsKey(cell.getColumnName())) {
                Predicate predicate = map.get(cell.getColumnName()).and(cell.getFilter());
                map.put(cell.getColumnName(), predicate);
            } else {
                map.put(cell.getColumnName(), cell.getFilter());
            }
        });

        return map;
    }
}
