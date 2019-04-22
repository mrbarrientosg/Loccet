package util.ExportFile;

import java.io.File;

/**
 * Clas que maneja de la forma que se exporta un archivo
 */
public final class ExportFile {

    private ExportFileStrategy fileStrategy;

    public void changeStrategy(ExportFileStrategy strategy) {
        this.fileStrategy = strategy;
    }

    public File export() {
        return fileStrategy.export();
    }
}
