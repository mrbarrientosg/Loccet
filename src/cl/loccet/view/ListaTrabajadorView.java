package cl.loccet.view;

import cl.loccet.base.View;
import cl.loccet.model.Trabajador;
import cl.loccet.router.AgregarTrabajadorRouter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class ListaTrabajadorView extends View {

    @FXML
    private TableView<Trabajador> tableView;

    @FXML
    private TableColumn<Trabajador, String> rutColumn;

    @FXML
    private TableColumn<Trabajador, String> nameColumn;

    @FXML
    private TableColumn<Trabajador, String> lastNameColumn;

    @FXML
    private TableColumn<Trabajador, String> proyectColumn;

    @FXML
    private TableColumn<Trabajador, String> specialityColumn;

    @Override
    public void viewDidLoad() {

        rutColumn.setCellValueFactory(new PropertyValueFactory<>("rut"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        tableView.getItems().add(new Trabajador.Builder().rut("19").nombre("Matias").build());
    }

    @Override
    public void viewDidClose() {

    }

    @FXML
    private void editTrabajador() {

    }

}
