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
import javafx.scene.layout.BorderPane;

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
    private TableView<MaterialCell> tablaInventario;
    @FXML
    private TableColumn<MaterialCell,String> idMaterialCL;
    @FXML
    private TableColumn<MaterialCell,String> descripcionCL;
    @FXML
    private TableColumn<MaterialCell,Double> cantidadCL;
    @FXML
    private TableColumn<MaterialCell, Date> fechaIngresoCL;
    @FXML
    private TableColumn<MaterialCell, Date> fechaRetiroCL;
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
        inicializarTablaMateriales();
        searchText.setOnKeyReleased(event -> {
            searchText.textProperty().addListener((observable, oldValue, newValue) -> {
                controller.didSearch(newValue);
            });
            refreshTable();
        });
   }

    @Override
    public void viewDidClose() {

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
    public MaterialCell seleccion(){
        int seleccion = tablaInventario.getSelectionModel().getSelectedIndex();
        if(seleccion>=0){
            MaterialCell material = tablaInventario.getItems().get(seleccion);
            return material;
        }
        return null;
    }

    /**
     * Funcion que mostrara la vista modificar material.
     *
     * @author Sebastian Fuenzalida.
     *
     * @param event presiona el boton modificar
     */
    @FXML
    public void modificarMaterial(ActionEvent event){
        MaterialCell material = seleccion();
        if(material!=null) {
            ModificarMaterialView view = Injectable.find(ModificarMaterialView.class);
            view.setIdMaterial(material.getId());
            view.setController(controller);
            view.modal().withBlock(true).show();
            refreshTable();
        }
        else{
            controller.showWarning("Seleccionar material", "Por favor seleccione material a eliminar").showAndWait();;
        }

    }

    /**
     * Funcion que mostrara la vista agregar material.
     *
     * @author Sebastian Fuenzalida.
     *
     * @param event presiona el boton agregar
     */
    @FXML
    public void agregarMaterial(ActionEvent event){
        MaterialCell material = seleccion();
        if(material!=null) {
            AgregarMaterialView view = Injectable.find(AgregarMaterialView.class);
            view.setIdMaterial(material.getId());
            view.setController(controller);
            view.modal().withBlock(true).show();
            tablaInventario.refresh();
        }
        else{
            controller.showWarning("Seleccionar material", "Por favor seleccione material a eliminar").showAndWait();;
        }
    }

    /**
     * Funcion que mostrara la vista retirar material.
     *
     * @author Sebastian Fuenzalida.
     *
     * @param event presiona el boton retirar
     */
    @FXML
    public void retirarMaterial(ActionEvent event){
        MaterialCell material = seleccion();
        if(material!=null) {
            RetirarMaterialView view = Injectable.find(RetirarMaterialView.class);
            view.setMaterial(material);
            view.setController(controller);
            view.modal().withBlock(true).show();
            tablaInventario.refresh();
        }
        else{
            controller.showWarning("Seleccionar material", "Por favor seleccione material a eliminar").showAndWait();;
        }
    }

    @FXML
    public void salir(ActionEvent event){
        ((BorderPane) getRoot().getParent()).getChildren().remove(getRoot());
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
            controller.showWarning("Seleccionar material", "Por favor seleccione material a eliminar").showAndWait();;
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
        fechaRetiroCL.setCellValueFactory(new PropertyValueFactory<>("fechaRetiro"));
        fechaRetiroCL.setCellFactory(column -> {
            TableCell<MaterialCell, Date> cell = new TableCell<MaterialCell, Date>() {
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
            TableCell<MaterialCell, Date> cell = new TableCell<MaterialCell, Date>() {
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