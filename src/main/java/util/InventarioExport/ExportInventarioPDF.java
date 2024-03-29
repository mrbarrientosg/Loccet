package util.InventarioExport;

import cell.MaterialCell;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import util.ExportFile.ExportFileStrategy;
import util.PDFBuilder;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExportInventarioPDF implements ExportFileStrategy {

   private PDFBuilder pdfBuilder;

   private final List<MaterialCell> materialCells;

   private final String nombreProyecto;

   private final Font titleFont = FontFactory.getFont(FontFactory.TIMES, 28, Font.BOLD);

   private final Font subTitleFont = FontFactory.getFont(FontFactory.TIMES, 20, Font.ITALIC);

   private final Font headerTableFont = FontFactory.getFont(FontFactory.TIMES, 14, Font.BOLD);

   private final Font cellTableFont = FontFactory.getFont(FontFactory.TIMES, 12, Font.NORMAL);

   public ExportInventarioPDF(final String nombreProyecto, final List<MaterialCell> materialCells) throws IOException, DocumentException {
       this.materialCells = materialCells;
       this.nombreProyecto = nombreProyecto;
       pdfBuilder = PDFBuilder.create("Inventario " + nombreProyecto);
       makePDF();
   }

   private void makePDF() {
       pdfBuilder.start(document -> {
           Paragraph title = createTitle("Inventario Materiales");
           Paragraph subTitle = createSubTitle("Proyecto: " + nombreProyecto);
           title.add(subTitle);
           addEmptyLine(title, 1);
           document.add(title);

           PdfPTable table = new PdfPTable(6);
           table.addCell(createHeader("ID"));
           table.addCell(createHeader("Nombre"));
           table.addCell(createHeader("Descripción"));
           table.addCell(createHeader("Cantidad"));
           table.addCell(createHeader("UDS"));
           table.addCell(createHeader("Precio"));

           table.setHeaderRows(1);
           table.setWidthPercentage(100);
           table.setHorizontalAlignment(Element.ALIGN_CENTER);

           materialCells.forEach(materialCell -> {
                createCell(table, materialCell);
           });

           document.add(table);
       });
   }

    @Override
    public File export() throws IOException {
        return pdfBuilder.buildPDF();
    }

    private Paragraph createTitle(String title) {
        Paragraph titlePara = new Paragraph(title, titleFont);
        titlePara.setAlignment(Element.ALIGN_CENTER);
        return titlePara;
    }

    private Paragraph createSubTitle(String title) {
        Paragraph titlePara = new Paragraph(title, subTitleFont);
        titlePara.setAlignment(Element.ALIGN_CENTER);
        return titlePara;
    }

    private PdfPCell createHeader(String title) {
        PdfPCell columnHeader = new PdfPCell(new Phrase(title, headerTableFont));
        columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
        columnHeader.setVerticalAlignment(Element.ALIGN_CENTER);
        return columnHeader;
    }

    private void createCell(PdfPTable table, MaterialCell materialCell) {
        for (int column = 0; column < table.getNumberOfColumns(); column++) {
            switch (column) {

                case 0:
                    table.addCell(createCell(materialCell.getId()));
                    break;
                case 1:
                    table.addCell(createCell(materialCell.getNombre()));
                    break;
                case 2:
                    table.addCell(createCell(materialCell.getDescripcion()));
                    break;
                case 3:
                    table.addCell(createCell(materialCell.getCantidad()));
                    break;
                case 4:
                    table.addCell(createCell(materialCell.getUds()));
                    break;
                case 5:
                    table.addCell(createCell(materialCell.getPrecio()));
                    break;
            }
        }
    }

    private PdfPCell createCell(String title) {
        PdfPCell cell = new PdfPCell(new Phrase(title, cellTableFont));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        return cell;
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

}
