package cl.loccet.view;

import cl.loccet.base.View;
import cl.loccet.cell.TrabajadorCell;
import cl.loccet.controller.ListaTrabajadorController;
import cl.loccet.model.Trabajador;
import cl.loccet.router.TrabajadorRouter;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class ListaTrabajadorView extends View {

    private HomeView master;

    private ListaTrabajadorController controller;

    @FXML
    private TableView<TrabajadorCell> tableView;

    @FXML
    private TableColumn<TrabajadorCell, String> rutColumn;

    @FXML
    private TableColumn<TrabajadorCell, String> nameColumn;

    @FXML
    private TableColumn<TrabajadorCell, String> lastNameColumn;

    @FXML
    private TableColumn<TrabajadorCell, String> proyectColumn;

    @FXML
    private TableColumn<TrabajadorCell, String> specialityColumn;

    @Override
    public void viewDidLoad() {

        rutColumn.setCellValueFactory(new PropertyValueFactory<>("rut"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("apellido"));

        tableView.setItems(controller.getTrabajadorCells());

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
        tableView.refresh();
    }

    public void refresh() {
        tableView.refresh();
        getRoot().setRight(null);
        getCurrentStage().sizeToScene();
        getCurrentStage().centerOnScreen();
    }

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

}
