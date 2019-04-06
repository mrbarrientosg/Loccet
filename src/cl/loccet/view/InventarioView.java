package cl.loccet.view;

import cl.loccet.model.Inventario;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Observable;

public class InventarioView {

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


}
