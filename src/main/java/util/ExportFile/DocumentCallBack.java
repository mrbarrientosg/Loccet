package util.ExportFile;

import com.itextpdf.text.Document;

public interface DocumentCallBack {
    void call(Document document) throws Exception;
}
