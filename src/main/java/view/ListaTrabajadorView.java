package view;

import base.View;
import cell.TrabajadorCell;
import controller.ListaTrabajadorController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public final class ListaTrabajadorView extends View {

    private HomeView master;

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
        //proyectColumn.setCellValueFactory(new PropertyValueFactory<>("sueldoPorHora"));
        telefonoColumn.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("correoElectronico"));

        searchTextField.setOnKeyReleased(event -> {
            searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                controller.didSearch(newValue);
            });
            refreshTable();
        });

        refreshTable();

        controller.selectedTrabajadorProperty().bind(tableView.getSelectionModel().selectedItemProperty());
    }

    @Override
    public void viewDidClose() {
        controller.selectedTrabajadorProperty().unbind();
    }

    @FXML
    private void editTrabajador(ActionEvent event) {
        TrabajadorView editView = controller.mostrarEditar();

        if (editView == null) return;

        getRoot().setRight(editView.getRoot());
        getCurrentStage().sizeToScene();
        getCurrentStage().centerOnScreen();
    }

    @FXML
    private void salir() {
        getRoot().setRight(null);
        master.removeNode(getRoot());
    }

    @FXML
    void actualizarTabla(ActionEvent event) {
        refreshTable();
    }

//    public void refresh() {
//        tableView.refresh();
//        getRoot().setRight(null);
//        getCurrentStage().sizeToScene();
//        getCurrentStage().centerOnScreen();
//    }

    @Override
    public BorderPane getRoot() {
        return (BorderPane) root;
    }

    public void setController(ListaTrabajadorController controller) {
        this.controller = controller;
    }

    public void setMaster(HomeView master) {
        this.master = master;
    }

    private void refreshTable() {
        SortedList sortedList = controller.sortedList();
        tableView.setItems(sortedList);
        sortedList.comparatorProperty().bind(tableView.comparatorProperty());
    }

}
