package cl.loccet.base;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public abstract class View extends Component implements Initializable {

    private FXMLLoader fxmlLoader;

    public <T extends Node> T cargarFXML() throws IOException {
        return cargarFXML(null, false, null);
    }

    public <T extends Node> T cargarFXML(String ruta) throws IOException {
        return cargarFXML(ruta, false, null);
    }

    public <T extends Node> T cargarFXML(String ruta, boolean atributoControlador, Object raiz) throws IOException {
        URL fxmlUrl = locFXML(ruta);

        fxmlLoader = new FXMLLoader(fxmlUrl);

        if (raiz != null) fxmlLoader.setRoot(raiz);

        if (atributoControlador)
            fxmlLoader.setControllerFactory(param -> {  return this; });
        else
            fxmlLoader.setController(this);

        return fxmlLoader.load();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();
    }

    protected abstract void init();

    private URL locFXML(String ruta) {
        String loc;

        if (ruta == null)
            loc = this.getClass().getSimpleName() + ".fxml";
        else
            loc = ruta;

        return getResources().url(loc);
    }
}
