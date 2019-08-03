package util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import util.ExportFile.DocumentCallBack;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Clase que permite crear de mejor forma un PDF
 */
public final class PDFBuilder {

    private Document document;

    private File pdfNewFile;

    private PDFBuilder(String name) throws IOException, DocumentException {
        document = new Document();
        pdfNewFile = File.createTempFile(name, ".pdf");
        PdfWriter.getInstance(document, new FileOutputStream(pdfNewFile));
    }

    public static PDFBuilder create(String name) throws IOException, DocumentException {
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
