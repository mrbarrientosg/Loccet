package cl.loccet.view;

import cl.loccet.base.View;
import cl.loccet.controller.InventarioMaterialController;
import cl.loccet.model.Material;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 * Clase que mostrara las vistas.
 *
 * @author  Sebastian Fuenzalida.
 */



public class InventarioMaterialView extends View {

    private InventarioMaterialController controller;

    //Botones.
    @FXML
    private Button agregarBT;
    @FXML
    private Button obtenerBT;
    @FXML
    private Button salirBT;

    //TextField.
    @FXML
    private TextField idFD;
    @FXML
    private TextField descripcionFD;
    @FXML
    private TextField cantidadFD;

    //Tabla inventario.
    @FXML
    private TableView<Material> tablaInventario;
    @FXML
    private TableColumn<Material,Integer> idMaterialCL;
    @FXML
    private TableColumn<Material,String> descripcionCL;
    @FXML
    private TableColumn<Material,Integer> cantidadCL;


    public void setController(InventarioMaterialController controller) {
        this.controller = controller;
    }

    @Override
    public void viewDidLoad() {
        obtenerBT.setDisable(true);
        inicializarTablaMateriales();
    }
    @Override
    public void viewDidClose() {

    }
    @FXML public void nuevoMaterial(ActionEvent event){
        Material material = new Material(Integer.parseInt(idFD.getText()),descripcionFD.getText(),Integer.parseInt(cantidadFD.getText()));
        controller.agregarMaterial(material);
        tablaInventario.setItems(controller.obtenerDatos());
        clearFields();
    }


    private void clearFields() {
        idFD.setText("");
        descripcionFD.setText("");
        cantidadFD.setText("");

    }


    private void inicializarTablaMateriales() {
        idMaterialCL.setCellValueFactory(new PropertyValueFactory<>("id"));
        cantidadCL.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        descripcionCL.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tablaInventario.setItems(controller.obtenerDatos());
    }





}