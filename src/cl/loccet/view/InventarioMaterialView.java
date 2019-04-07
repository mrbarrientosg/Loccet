package cl.loccet.view;

import cl.loccet.base.View;
import cl.loccet.controller.InventarioMaterialController;
import cl.loccet.model.InventarioMaterial;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;


/**
 * Clase que mostrara las vistas.
 *
 * @author  Sebastian Fuenzalida.
 */



public class InventarioMaterialView extends View {

    private InventarioMaterialController controller;

    //Botones.
    @FXML private javafx.scene.control.Button agregarBT;
    @FXML private javafx.scene.control.Button obtenerBT;
    @FXML private javafx.scene.control.Button salirBT;

    //TextField.
    @FXML private javafx.scene.control.TextField idFD;
    @FXML private javafx.scene.control.TextField descripcionFD;
    @FXML private javafx.scene.control.TextField cantidadFD;

    //Tabla inventario.
    @FXML private TableView<InventarioMaterial> inventarioTableView;
    @FXML private javafx.scene.control.TableColumn idMaterialCL;
    @FXML private javafx.scene.control.TableColumn descripcionCL;
    @FXML private javafx.scene.control.TableColumn cantidadCL;

    ObservableList<InventarioMaterial> inventarios;



    public void setController(InventarioMaterialController controller) {
        this.controller = controller;
    }

    @Override
    public void viewDidLoad() {

    }

    @Override
    public void viewDidClose() {

    }
}
