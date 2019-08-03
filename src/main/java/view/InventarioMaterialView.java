package view;

import base.Fragment;
import base.Injectable;
import base.View;
import cell.MaterialCell;
import controller.InventarioMaterialController;
import delegate.EditMaterialDelegate;
import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import io.reactivex.schedulers.Schedulers;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.StageStyle;
import model.Material;
import model.Proyecto;
import util.AsyncTask;
import java.io.File;
import java.io.IOException;
import java.util.ListIterator;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * Clase que mostrara las vistas.
 *
 * @author  Sebastian Fuenzalida.
 */
public final class InventarioMaterialView extends View implements EditMaterialDelegate {

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
    private TableColumn<MaterialCell,Double> precioCL;

    @Override
    public void viewDidLoad() {
         controller = Injectable.find(InventarioMaterialController.class);
         controller.setView(this);

        tablaInventario.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                editarBT.setDisable(false);
                eliminarBT.setDisable(false);
            }else{
                editarBT.setDisable(true);
                eliminarBT.setDisable(true);
            }
        });

        udsCL.setCellValueFactory(new PropertyValueFactory<>("uds"));
        nombreMaterialCL.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        idMaterialCL.setCellValueFactory(new PropertyValueFactory<>("id"));
        cantidadCL.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        descripcionCL.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        precioCL.setCellValueFactory(new PropertyValueFactory<>("precio"));
   }

    @Override
    public void viewDidShow() {
        Observable<String> textInputs = JavaFxObservable.valuesOf(searchText.textProperty());

        textInputs
                .debounce(300, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .map(controller::searchMaterials)
                .subscribeOn(Schedulers.io())
                .observeOn(JavaFxScheduler.platform())
                .subscribe(tablaInventario::setItems);
    }

    @Override
    public void viewDidClose() {
        tablaInventario.getItems().clear();
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
        Injectable.find(CrearMaterialView.class).display();
    }

    public void didAddMaterial(MaterialCell cell) {
        tablaInventario.getItems().add(cell);
        searchText.setText("");
    }


    public void removeMaterial(MaterialCell cell) {
        tablaInventario.getItems().removeIf((materialCell) -> materialCell.getId().equals(cell.getId()));
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
        if(seleccion >= 0){
            return tablaInventario.getItems().get(seleccion);
        }
        return null;
    }


    @FXML
    public void detalleMaterial(ActionEvent event){
        MaterialCell materialCell = seleccion();
        if (materialCell != null) {
            Material material = controller.getMaterial(materialCell.getId());
            Injectable.find(DetalleMaterialView.class).display(controller.getIdProyecto(), material, this);
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
                controller.guardarArchivoInventario(fileChooser.selectedExtensionFilterProperty().get().getExtensions().get(0),
                        dest, tablaInventario.getItems());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
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

    public InventarioMaterialView display(String idProyecto) {
        controller.setIdProyecto(idProyecto);
        return this;
    }
}

