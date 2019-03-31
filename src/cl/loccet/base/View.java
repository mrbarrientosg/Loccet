package cl.loccet.base;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public abstract class View extends Component implements Initializable {

    private FXMLLoader fxmlLoader;

    public <T extends Node> T loadFXML() throws IOException {
        return loadFXML(null, false, null);
    }

    public <T extends Node> T loadFXML(String ruta) throws IOException {
        return loadFXML(ruta, false, null);
    }

    public <T extends Node> T loadFXML(String ruta, boolean atributoControlador, Object raiz) throws IOException {
        URL fxmlUrl = locFXML(ruta);

        fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(fxmlUrl);

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
            loc = "../resource/fxml/" + this.getClass().getSimpleName().replaceAll("View", "").toLowerCase() + ".fxml";
        else
            loc = ruta;

        return getResources().url(loc);
    }
}
