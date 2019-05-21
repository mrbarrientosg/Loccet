package view;

import base.View;
import cell.TrabajadorCell;
import controller.RRHHController;
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

    @Override
    public void viewDidLoad() {
        rutColumn.setCellValueFactory(new PropertyValueFactory<>("rut"));
    }

    @Override
    public void viewDidShow() {

    }

    public void setController(RRHHController controller) {
        this.controller = controller;
    }

    public void setRouter(RRHHRouter router) {
        this.router = router;
    }
}
