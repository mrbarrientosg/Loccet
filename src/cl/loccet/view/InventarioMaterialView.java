package cl.loccet.view;

import cl.loccet.base.Injectable;
import cl.loccet.base.View;
import cl.loccet.controller.InventarioMaterialController;
import cl.loccet.model.InventarioMaterial;
import cl.loccet.model.Material;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.util.StringConverter;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;


/**
 * Clase que mostrara las vistas.
 *
 * @author  Sebastian Fuenzalida.
 */



public class InventarioMaterialView extends View {

    private InventarioMaterialController controller;

    //Botones.
    @FXML
    private Button modificarMaterialBT;
    @FXML
    private Button retirarBT;
    @FXML
    private Button eleminarBT;
    @FXML
    private Button agregarBT;
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
    private TableColumn<Material,Double> cantidadCL;
    @FXML
    private TableColumn<Material, Date> fechaIngresoCL;
    @FXML
    private TableColumn<Material, Date> fechaRetiroCL;
    @FXML
    private TableColumn<Material,String> udsCL;
    @FXML
    private TableColumn<Material, String> nombreMaterialCL;
    @FXML
    private TableColumn<Material,Double> retiroCL;

    ObservableList<InventarioMaterial> inventarios;


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
        NuevoMaterialView view = Injectable.find(NuevoMaterialView.class);
        view.setController(controller);
        view.modal().withBlock(true).show();
        tablaInventario.setItems(controller.obtenerDatos());
        tablaInventario.refresh();
    }
    public Material seleccion(){
        int seleccion = tablaInventario.getSelectionModel().getSelectedIndex();
        if(seleccion>=0){
            Material material =tablaInventario.getItems().get(seleccion);
            return material;
        }
        return null;
    }

    @FXML public void modificarMaterial(ActionEvent event){
        Material material = seleccion();
        if(material!=null) {
            ModificarMaterialView view = Injectable.find(ModificarMaterialView.class);
            view.setIdMaterial(material.getId());
            view.setController(controller);
            view.modal().withBlock(true).show();
            tablaInventario.setItems(controller.obtenerDatos());
            tablaInventario.refresh();

        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Seliccionar material");
            alert.setContentText("Por favor seleccione material a modificar");
            alert.showAndWait();
        }

    }

    @FXML public void agregarMaterial(ActionEvent event){
        Material material = seleccion();
        if(material!=null) {
            AgregarMaterialView view = Injectable.find(AgregarMaterialView.class);
            view.setIdMaterial(material.getId());
            view.setController(controller);
            view.modal().withBlock(true).show();
            tablaInventario.refresh();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Seliccionar material");
            alert.setContentText("Por favor seleccione material a agregar");
            alert.showAndWait();
        }
    }
    @FXML public void retirarMaterial(ActionEvent event){
        Material material = seleccion();
        if(material!=null) {
            RetirarMaterialView view = Injectable.find(RetirarMaterialView.class);
            view.setMaterial(material);
            view.setController(controller);
            view.modal().withBlock(true).show();
            tablaInventario.refresh();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Seliccionar material");
            alert.setContentText("Por favor seleccione material a retirar");
            alert.showAndWait();
        }
    }

    @FXML
    public void salir(ActionEvent event){
        ((BorderPane) getRoot().getParent()).getChildren().remove(getRoot());
    }

    @FXML
    public void eliminar(ActionEvent event){
        Material material = seleccion();
        if (material != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alerta");
            alert.setHeaderText("Esta accion borrara el material");
            alert.setContentText("¿Esta seguro de que desea continuar?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                controller.eliminarMaterial(material.getId());
                tablaInventario.refresh();
            }

        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Seliccionar material");
            alert.setContentText("Por favor seleccione material a eliminar");
            alert.showAndWait();
        }
    }



    private void inicializarTablaMateriales() {


        retiroCL.setCellValueFactory(new PropertyValueFactory<>("retiro"));
        udsCL.setCellValueFactory(new PropertyValueFactory<>("uds"));
        fechaRetiroCL.setCellValueFactory(new PropertyValueFactory<>("fechaRetiro"));
        fechaRetiroCL.setCellFactory(column -> {
            TableCell<Material, Date> cell = new TableCell<Material, Date>() {
                private SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if(empty) {
                        setText(null);
                    }
                    else {
                        if (item != null) setText(format.format(item));
                        else setText(null);
                    }
                }
            };

            return cell;
        });
        fechaIngresoCL.setCellValueFactory(new PropertyValueFactory<>("fechaIngreso"));
        fechaIngresoCL.setCellFactory(column -> {
            TableCell<Material, Date> cell = new TableCell<Material, Date>() {
                private SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if(empty) {
                        setText(null);
                    }
                    else {
                        setText(format.format(item));
                    }
                }
            };

            return cell;
        });
        nombreMaterialCL.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        idMaterialCL.setCellValueFactory(new PropertyValueFactory<>("id"));
        cantidadCL.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        descripcionCL.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tablaInventario.setItems(controller.obtenerDatos());
        tablaInventario.setEditable(true);

    }





}