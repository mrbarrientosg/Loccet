package view;

import base.Fragment;
import base.Injectable;
import base.View;
import cell.MaterialCell;
import controller.InventarioMaterialController;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.StageStyle;
import model.InventarioMaterial;
import model.Material;
import router.DetalleMaterialRouter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;


/**
 * Clase que mostrara las vistas.
 *
 * @author  Sebastian Fuenzalida.
 */
public final class InventarioMaterialView extends Fragment {

    private InventarioMaterialController controller;

    @FXML
    private TextField searchText;

    //Botones.
    @FXML
    private Button editarBT;
    @FXML
    private Button eliminarBT;
    @FXML
    private Button nuevoMaterialBT;



    //Tabla inventario.
    @FXML
    private TableView<MaterialCell> tablaInventario;
    @FXML
    private TableColumn<MaterialCell,String> idMaterialCL;
    @FXML
    private TableColumn<MaterialCell,String> descripcionCL;
    @FXML
    private TableColumn<MaterialCell,Double> cantidadCL;
    @FXML
    private TableColumn<MaterialCell,String> udsCL;
    @FXML
    private TableColumn<MaterialCell, String> nombreMaterialCL;
    @FXML
    private TableColumn<MaterialCell,Double> retiroCL;
    @FXML
    private TableColumn<MaterialCell,Double> precioCL;

    @Override
    public void viewDidLoad() {
        tablaInventario.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                editarBT.setDisable(false);
                eliminarBT.setDisable(false);
            }else{
                editarBT.setDisable(true);
                eliminarBT.setDisable(true);
            }
        });
   }

    @Override
    public void viewDidShow() {

        inicializarTablaMateriales();
        searchText.setOnKeyReleased(event -> {
            searchText.textProperty().addListener((observable, oldValue, newValue) -> {
                controller.didSearch(newValue);
            });
            refreshTable();
        });
    }

    /**
     * Funcion que mostrara la vista de nuevo material.
     *
     * @author Sebastian Fuenzalida.
     *
     * @param event presionar el boton nuevo material
     */
    @FXML
    public void nuevoMaterial(ActionEvent event){
        NuevoMaterialView view = Injectable.find(NuevoMaterialView.class);
        view.setController(controller);
        view.modal().withBlock(true).show();
        refreshTable();
    }


    /**
     * Funcion que retorna el item seleccionado de la tabla inventario.
     *
     * @author Sebastian Fuenzalida.
     *
     * @return un material en caso de haber sido seleccionado.
     */
    private MaterialCell seleccion(){
        int seleccion = tablaInventario.getSelectionModel().getSelectedIndex();
        if(seleccion>=0){
            MaterialCell material = tablaInventario.getItems().get(seleccion);
            return material;
        }
        return null;
    }


    @FXML
    public void detalleMaterial(ActionEvent event){
        MaterialCell materialCell = seleccion();
        if(materialCell!=null) {
            Material material = controller.getMaterial(materialCell.getId());
            DetalleMaterialView view = DetalleMaterialRouter.create(material);
            view.modal().withStyle(StageStyle.TRANSPARENT).show();
            controller.cargarDatos();
            refreshTable();
        }
        else{
            controller.showWarning("Seleccionar material", "Por favor seleccione material a eliminar").showAndWait();;
        }
    }

    /**
     * Funcion que conectara la vista con el controlador para eliminar un item seleccionado.
     *
     * @author Sebastian Fuenzalida.
     *
     * @param event presiona el boton eliminar
     */
    @FXML
    public void eliminar(ActionEvent event){
        MaterialCell material = seleccion();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alerta");
            alert.setHeaderText("Esta accion borrara el material");
            alert.setContentText("¿Esta seguro de que desea continuar?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                MaterialCell aux = controller.eliminarMaterial(material.getId());
                controller.cargarDatos();
            }
    }

    @FXML
    private void exportarInventario(ActionEvent event) {
        controller.exportarInventario();
    }


    /**
     * Funcion privada que inicializa la tabla de materiales.
     *
     * @author Sebastian Fuenzalida.
     */
    private void inicializarTablaMateriales() {
        retiroCL.setCellValueFactory(new PropertyValueFactory<>("retiro"));
        udsCL.setCellValueFactory(new PropertyValueFactory<>("uds"));
        nombreMaterialCL.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        idMaterialCL.setCellValueFactory(new PropertyValueFactory<>("id"));
        cantidadCL.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        descripcionCL.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        precioCL.setCellValueFactory(new PropertyValueFactory<>("precio"));
        refreshTable();
    }


    /**
     * Funcion que recarga la tabla.
     */
    private void refreshTable() {
        SortedList sortedList = controller.sortedList();
        tablaInventario.setItems(sortedList);
        sortedList.comparatorProperty().bind(tablaInventario.comparatorProperty());
    }

    /**
     * @param controller inventario Material
     */
    public void setController(InventarioMaterialController controller) {
        this.controller = controller;
    }
}

