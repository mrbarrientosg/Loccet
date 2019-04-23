package util.InventarioExport;

import cell.MaterialCell;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import util.ExportFile.ExportFileStrategy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExportInventarioXLSX implements ExportFileStrategy {

    private Workbook workbook;

    private Sheet sheet;

    private final List<MaterialCell> materialCells;

    private final String nombreProyecto;

    private CellStyle headerCellStyle;

    public ExportInventarioXLSX(final String nombreProyecto, final List<MaterialCell> materialCells) {
        this.nombreProyecto = nombreProyecto;
        this.materialCells = materialCells;
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Inventario");
        makeXLSX();
    }

    private void makeXLSX() {
        CreationHelper createHelper = workbook.getCreationHelper();
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);

        headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        Row headerRow = sheet.createRow(0);

        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yyyy"));

        createHeaderCell(headerRow,"Fecha Ingreso", 0);
        createHeaderCell(headerRow,"ID", 1);
        createHeaderCell(headerRow,"Nombre", 2);
        createHeaderCell(headerRow,"Descripción", 3);
        createHeaderCell(headerRow,"Cantidad", 4);
        createHeaderCell(headerRow,"UDS", 5);
        createHeaderCell(headerRow,"Retiro", 6);
        createHeaderCell(headerRow,"Fecha Retiro", 7);
        createHeaderCell(headerRow,"Precio", 8);

        int rowNum = 1;

        for (MaterialCell materialCell : materialCells) {
            Row row = sheet.createRow(rowNum++);
            Cell cell;
            cell = row.createCell(0);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(materialCell.getFechaIngreso());
            row.createCell(1).setCellValue(materialCell.getId());
            row.createCell(2).setCellValue(materialCell.getNombre());
            row.createCell(3).setCellValue(materialCell.getDescripcion());
            row.createCell(4).setCellValue(materialCell.getCantidad());
            row.createCell(5).setCellValue(materialCell.getUds());
            row.createCell(6).setCellValue(materialCell.getRetiro());
            cell = row.createCell(7);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(materialCell.getFechaRetiro());
            row.createCell(8).setCellValue(materialCell.getPrecio());
        }

        // Resize all columns to fit the content size
        for (int i = 0; i < 9; i++) {
            sheet.autoSizeColumn(i);
        }

    }

    private Cell createHeaderCell(Row headerRow, String title, int column) {
        Cell cell = headerRow.createCell(column);
        cell.setCellValue(title);
        cell.setCellStyle(headerCellStyle);
        return cell;
    }

    @Override
    public File export() {
        // Write the output to a file
        File file = null;
        FileOutputStream fileOut = null;
        try {
            file = File.createTempFile("Inventario_" + nombreProyecto , ".xlsx");
            fileOut = new FileOutputStream(file);
            workbook.write(fileOut);
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }
}
