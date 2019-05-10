package view;

import base.View;
import cell.TrabajadorCell;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class RRHHView extends View {

    @FXML
    private TextField searchField;

    @FXML
    private ComboBox<String> proyectList;

    @FXML
    private Button createTrabajador;

    @FXML
    private TableView<TrabajadorCell> tableView;

    @FXML
    private TableColumn<TrabajadorCell, String> rutColumn;

    @Override
    public void viewDidLoad() {
        rutColumn.setCellValueFactory(new PropertyValueFactory<>("rut"));
    }

    @Override
    public void viewDidShow() {

    }
}
