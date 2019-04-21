package util;

import java.io.File;

public class ExportFile {

    private ExportFileStrategy fileStrategy;

    public void changeStrategy(ExportFileStrategy strategy) {
        this.fileStrategy = strategy;
    }

    public File export() {
        return fileStrategy.export();
    }
}
