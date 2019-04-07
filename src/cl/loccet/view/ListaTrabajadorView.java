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
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class ListaTrabajadorView extends View {

    private HomeView master;

    private ListaTrabajadorController controller;

    private ObjectProperty<TrabajadorCell> selectedTrabajador = new SimpleObjectProperty<>();

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

        tableView.setItems(controller.trabajadorCells());

        selectedTrabajador.bind(tableView.getSelectionModel().selectedItemProperty());

    }

    @Override
    public void viewDidClose() {

    }

    @FXML
    private void editTrabajador() {
        System.out.println(selectedTrabajador.get());
        master.setRight(controller.mostrarEditar(selectedTrabajador.get()).getRoot());
    }

    public void setController(ListaTrabajadorController controller) {
        this.controller = controller;
    }

    public void setMaster(HomeView master) {
        this.master = master;
    }
}
