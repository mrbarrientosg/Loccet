package cl.loccet.view;

import cl.loccet.base.View;
import cl.loccet.cell.TrabajadorCell;
import cl.loccet.model.Especialidades;
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
        specialityColumn.setCellValueFactory(new PropertyValueFactory<>("nombreEspecialidad"));

        tableView.getItems()
                .add(new TrabajadorCell(new Trabajador.Builder()
                .rut("19")
                .nombre("Matias")
                .especialidad(Especialidades.getInstance().get("Pintor"))
                .build()));
    }

    @Override
    public void viewDidClose() {

    }

    @FXML
    private void editTrabajador() {

    }

}
