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
    private Material verificarNuevoMaterial(){

        Alert alert = new Alert(Alert.AlertType.WARNING);
        Material material = new Material();
        String lector = idFD.getText();
        if (lector.length()!= 0) material.setId(Integer.parseInt(lector));
        else{
            alert.setTitle("Error");
            alert.setHeaderText("No se rellenaron todos los campos");
            alert.setContentText("Por favor verificar que todos los campos esten llenos!!!");
            alert.showAndWait();
            return null;
        }
        lector = descripcionFD.getText();
        if (lector.length()!= 0) material.setDescripcion(lector);
        else{
            alert.setTitle("Error");
            alert.setHeaderText("No se rellenaron todos los campos");
            alert.setContentText("Por favor verificar que todos los campos esten llenos!!!");
            alert.showAndWait();
            return null;
        }
        lector = cantidadFD.getText();
        if (lector.length()!=0) material.setCantidad(Integer.parseInt(lector));
        else{
            alert.setTitle("Error");
            alert.setHeaderText("No se rellenaron todos los campos");
            alert.setContentText("Por favor verificar que todos los campos esten llenos!!!");
            alert.showAndWait();
            return null;
        }
        return material;
    }

    @FXML public void nuevoMaterial(ActionEvent event){
        Material material = verificarNuevoMaterial();
        if (material != null) {
            controller.agregarMaterial(material);
            tablaInventario.setItems(controller.obtenerDatos());
            clearFields();
            tablaInventario.refresh();
        }
    }

    @FXML public void obtenerMaterial(ActionEvent event){
        int posicion = tablaInventario.getSelectionModel().getSelectedIndex();
        if (posicion >= 0){
            int sacar = Integer.parseInt(obtenerFD.getText());
            Material material = tablaInventario.getItems().get(posicion);
            controller.retiraMaterial(material,sacar);
            tablaInventario.refresh();

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