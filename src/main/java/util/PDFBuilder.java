package util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.function.Consumer;

public class PDFBuilder {

    private Document document;

    private File pdfNewFile;

    private PDFBuilder(String name) {
        document = new Document();
        try {
            pdfNewFile = File.createTempFile(name, ".pdf");
            PdfWriter.getInstance(document, new FileOutputStream(pdfNewFile));
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("No such file was found to generate the PDF "
                    + "(No se encontró el fichero para generar el pdf)" + fileNotFoundException);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    public static PDFBuilder create(String name) {
        return new PDFBuilder(name);
    }

    public void start(DocumentCallBack callback) {
        document.open();
        try {
            callback.call(document);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public File buildPDF() {
        document.close();
        return pdfNewFile;
    }
}
