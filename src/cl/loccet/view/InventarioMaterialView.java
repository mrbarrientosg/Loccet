package cl.loccet.view;

import cl.loccet.base.View;
import cl.loccet.controller.InventarioMaterialController;
import cl.loccet.model.Material;

import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;


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
    @FXML
    private TextField obtenerFD;

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

    @FXML public void obtenerMaterial(ActionEvent event){
        int posicion = tablaInventario.getSelectionModel().getSelectedIndex();
        if (posicion >= 0){
            Material material = tablaInventario.getItems().get(posicion);
            controller.retiraMaterial(material);

        }

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
        tablaInventario.setEditable(true);
    }






}