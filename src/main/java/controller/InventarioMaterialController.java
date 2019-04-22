package controller;

import base.Controller;
import cell.MaterialCell;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.sun.javafx.collections.SortableList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import model.InventarioMaterial;
import model.Material;
import model.Proyecto;
import router.InventarioMaterialRouter;
import util.ExportFile;
import util.InventarioExport.ExportInventarioPDF;
import util.InventarioExport.ExportInventarioXLSX;
import util.PDFBuilder;
import view.InventarioMaterialView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ListIterator;
import java.util.stream.Collectors;

/**
 *Clase manejadora de las funciones de la vista inventario.
 *
 * @author Sebastian Fuenzalida.
 */
public class InventarioMaterialController extends Controller {

    private InventarioMaterialView view;

    private InventarioMaterialRouter router;

    private InventarioMaterial model;

    private Proyecto proyecto;

    private FilteredList<MaterialCell> filteredMateriales;

    private ObservableList<MaterialCell> listMateriales;

    private ExportFile exportFile;

    public InventarioMaterialController(InventarioMaterialView view, InventarioMaterial model, InventarioMaterialRouter router) {
        this.view = view;
        this.model = model;
        this.router = router;
        cargarDatos();
        exportFile = new ExportFile();
    }

    /**
     * Se obtienen los datos cargados del modelo.
     *
     * @author Sebastian Fuenzalida.
     */
    private void cargarDatos() {
        listMateriales = FXCollections.observableList(model.obtenerMateriales().stream().map(MaterialCell::new).collect(Collectors.toList()));
        filteredMateriales = new FilteredList<>(listMateriales, e -> true);
    }

    /**
     * Se retornan los datos obtenidos previamente.
     *
     * @author Sebastian Fuenzalida.
     *
     * @return
     */
    public SortedList<MaterialCell> sortedList() {
        return new SortedList<>(filteredMateriales);
    }

    public void nuevoMaterial(Material material){
        model.nuevoItem(material);
        listMateriales.add(new MaterialCell(material));
    }

    public void agregarMaterial(String idMaterial, double cantidad){
        changeMaterial(model.agregarMaterial(idMaterial, cantidad));
    }

    public void retirarMaterial(String idMaterial, double cantidad){
        changeMaterial(model.retirarMaterial(idMaterial, cantidad));
    }

    public void modificarNombre(String idMaterial, String nombre){
        changeMaterial(model.modificarNombre(idMaterial, nombre));
    }

    public void modificarDescripcion(String idMaterial, String descripcion){
        changeMaterial(model.modificarDescripcion(idMaterial, descripcion));
    }

    public void eliminarMaterial(String idMaterial){
        Material eliminado = model.eliminarItem(idMaterial);
        listMateriales.removeIf(materialCell -> materialCell.getId().equals(eliminado.getId()));
    }

    public void didSearch(String query) {
        filteredMateriales.setPredicate(materialCell ->
                materialCell.getNombre().toLowerCase().contains(query.toLowerCase()) ||
                        materialCell.getId().toLowerCase().contains(query.toLowerCase()) ||
                        materialCell.getDescripcion().toLowerCase().contains(query.toLowerCase()) ||
                        materialCell.getUds().toLowerCase().contains(query.toLowerCase())
        );
    }

    public Alert showWarning(String header, String message) {
        return router.showWarning(header, message);
    }

    public void exportarInventario() {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter for text files
        FileChooser.ExtensionFilter pdfFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
        FileChooser.ExtensionFilter xlsxFilter = new FileChooser.ExtensionFilter("Excel files (*.xlsx)", "*.xlsx");

        fileChooser.getExtensionFilters().addAll(pdfFilter, xlsxFilter);

        //Show save file dialog
        File dest = fileChooser.showSaveDialog(getPrimaryStage());

        if (dest != null) {
            try {
                guardarArchivoInventario(fileChooser.selectedExtensionFilterProperty().get().getExtensions().get(0), dest);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void guardarArchivoInventario(String extension, File dest) throws IOException {
        if (extension.equals("*.pdf")) {
            exportFile.changeStrategy(new ExportInventarioPDF(proyecto.getNombreProyecto(), listMateriales));
        } else {
            exportFile.changeStrategy(new ExportInventarioXLSX(proyecto.getNombreProyecto(), listMateriales));
        }

        File file = exportFile.export();

        if (file == null) {
            router.showWarning("Error", "La exportación no se pudo guardar");
            return;
        }

        Files.copy(file.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    private void changeMaterial(Material newValue) {
        ListIterator<MaterialCell> iterator = listMateriales.listIterator();

        while (iterator.hasNext()) {
            if (iterator.next().getId().equals(newValue.getId())) {
                iterator.set(new MaterialCell(newValue));
                break;
            }
        }
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }
}
