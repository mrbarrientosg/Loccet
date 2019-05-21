package view;

import base.View;
import cell.TrabajadorCell;
import controller.RRHHController;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import router.RRHHRouter;

public class RRHHView extends View {

    private RRHHController controller;

    private RRHHRouter router;

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

    private SortedList<TrabajadorCell> trabajadorCells;

    @Override
    public void viewDidLoad() {
        rutColumn.setCellValueFactory(new PropertyValueFactory<>("rut"));
        tableView.setItems(trabajadorCells);
    }

    @Override
    public void viewDidShow() {
        trabajadorCells = controller.obtenerTrabajadores();
    }

    public void setController(RRHHController controller) {
        this.controller = controller;
    }

    public void setRouter(RRHHRouter router) {
        this.router = router;
    }
}
