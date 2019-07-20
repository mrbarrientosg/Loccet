package view;

import base.Fragment;
import base.Injectable;
import cell.MaterialCell;
import cell.TrabajadorCell;
import controller.InventarioMaterialController;
import delegate.EditMaterialDelegate;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.StageStyle;
import model.Material;
import router.DetalleMaterialRouter;
import util.AsyncTask;

import java.io.File;
import java.io.IOException;
import java.util.ListIterator;
import java.util.Optional;


/**
 * Clase que mostrara las vistas.
 *
 * @author  Sebastian Fuenzalida.
 */
public final class InventarioMaterialView extends Fragment implements EditMaterialDelegate {

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

    private FilteredList<MaterialCell> filteredMateriales;

    private ObservableList<MaterialCell> listMateriales;

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

        controller.cargarDatos(list -> {
            listMateriales = list;
            tablaInventario.setItems(list);
            filteredMateriales = new FilteredList<>(list, e -> true);
        });

        searchText.setOnKeyReleased(event -> {
            searchText.textProperty().addListener((observable, oldValue, newValue) -> {
                didSearch(newValue);
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
        view.modal().withOwner(null).withStyle(StageStyle.TRANSPARENT)
                .show().getScene().setFill(Color.TRANSPARENT);;
    }

    public void didAddMaterial(MaterialCell cell) {
        listMateriales.add(cell);
        tablaInventario.refresh();
    }


    public void removeMaterial(MaterialCell cell) {
        listMateriales.removeIf((materialCell) -> materialCell.getId().equals(cell.getId()));
        tablaInventario.refresh();
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
            DetalleMaterialView view = DetalleMaterialRouter.create(material, this);
            view.modal().withOwner(null).withStyle(StageStyle.TRANSPARENT)
                    .show().getScene().setFill(Color.TRANSPARENT);
        }
        else{
           // controller.showWarning("Seleccionar material", "Por favor seleccione material a eliminar").showAndWait();;
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
                controller.eliminarMaterial(material.getId());
            }
    }

    @FXML
    private void exportarInventario(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter for text files
        FileChooser.ExtensionFilter pdfFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
        FileChooser.ExtensionFilter xlsxFilter = new FileChooser.ExtensionFilter("Excel files (*.xlsx)", "*.xlsx");

        fileChooser.getExtensionFilters().addAll(pdfFilter, xlsxFilter);

        //Show save file dialog
        File dest = fileChooser.showSaveDialog(getPrimaryStage());

        if (dest != null) {
            try {
                controller.guardarArchivoInventario(fileChooser.selectedExtensionFilterProperty().get().getExtensions().get(0), dest, listMateriales);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
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
    }


    /**
     * Funcion que recarga la tabla.
     */
    private void refreshTable() {
        filteredMateriales = new FilteredList<>(listMateriales, e -> true);
        SortedList<MaterialCell> sortedList = new SortedList<>(filteredMateriales);
        tablaInventario.setItems(sortedList);
        sortedList.comparatorProperty().bind(tablaInventario.comparatorProperty());
    }

    /**
     * Filtra la busqueda de la vista
     * @param query String que contiene la busqueda de la vista
     */
    private void didSearch(String query) {
        filteredMateriales.setPredicate(materialCell ->
                materialCell.getNombre().toLowerCase().contains(query.toLowerCase()) ||
                        materialCell.getId().toLowerCase().contains(query.toLowerCase()) ||
                        materialCell.getDescripcion().toLowerCase().contains(query.toLowerCase()) ||
                        materialCell.getUds().toLowerCase().contains(query.toLowerCase())
        );
    }

    /**
     * @param controller inventario Material
     */
    public void setController(InventarioMaterialController controller) {
        this.controller = controller;
    }

    @Override
    public void didEditMaterial(Material material) {
        AsyncTask.supplyAsync(() -> {
            ListIterator<MaterialCell> iterator = tablaInventario.getItems().listIterator();

            while (iterator.hasNext()) {
                MaterialCell cell = iterator.next();
                if (cell.getId().equals(material.getId())) {
                    Platform.runLater(() -> {
                        iterator.set(new MaterialCell(material));
                    });
                    return true;
                }
            }
            return false;
        });
    }
}

