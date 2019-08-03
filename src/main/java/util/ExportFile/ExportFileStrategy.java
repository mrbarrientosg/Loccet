package util.ExportFile;

import java.io.File;
import java.io.IOException;

public interface ExportFileStrategy {
    public File export() throws IOException;
}
