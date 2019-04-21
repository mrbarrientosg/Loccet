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

    public InventarioMaterialController(InventarioMaterialView view, InventarioMaterial model, InventarioMaterialRouter router) {
        this.view = view;
        this.model = model;
        this.router = router;
        cargarDatos();
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

    public FilteredList<MaterialCell> getFilteredMateriales() {
        return filteredMateriales;
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
        filteredMateriales.setPredicate(materialCell -> materialCell.getNombre().toLowerCase().startsWith(query.toLowerCase()));
    }

    public Alert showWarning(String header, String message) {
        return router.showWarning(header, message);
    }

    public void exportarInventario() {
        PDFBuilder pdfBuilder = PDFBuilder.create("Test");

        pdfBuilder.start(document -> {
            //anchor.setName("Table export to PDF (Exportamos la tabla a PDF)");
            Anchor anchor = new Anchor(proyecto.getNombreProyecto(), FontFactory.getFont(FontFactory.TIMES, 30));
            Paragraph title = new Paragraph(anchor);
            title.setAlignment(Element.ALIGN_CENTER);
            addEmptyLine(title, 1);

            document.add(title);

            // We create the table (Creamos la tabla).
            PdfPTable table = new PdfPTable(9);
            // Now we fill the PDF table
            // Ahora llenamos la tabla del PDF

            table.addCell(createHeader("Fecha Ingreso"));
            table.addCell(createHeader("ID"));
            table.addCell(createHeader("Nombre"));
            table.addCell(createHeader("Descripción"));
            table.addCell(createHeader("Cantidad"));
            table.addCell(createHeader("UDS"));
            table.addCell(createHeader("Retiro"));
            table.addCell(createHeader("Fecha Retiro"));
            table.addCell(createHeader("Precio"));

            table.setHeaderRows(1);
            // Fill table rows (rellenamos las filas de la tabla).

            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.setWidths(new float[]{30, 20, 30, 40, 30, 30, 30, 30, 30});
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

            Font fontCell = FontFactory.getFont(FontFactory.TIMES, 12);

            listMateriales.forEach(materialCell -> {
                for (int column = 0; column < 9; column++) {
                    switch (column) {
                        case 0:
                            table.addCell(new Phrase(format.format(materialCell.getFechaIngreso()), fontCell));
                            break;
                        case 1:
                            table.addCell(new Phrase(materialCell.getId(), fontCell));
                            break;
                        case 2:
                            table.addCell(new Phrase(materialCell.getNombre(), fontCell));
                            break;
                        case 3:
                            table.addCell(new Phrase(materialCell.getDescripcion(), fontCell));
                            break;
                        case 4:
                            table.addCell(new Phrase(String.valueOf(materialCell.getCantidad()), fontCell));
                            break;
                        case 5:
                            table.addCell(new Phrase(materialCell.getUds(), fontCell));
                            break;
                        case 6:
                            table.addCell(new Phrase(String.valueOf(materialCell.getRetiro()), fontCell));
                            break;
                        case 7:
                            if (materialCell.getFechaRetiro() == null) table.addCell("-");
                            else table.addCell(new Phrase(format.format(materialCell.getFechaRetiro()), fontCell));
                            break;
                        case 8:
                            table.addCell(new Phrase(String.valueOf(materialCell.getPrecio()), fontCell));
                            break;
                    }
                }
            });

            // We add the table (Añadimos la tabla)
            document.add(table);
        });

        guardarPDF(pdfBuilder.buildPDF());

    }

    private void guardarPDF(File file) {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter for text files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        File dest = fileChooser.showSaveDialog(getPrimaryStage());
        if (dest != null) {
            try {
                Files.copy(file.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                // handle exception...
                ex.printStackTrace();
            }
        }
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

    private PdfPCell createHeader(String title) {
        Font fontCell = FontFactory.getFont(FontFactory.TIMES, 14);
        PdfPCell columnHeader = new PdfPCell(new Phrase(title, fontCell));
        columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
        columnHeader.setVerticalAlignment(Element.ALIGN_CENTER);
        return columnHeader;
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }
}
