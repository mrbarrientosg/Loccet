package cl.loccet.view;

import cl.loccet.base.Injectable;
import cl.loccet.base.View;
import cl.loccet.controller.InventarioMaterialController;
import cl.loccet.model.Material;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;


/**
 * Clase que mostrara las vistas.
 *
 * @author  Sebastian Fuenzalida.
 */



public class InventarioMaterialView extends View {

    private InventarioMaterialController controller;

    //Botones.
    @FXML
    private Button nuevoMaterialBT;
    @FXML
    private Button salirBT;



    //Tabla inventario.
    @FXML
    private TableView<Material> tablaInventario;
    @FXML
    private TableColumn<Material,String> idMaterialCL;
    @FXML
    private TableColumn<Material,String> descripcionCL;
    @FXML
    private TableColumn<Material,Integer> cantidadCL;
    @FXML
    private TableColumn<Material, LocalDate> fechaIngresoCL;
    @FXML
    private TableColumn<Material, LocalDate> fechaRetiroCL;
    @FXML
    private TableColumn<Material,String> udsCL;
    @FXML
    private TableColumn<Material, String> nombreMaterialCL;
    @FXML
    private TableColumn<Material,Integer> retiroCL;

    public InventarioMaterialView() {
    }

    public void setController(InventarioMaterialController controller) {
        this.controller = controller;
    }

    @Override
    public void viewDidLoad() {
        inicializarTablaMateriales();
    }
    @Override
    public void viewDidClose() {

    }

    @FXML public void nuevoMaterial(ActionEvent event){
        Injectable.find(NuevoMaterialView.class).window().show();
    }





    private void inicializarTablaMateriales() {

        retiroCL.setCellValueFactory(new PropertyValueFactory<>("retiro"));
        udsCL.setCellValueFactory(new PropertyValueFactory<>("uds"));
        fechaRetiroCL.setCellValueFactory(new PropertyValueFactory<>("fechaRetiro"));
        fechaIngresoCL.setCellValueFactory(new PropertyValueFactory<>("fechaIngreso"));
        nombreMaterialCL.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        idMaterialCL.setCellValueFactory(new PropertyValueFactory<>("id"));
        cantidadCL.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        descripcionCL.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tablaInventario.setItems(controller.obtenerDatos());
        tablaInventario.setEditable(true);
    }



}