package cl.loccet.view;

import cl.loccet.base.View;
import cl.loccet.controller.InventarioController;
import cl.loccet.model.Inventario;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javax.swing.table.TableColumn;
import java.awt.*;


public class InventarioView extends View {

    private InventarioController controller;

    //Botones.
    @FXML private Button agregarBT;
    @FXML private Button obtenerBT;
    @FXML private Button salirBT;

    //TextField.
    @FXML private TextField idFD;
    @FXML private TextField descripcionFD;
    @FXML private TextField cantidadFD;

    //Tabla inventario.
        @FXML private TableView<Inventario> inventarioTableView;
    @FXML private TableColumn idMaterialCL;
    @FXML private TableColumn descripcionCL;
    @FXML private TableColumn cantidadCL;

    ObservableList<Inventario> inventarios;





    public void setController(InventarioController controller) {
        this.controller = controller;
    }

    @Override
    public void viewDidLoad() {

    }

    @Override
    public void viewDidClose() {

    }
}
